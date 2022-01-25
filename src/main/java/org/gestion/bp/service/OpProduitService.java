package org.gestion.bp.service;

import org.gestion.bp.dao.OpProduitRepository;
import org.gestion.bp.entities.Operation;
import org.gestion.bp.entities.OperationProduit;
import org.gestion.bp.entities.Produit;
import org.gestion.bp.entities.User;
import org.gestion.bp.exception.RessourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OpProduitService {
	@Autowired
	private OpProduitRepository opProduitRepository;
	@Autowired
	private OperationService operationService;
	@Autowired
	private UserService userService;
	@Autowired
	private ProduitService produitService;


	public OperationProduit insertOpProd(Long userId,Operation operation,OperationProduit operationProduit,Long code) throws RessourceNotFoundException {
		User user = userService.getOneUser(userId).orElseThrow(()-> new RessourceNotFoundException("user not found"));
		operation.setUser(user);
		Operation operation1 = operationService.insertOperation(operation);
		operationProduit.setOperation(operation1);
		Produit produit = produitService.getOneProduit(code);
		operationProduit.setProduit(produit);
		return opProduitRepository.save(operationProduit);
	}
	public OperationProduit updateOpProd(Long id,OperationProduit operationProduit) throws RessourceNotFoundException {
		OperationProduit operationProduit1 = opProduitRepository.findById(id).orElseThrow(()-> new RessourceNotFoundException("operationProduit not found"));;
		operationProduit1.setProduit(operationProduit.getProduit());
		operationProduit1.setOperation(operationProduit.getOperation());
		operationProduit1.setDatePrise(operationProduit.getDatePrise());
		operationProduit1.setQte(operationProduit.getQte());
		operationProduit1.setDateRetour(operationProduit.getDateRetour());
		return opProduitRepository.save(operationProduit1);
	}
	public OperationProduit getOneOpProd(Long id) throws RessourceNotFoundException {
		return opProduitRepository.findById(id).orElseThrow(()-> new RessourceNotFoundException("operationProduit not found"));
	}
	public List<OperationProduit> getAllOpProd(){
		return opProduitRepository.findAll();
	}
	
	public void deleteOperationProduit(Long id) throws RessourceNotFoundException {
		OperationProduit operationProduit1 = opProduitRepository.findById(id).orElseThrow(()-> new RessourceNotFoundException("operationProduit not found"));;
		operationService.deleteOperation(operationProduit1.getOperation().getId());
		opProduitRepository.deleteById(id);
	}
	
	
}
