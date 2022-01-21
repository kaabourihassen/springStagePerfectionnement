package org.gestion.bp.entities;

import lombok.*;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue("ArticleConsomme")
@Table(name="ArticleConsomme")
public class ArticleConsomme extends Produit  {

	@NotEmpty
	private int qte;
	@NotEmpty
	private int qteMin;

	
}