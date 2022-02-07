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
	@Column(name="id",length=15)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	
	@Column(name="cin",length=8)
	@NotEmpty
	@Size(min=8,max=8)
	private int cin ;
	
	@Column(name="Age",length=2)
	@Size(min=1,max=2)
	private int age;

	@Column(name = "email", unique = true)
	@NotEmpty
	private String email;

	@Column(name="Password",length=1200)
	@NotEmpty
	@Size(min=8,max=30)
	private String password;
	@Column(name = "fullName",length = 25)
	@NotEmpty
	private String fullName;
	private String photo;
	
	@NotNull
	private boolean enabled=true;

	@JsonBackReference
	private Role role;

	@JsonManagedReference
	@JsonIgnore
	@OneToMany(mappedBy="user",cascade = CascadeType.ALL , fetch =FetchType.EAGER)
	private List<Operation> operations;


	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority authority= new SimpleGrantedAuthority(role.name());
		return Collections.singletonList(authority);
	}

	@Override
	public String getUsername() {
		return getEmail();
	}


}