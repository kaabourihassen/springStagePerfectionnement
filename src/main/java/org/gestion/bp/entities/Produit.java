package org.gestion.bp.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import lombok.*;

@Data@Getter@Setter@NoArgsConstructor@AllArgsConstructor
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
    @Column(unique = true)
	private String matricule;

	@ManyToOne
	@JoinColumn(name="magazinId")
	private Magazin magazin;
	
	
	@ManyToOne
    @JoinColumn(name="categoryId")
	private Category category;

	@JsonIgnore
	@OneToMany(mappedBy="produit",cascade = CascadeType.MERGE)
    private List<Operation> operations;


	

}
