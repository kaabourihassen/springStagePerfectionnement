package org.gestion.bp.web;


import java.util.List;

import org.gestion.bp.entities.OperationProduit;
import org.gestion.bp.exception.RessourceNotFoundException;
import org.gestion.bp.service.OpProduitService;
import org.gestion.bp.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.gestion.bp.entities.Operation;

@RestController
public class OperationController {
	@Autowired
	OperationService operationService;
	@Autowired
	OpProduitService opProduitService;


	@GetMapping("/operations")
	public List<Operation> getAllOperations() {
		 return operationService.findAllOperations();
	 }

	@GetMapping("/operations/{opId}")
	public Operation getOneOperation(@PathVariable Long opId){
		return operationService.getOneOperation(opId);
	}

	@GetMapping("/operationProducts/{opProdId}")
	public OperationProduit getOneOpProd(@PathVariable Long opProdId) throws RessourceNotFoundException {
		return opProduitService.getOneOpProd(opProdId);
	}
	@GetMapping("/operationProducts")
	public List<OperationProduit> getAllOpProd(){
		return opProduitService.getAllOpProd();
	}

	@PostMapping("/operationProducts/{userId}/{produitId}")
	public OperationProduit createOperation(@PathVariable Long userId,@RequestPart("operation") Operation operation,@RequestPart("operationProduit") OperationProduit operationProduit,@PathVariable Long produitId) throws RessourceNotFoundException {
		return opProduitService.insertOpProd(userId,operation,operationProduit,produitId);
	}
	@PutMapping("/operationProducts/{opProdId}")
	public OperationProduit updateOpProd(@PathVariable Long opProdId,@RequestBody OperationProduit operationProduit) throws RessourceNotFoundException {
		return opProduitService.updateOpProd(opProdId,operationProduit);
	}
	@DeleteMapping("/operationProducts/{opProdId}")
	public void deleteOpProd(@PathVariable Long opProdId) throws RessourceNotFoundException {
		opProduitService.deleteOperationProduit(opProdId);
	}
}