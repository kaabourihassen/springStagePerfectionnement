package org.gestion.bp.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.*;

@Data@Getter@Setter@NoArgsConstructor@AllArgsConstructor
@Entity
@Table(name="Magazin")
public class Magazin implements Serializable {
	@Id
	@Column(name="magazinId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotEmpty
	private Long magazinId;
	@Column(name = "nomMagazin",length = 15,unique = true)
	private String nomMagazin;


	@JsonIgnore
	@OneToMany(mappedBy="magazin",cascade = CascadeType.MERGE)
	private List<Produit> produits;

	
}