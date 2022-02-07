package org.gestion.bp.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

@Data@Getter@Setter@NoArgsConstructor@AllArgsConstructor
@Entity
@Table(name="Categorie")
public class Category implements Serializable {
	@Id
	@Column(name="CategoryId",length=45)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotEmpty
	private Long CategoryId;
	@NotEmpty
	private String nomCateg;

	@NotEmpty
	private String description;
	

	@JsonIgnore
	@OneToMany(mappedBy= "category",cascade = CascadeType.ALL)
	private Collection<Produit> produits;

	
}