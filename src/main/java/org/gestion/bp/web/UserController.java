package org.gestion.bp.web;

import java.util.List;
import java.util.stream.Collectors;

import org.gestion.bp.configuration.JwtResponse;
import org.gestion.bp.configuration.jwt.AuthTokenFilter;
import org.gestion.bp.configuration.jwt.JwtUtils;
import org.gestion.bp.entities.Role;
import org.gestion.bp.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import org.gestion.bp.dao.MaterielRepository;
import org.gestion.bp.dao.OpProduitRepository;
import org.gestion.bp.dao.OperationRepository;
import org.gestion.bp.dao.ProduitRepository;
import org.gestion.bp.entities.User;
import org.gestion.bp.service.ArticleCService;
import org.gestion.bp.service.CategorieService;
import org.gestion.bp.service.MagazinService;
import org.gestion.bp.service.MaterielService;
import org.gestion.bp.service.OperationService;
import org.gestion.bp.service.ProduitService;
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
    CategorieService categorieService;
    
    @Autowired
    ProduitService produitService;
    
    @Autowired
    ProduitRepository produitRepository;
    
    @Autowired
    OperationRepository operationRepository;
    
    @Autowired
    MagazinService magazinService;
    
    @Autowired
    OperationService operationService;
    
    @Autowired
    MaterielService materielService;
    
    @Autowired
    MaterielRepository materielRepository;

	@Autowired
	OpProduitRepository opProduitRepository;
	
	@Autowired
	ArticleCService articleCService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtUtils jwtUtils;

	AuthTokenFilter authTokenFilter;
	 
	 @Value("${dir.images}")
	 private String imageDir;


	@PostMapping("/auth/signIn")
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
				userDetails.getUsername(),roles,userDetails.getPrenom(),true));
	}
	 
	 @GetMapping("/profil")
	 public User getProfil(HttpServletRequest request) {
		 String token = authTokenFilter.parseJwt(request);
		 return userService.loadUserByUsername(jwtUtils.getUserNameFromJwtToken(token));
	 }
//Liste USER
	@GetMapping("/ListUsers")
	public List<User> getAllUsers() {
		return userService.findUsers();
	}

	@GetMapping("/ListAdmin")
	public List<User> getAllA() {
		return userService.findAdmins();
	}
@PostMapping(value="/InsertOuvrier")
public User insertProdMateriel(@RequestBody User user) {
	 BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	    try {
	        String encodedPassword = encoder.encode(user.getPassword());
	        user.setPassword(encodedPassword);
			user.setRole(Role.USER);
	        return userService.insertUser(user);
	    }
	    catch(Exception e) {
	        e.printStackTrace();
	    }
	    return user;
	}

	@PutMapping(value="/evolveToAdmin/{userId}")
	public User evolveToAdmin(@PathVariable Long userId) {
	    try {
			User user1 = userService.getOneUser(userId).orElseThrow(()->new ResourceNotFoundException("user not found!"));
			user1.setRole(Role.ADMIN);
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


	@PutMapping(value="/updateUser/{userId}")
	public User updateUser(@PathVariable Long userId,@RequestBody User user) {
		return userService.updateUser(userId,user);
	}

	//Delete user
	@DeleteMapping("/DeleteUser/{userId}")
	public void deleteUser(@PathVariable Long userId){
		userService.deleteUser(userId);
   	}


}