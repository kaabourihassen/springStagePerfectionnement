package org.gestion.bp.entities;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data@Getter@Setter@NoArgsConstructor@AllArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {

	@Id
	@Column(name="username",length=15)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;

	@Column(name = "username",length = 25)
	private String username;
	
	@Column(name="cin",length=8)
	@NotEmpty
	@Size(min=8,max=8)
	private int cin ;
	
	@Column(name="Prenom",length=15)
	@NotEmpty
	@Size(min=3,max=15)
	private String prenom;
	
	@Column(name="Age",length=2)
	@NotEmpty
	@Size(min=1,max=2)
	private int age;

	@Column(name = "email")
	@NotEmpty
	private String email;

	@Column(name="Password",length=1200)
	@NotEmpty
	@Size(min=8,max=30)
	private String password;
	
	private String photo;
	
	@NotNull
	private boolean enabled=true;

	@JsonBackReference
	private Role role;

	@JsonManagedReference
	@JsonIgnore
	@OneToMany(mappedBy="user",cascade = CascadeType.ALL , fetch =FetchType.EAGER)
	private List<Operation> operations;



	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority authority= new SimpleGrantedAuthority(role.name());
		return Collections.singletonList(authority);
	}




}