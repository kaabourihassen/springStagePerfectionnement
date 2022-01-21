package org.gestion.bp.configuration;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.*;

import java.util.List;

@Data@NoArgsConstructor@AllArgsConstructor@Getter@Setter
public class JwtResponse {
	private String token;
	private String type = "Bearer";
	@JsonSerialize(using= ToStringSerializer.class)
	private Long id;
	private String username;
	private List<String> roles;
	private String prenom;
	private Boolean active;

	public JwtResponse(String token, String username, List<String> roles, String prenom, Boolean active) {
		this.token = token;
		this.username = username;
		this.roles = roles;
		this.prenom = prenom;
		this.active = active;
	}

}
