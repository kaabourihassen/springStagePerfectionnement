package org.gestion.bp.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Categorie")
@DiscriminatorValue("categorie")
public class Categorie implements Serializable {
	@Id
	@Column(name="nomCateg",length=45)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotEmpty
	private Long id;
	@NotEmpty
	private String nomCateg;

	@NotEmpty
	private String description;
	
	@JsonManagedReference
	@JsonIgnore
	@OneToMany(mappedBy="categorie",cascade = CascadeType.ALL)
	private Collection<Produit> produits;

	
}