package org.gestion.bp.service;

import java.util.ArrayList;
import java.util.List;

import org.gestion.bp.dao.OperationRepository;
import org.gestion.bp.entities.ArticleConsomme;
import org.gestion.bp.entities.Materiel;
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
				Boolean b = articleCService.retraitQTE(operation.getQte(),operation.getProduit().getCode());
				if(b){
					operation.getUser().setRole(Role.USER);
					return operationRepository.save(operation);
				}
			}else if (operation.getNatureOperation().equals("versement")){
				articleCService.versementQTE(operation.getQte(),operation.getProduit().getCode());
				operation.getUser().setRole(Role.USER);
				return operationRepository.save(operation);
			}
		}else{
			if (operation.getNatureOperation().equals("retrait") ) {
				Boolean b = materielService.retrait(operation.getProduit().getCode());
				if(b){
					operation.getUser().setRole(Role.USER);
					return operationRepository.save(operation);
				}
			} else if (operation.getNatureOperation().equals("versement")) {
				Boolean b = materielService.versement(operation.getProduit().getCode());
				if (b) {
					operation.getUser().setRole(Role.USER);
					return operationRepository.save(operation);
				}
			}
		}
		return null;

	}

	public Operation getOneOperation(Long id) throws RessourceNotFoundException {
		return operationRepository.findById(id).orElseThrow(()-> new RessourceNotFoundException("user not found"));
	}
	
	public List<Operation> findAllOperations(){
		return  operationRepository.findAll();
	}
	public List<Operation> getOperationArticles(){
		List<Operation> materielsOp1 = new ArrayList<Operation>();
		List<Operation> materielsOp = operationRepository.findAll();
		for(int i=0;i<materielsOp.size();i++){
			if(materielsOp.get(i).getProduit() instanceof ArticleConsomme){
				materielsOp1.add(materielsOp.get(i));

			}
		}
		return materielsOp1;
	}
	public List<Operation> getOperationMateriels(){
		List<Operation> materielsOp1 = new ArrayList<Operation>();
		List<Operation> materielsOp = operationRepository.findAll();
		for(int i=0;i<materielsOp.size();i++){
			if(materielsOp.get(i).getProduit() instanceof Materiel){
				materielsOp1.add(materielsOp.get(i));

			}
		}
		return materielsOp1;
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

    public List<Operation> getOperations() {
		return operationRepository.findAll();
    }
}
