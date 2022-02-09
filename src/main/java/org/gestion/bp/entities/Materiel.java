package org.gestion.bp.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import lombok.*;
import java.time.LocalDate;

@Data@Getter@Setter@NoArgsConstructor@AllArgsConstructor
@Entity
@DiscriminatorValue("Materiel")
public class Materiel extends Produit{
	@NotEmpty
	private LocalDate dateRetour;
}
