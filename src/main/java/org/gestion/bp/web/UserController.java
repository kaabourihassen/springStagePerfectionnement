package org.gestion.bp.web;

import java.util.List;
import java.util.stream.Collectors;

import org.gestion.bp.configuration.JwtResponse;
import org.gestion.bp.configuration.jwt.AuthTokenFilter;
import org.gestion.bp.configuration.jwt.JwtUtils;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
				userDetails.getFullName(),userDetails.getUserId(),roles,userDetails.getEmail(),true));
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

	@GetMapping("/admin/ListAdmin")
	public List<User> getAllA() {
		return userService.findAdmins();
	}

	@PostMapping(value="/dashboard/InsertOuvrier")
	public User insertProdMateriel(@RequestBody User user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		try {
			String encodedPassword = passwordEncoder.encode(user.getPassword());
			user.setPassword(encodedPassword);
			user.setRole(Role.USER);
			return userService.insertUser(user);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	@PutMapping(value="/admin/evolveToAdmin/{userId}")
	public User evolveToAdmin(@PathVariable Long userId) {
	    try {
			User user1 = userService.getOneUser(userId);
			user1.setRole(Role.ADMIN);
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
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	//hethi chnou taamel
	//hethi chnou taamel
	//hethi chnou taamel
	//hethi chnou taamel
	//hethi chnou taamel
//	@RequestMapping(value="/RoleUserIntoDB", method= RequestMethod.POST)
//	public String AffRoleUserIntoDB(Model model, @ModelAttribute("USER") User user, @ModelAttribute("roles") Role role) {
//	try {
//		User u = userService.findById(user.getUsername());
//		System.out.println("$$$****----- : "+u.getUsername());
//		System.out.println("$$$****----- : "+user.getUsername());
//		u.setRoles(user.getRoles());
//		userService.insertUser(u);
//	}
//	catch(Exception e) {
//		e.printStackTrace();
//	}
//
//	return "redirect:/ListUsers";
//	}


	@PutMapping(value="/dashboard/updateUser/{userId}")
	public User updateUser(@PathVariable Long userId,@RequestBody User user) {
		return userService.updateUser(userId,user);
	}

	//Delete user
	@DeleteMapping("/admin/DeleteUser/{userId}")
	public void deleteUser(@PathVariable Long userId){
		userService.deleteUser(userId);
   	}


}