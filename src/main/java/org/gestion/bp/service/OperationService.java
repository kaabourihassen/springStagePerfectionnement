package org.gestion.bp.service;

import java.util.List;

import org.gestion.bp.dao.OperationRepository;
import org.gestion.bp.entities.ArticleConsomme;
import org.gestion.bp.entities.Operation;
import org.gestion.bp.entities.Role;
import org.gestion.bp.exception.RessourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperationService {
	@Autowired
	private OperationRepository operationRepository;
	@Autowired
	ArticleCService articleCService;
	@Autowired
	MaterielService materielService;
	
	public Operation createOperation(Operation operation) throws RessourceNotFoundException {
		if(operation.getProduit() instanceof ArticleConsomme){
			if(operation.getNatureOperation().equals("retrait")){
				articleCService.retraitQTE(operation.getQte(),operation.getProduit().getCode());
			}else if (operation.getNatureOperation().equals("versemet")){
				articleCService.versementQTE(operation.getQte(),operation.getProduit().getCode());
			}
		}else{
			if (operation.getNatureOperation().equals("retrait") ) {
				materielService.retrait(operation.getProduit().getCode());
			} else if (operation.getNatureOperation().equals("versemet")) {
				materielService.versement(operation.getProduit().getCode());
			}
		}
		operation = operationRepository.save(operation);
		operation.getUser().setRole(Role.USER);
		return operation;
	}

	public Operation getOneOperation(Long id) throws RessourceNotFoundException {
		return operationRepository.findById(id).orElseThrow(()-> new RessourceNotFoundException("user not found"));
	}
	
	public List<Operation> findAllOperations(){
		return  operationRepository.findAll();
	}
	
	public void deleteOperation(Long id) throws RessourceNotFoundException {
		Operation operation = getOneOperation(id);
		if(operation.getProduit() instanceof ArticleConsomme){
			if(operation.getNatureOperation().equals("retrait")){
				articleCService.versementQTE(operation.getQte(),operation.getProduit().getCode());
			}else if (operation.getNatureOperation().equals("versemet")){
				articleCService.retraitQTE(operation.getQte(),operation.getProduit().getCode());
			}
		}else{
			if (operation.getNatureOperation().equals("retrait") ) {
				materielService.versement(operation.getProduit().getCode());
			} else if (operation.getNatureOperation().equals("versemet")) {
				materielService.retrait(operation.getProduit().getCode());
			}
		}
		operationRepository.deleteById(id);
	}

}
