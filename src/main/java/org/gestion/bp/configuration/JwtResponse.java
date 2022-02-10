package org.gestion.bp.configuration;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.*;
import org.gestion.bp.entities.Role;

import java.util.List;

@Data@NoArgsConstructor@AllArgsConstructor@Getter@Setter
public class JwtResponse {
	private String token;
	private String type = "Bearer";
	@JsonSerialize(using= ToStringSerializer.class)
	private Long id;
	private String fullName;
	private Role role;
	private String email;
	private Boolean active;

	public JwtResponse(String token, String fullName, Long id,Role role, String email, Boolean active) {
		this.token = token;
		this.fullName = fullName;
		this.id=id;
		this.role = role;
		this.email = email;
		this.active = active;
	}

}
