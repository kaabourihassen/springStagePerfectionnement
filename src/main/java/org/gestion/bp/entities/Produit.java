package org.gestion.bp.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type_Produit")
@JsonTypeInfo(
		  use = JsonTypeInfo.Id.NAME, 
		  include = JsonTypeInfo.As.PROPERTY, 
		  property = "type_Produit")
		  @JsonSubTypes({ 
		  @Type(value = Materiel.class, name = "Materiel"), 
		  @Type(value = ArticleConsomme.class, name = "ArticleC") 
		})
public class Produit implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long code;
	@Column(name="intitule",length=35)
	@Size(min=3,max=15)
	@NotEmpty
	private String intitule;
	@NotEmpty
	private String matricule;
	private int test;
	private String photo;

	@ManyToOne
	@JoinColumn(name="nomMagazin")
	//@JsonIgnoreProperties("nomMagazin")
	@JsonBackReference
	private Magazin magazin;
	
	
	@ManyToOne
    @JoinColumn(name="nomCateg")
	//@JsonIgnoreProperties("nomCateg")
	@JsonBackReference
	private Categorie categorie;

	@JsonManagedReference
	@JsonIgnore
	@OneToMany(mappedBy="produit",cascade = CascadeType.REMOVE)
    private Set<OperationProduit> operationProduits;


	

}
