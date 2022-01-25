package org.gestion.bp.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data@Getter@Setter@NoArgsConstructor@AllArgsConstructor
@Entity
@DiscriminatorValue("Materiel")
public class Materiel extends Produit{
	@NotEmpty
	private LocalDateTime dateRetour;
}
