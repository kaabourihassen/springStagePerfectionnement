package org.gestion.bp.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.gestion.bp.configuration.JwtResponse;
import org.gestion.bp.configuration.jwt.AuthTokenFilter;
import org.gestion.bp.configuration.jwt.JwtUtils;
import org.gestion.bp.dao.UserRepository;
import org.gestion.bp.entities.Role;
import org.gestion.bp.entities.User;
import org.gestion.bp.exception.RessourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtUtils jwtUtils;


		
		public User insertUser(User user) {	
			return userRepository.save(user);
		}


		public void deleteUser(Long userId) {
			userRepository.deleteById(userId);
		}


	    public User getOneUser(Long userId) throws RessourceNotFoundException {
			return userRepository.findById(userId).orElseThrow(()-> new RessourceNotFoundException("user not found"));
	    }

	    public User updateUser(Long id,User user) {
			try {
				User user1 = getOneUser(id);
				user1.setAge(user.getAge());
				user1.setCin(user.getCin());
				user1.setFullName(user.getFullName());
				user1.setEmail(user.getEmail());
				return userRepository.save(user1);
			}catch(Exception e) {
				e.printStackTrace();
			}
			return user;
	    }
	    
	public List<User> findUsers(){
	    	return  userRepository.findUsersByRole(Role.USER);
	    }
	    
	public List<User> findAdmins(){
	    	return  userRepository.findUsersByRole(Role.ADMIN);
	    }
	public List<User> getAllRestMat() {
		return  userRepository.findUsersByRole(Role.RESP_MAT);
	}
	public List<User> getAllRestArt() {
		return  userRepository.findUsersByRole(Role.RESP_ART);
	}
	@Override
	public User loadUserByUsername(String email) throws UsernameNotFoundException {
		return userRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException(String.format("User not found", email)));
	}


    public User register(User user) {
		User user1 = new User();
		user1.setCin(user.getCin());
		user1.setAge(user.getAge());
		user1.setEmail(user.getEmail());
		user1.setFullName(user.getFullName());
		user1.setEnabled(false);
		user1.setRole(Role.USER);
		String encodedPass = passwordEncoder.encode(user.getPassword());
		user1.setPassword(encodedPass);
		return userRepository.save(user1);
    }


	public ResponseEntity<?> login(User loginRequest) {
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
}

