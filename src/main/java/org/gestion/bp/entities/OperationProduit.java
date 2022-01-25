package org.gestion.bp.entities;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Data@Getter@Setter@NoArgsConstructor@AllArgsConstructor
@Entity
@Table(name = "operationproduits")
public class OperationProduit implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="codeProduit")
	@JsonBackReference
	private Produit produit;
	
	@NotEmpty
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	private String datePrise;
	
	@NotEmpty
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	private String dateRetour;
	
	@NotEmpty
	private int qte;
	private int test;
	private int testDesact;

	@ManyToOne 
    @JoinColumn(name="idOperation")
	@JsonBackReference
	private Operation operation;

}
