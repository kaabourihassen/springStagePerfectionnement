package org.gestion.bp.web;


import java.util.List;

import org.gestion.bp.exception.RessourceNotFoundException;
import org.gestion.bp.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.gestion.bp.entities.Operation;

@RestController
@RequestMapping("/operations")
public class OperationController {
	@Autowired
	OperationService operationService;



	@GetMapping("/articles")
	public List<Operation> getOperationsArticle() {
		 return operationService.getOperationArticles();
	 }
	@GetMapping("/materiels")
	public List<Operation> getOperationsMaretiel() {
		return operationService.getOperationMateriels();
	}

	@GetMapping("/{opId}")
	public Operation getOneOperation(@PathVariable Long opId) throws RessourceNotFoundException {
		return operationService.getOneOperation(opId);
	}

	@PostMapping("")
	public Operation createOperation(@RequestBody Operation operation) throws RessourceNotFoundException {
		return operationService.createOperation(operation);
	}

	@DeleteMapping("/deleteOperation/{opId}")
	public void deleteOperation(@PathVariable Long opId) throws RessourceNotFoundException {
		operationService.deleteOperation(opId);
	}

}