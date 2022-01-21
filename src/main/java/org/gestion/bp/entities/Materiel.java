package org.gestion.bp.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

@Data@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue("Materiel")
@Table(name="Materiel")
public class Materiel extends Produit{

	@NotEmpty
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	private String dateRetour;
	
}
