package org.gestion.bp.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "opereations")
public class Operation implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="nomOp",length=20)
	@Size(min=3,max=20)
	@NotEmpty
	private String nomOp;
	
	@Column(name="natureOp",length=15)
	@Size(min=3,max=15)
	@NotEmpty
	private String natureOp;
	
	
	@Column(name="nomResp",length=15)
	@Size(min=3,max=15)
	@NotEmpty
	private String nomResp;
	
	@NotEmpty
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	private String dateOP;
	

	@ManyToOne 
	@JoinColumn(name="UserName")
	@JsonBackReference
	private User user;
	
	@JsonManagedReference
	@JsonIgnore
	@OneToMany(mappedBy="operation",cascade = CascadeType.ALL)
    private Set<OperationProduit> operationProduits;
}
