package org.gestion.bp.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.*;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Data@Getter@Setter@NoArgsConstructor@AllArgsConstructor
@Entity
@Table(name = "opereations")
public class Operation implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="natureOp",length=15)
	@Size(min=3,max=15)
	@NotEmpty
	private String natureOperation;

	@NotEmpty
	private LocalDate datePrise;

	@NotEmpty
	private LocalDate dateRetour;

	@NotEmpty
	private int qte;
	

	@ManyToOne 
	@JoinColumn(name="ouvrier")
	private User user;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="codeProduit")
	private Produit produit;

}
