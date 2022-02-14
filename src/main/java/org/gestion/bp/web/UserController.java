package org.gestion.bp.web;

import java.util.List;
import java.util.stream.Collectors;

import org.gestion.bp.configuration.JwtResponse;
import org.gestion.bp.configuration.jwt.AuthTokenFilter;
import org.gestion.bp.configuration.jwt.JwtUtils;
import org.gestion.bp.entities.Materiel;
import org.gestion.bp.entities.Role;
import org.gestion.bp.exception.RessourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import org.gestion.bp.entities.User;
import org.gestion.bp.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {
    @Autowired
    UserService userService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtUtils jwtUtils;

	AuthTokenFilter authTokenFilter;

	@Autowired
	PasswordEncoder passwordEncoder;

	@PostMapping("/auth/signin")
	public ResponseEntity<?> authenticateUser(@RequestBody User loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		User userDetails = (User) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt,
				userDetails.getFullName(),userDetails.getUserId(),userDetails.getRole(),userDetails.getEmail(),true));
	}
	@PostMapping("/auth/register")
	public User register(@RequestBody User user){
		return userService.register(user);
	}
	 
	 @GetMapping("/profil")
	 public User getProfil(HttpServletRequest request) {
		 String token = authTokenFilter.parseJwt(request);
		 return userService.loadUserByUsername(jwtUtils.getUserNameFromJwtToken(token));
	 }
//Liste USER
	@GetMapping("/dashboard/ListUsers")
	public List<User> getAllUsers() {
		return userService.findUsers();
	}

	@GetMapping("/dashboard/ListUsers/{userId}")
	public User getOneUser(@PathVariable Long userId) throws RessourceNotFoundException {
		return userService.getOneUser(userId);
	}

	@PostMapping(value="/dashboard/InsertOuvrier")
	public User insertProdMateriel(@RequestBody User user) {
		return userService.register(user);
	}
	@PutMapping(value="/dashboard/updateUser/{userId}")
	public User updateUser(@PathVariable Long userId,@RequestBody User user) {
		return userService.updateUser(userId,user);
	}


	@GetMapping("/admin/ListAdmin")
	public List<User> getAllAdmins() {
		return userService.findAdmins();
	}

	@GetMapping("/admin/ListRestMat")
	public List<User> getAllRestMat() {
		return userService.getAllRestMat();
	}

	@GetMapping("/admin/ListRestArt")
	public List<User> getAllRestArt() {
		return userService.getAllRestArt();
	}

	@PutMapping(value="/admin/evolveToAdmin/{userId}")
	public User evolveToAdmin(@PathVariable Long userId) {
	    try {
			User user1 = userService.getOneUser(userId);
			user1.setRole(Role.ADMIN);
			return userService.updateUser(userId,user1);
	    }
	    catch(Exception e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	@PutMapping(value="/admin/evolveToRESPART/{userId}")
	public User evolveToRESPART(@PathVariable Long userId) {
		try {
			User user1 = userService.getOneUser(userId);
			user1.setRole(Role.RESP_ART);
			return userService.updateUser(userId,user1);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@PutMapping(value="/admin/evolveToRESPMAT/{userId}")
	public User evolveToRESPMAT(@PathVariable Long userId) {
		try {
			User user1 = userService.getOneUser(userId);
			user1.setRole(Role.RESP_MAT);
			return userService.updateUser(userId,user1);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@PutMapping(value="/admin/demoteToUser/{userId}")
	public User demoteToUser(@PathVariable Long userId) {
		try {
			User user1 = userService.getOneUser(userId);
			user1.setRole(Role.USER);
			return userService.updateUser(userId,user1);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	//Delete user
	@DeleteMapping("/admin/DeleteUser/{userId}")
	public void deleteUser(@PathVariable Long userId){
		userService.deleteUser(userId);
   	}


}