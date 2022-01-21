package org.gestion.bp.service;

import org.gestion.bp.dao.OpProduitRepository;
import org.gestion.bp.entities.Operation;
import org.gestion.bp.entities.OperationProduit;
import org.gestion.bp.entities.Produit;
import org.gestion.bp.entities.User;
import org.gestion.bp.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jws.soap.SOAPBinding;


public class OpProduitService {
	@Autowired
	private OpProduitRepository opProduitRepository;
	@Autowired
	private OperationService operationService;
	@Autowired
	private UserService userService;
	@Autowired
	private ProduitService produitService;


	public OperationProduit insertOpProd(Long userId,Operation operation,OperationProduit operationProduit,int code) throws ResourceNotFoundException {
		User user = userService.getOneUser(userId).orElseThrow(()-> new ResourceNotFoundException("user not found"));
		operation.setUser(user);
		Operation operation1 = operationService.insertOperation(operation);
		operationProduit.setOperation(operation1);
		Produit produit = produitService.findById(code);
		operationProduit.setProduit(produit);
		return opProduitRepository.save(operationProduit);
	}
	public OperationProduit updateOpProd(int id,OperationProduit operationProduit){
		OperationProduit operationProduit1 = opProduitRepository.getById(id);
		operationProduit1.setProduit(operationProduit.getProduit());
		operationProduit1.setOperation(operationProduit.getOperation());
		operationProduit1.setDatePrise(operationProduit.getDatePrise());
		operationProduit1.setQte(operationProduit.getQte());
		operationProduit1.setDateRetour(operationProduit.getDateRetour());
		return opProduitRepository.save(operationProduit1);
	}
	
	public void deleteOperationProduit(int id) {	
		opProduitRepository.deleteById(id);
	}
	
	
}
