package org.gestion.bp.service;

import java.util.List;

import org.gestion.bp.dao.OperationRepository;
import org.gestion.bp.entities.Categorie;
import org.gestion.bp.entities.Magazin;
import org.gestion.bp.entities.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperationService {
	@Autowired
	private OperationRepository operationRepository;
	
	public Operation insertOperation(Operation operation) {
		return operationRepository.save(operation);
	}

	public Operation getOneOperation(int id){
		return operationRepository.getById(id);
	}
	
	public List<Operation> findAllOperations(){
		return  operationRepository.findAll();
	}

	public Operation updateOperation(int id,Operation operation){
		Operation operation1 = operationRepository.getById(id);
		operation1.setOperationProduits(operation.getOperationProduits());
		operation1.setDateOP(operation.getDateOP());
		operation1.setNatureOp(operation.getNatureOp());
		operation1.setUser(operation.getUser());
		operation1.setNomOp(operation.getNomOp());
		operation1.setNomResp(operation.getNomResp());
		return operationRepository.save(operation1);
	}
	
	public void deleteOperation(int id) {	
		operationRepository.deleteById(id);
	}

}
