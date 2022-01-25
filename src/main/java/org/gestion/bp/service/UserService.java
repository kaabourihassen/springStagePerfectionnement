package org.gestion.bp.service;

import java.util.List;
import java.util.Optional;

import org.gestion.bp.dao.UserRepository;
import org.gestion.bp.entities.Role;
import org.gestion.bp.entities.User;
import org.gestion.bp.exception.RessourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
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

		
		public User insertUser(User user) {	
			return userRepository.save(user);
		}
		
		public List<User> findAllUsers() {	
			return userRepository.findAll();
		}
		
		public User UpdateHardUser(User user) {	
			return userRepository.save(user);
		}

		public void deleteUser(Long userId) {
			userRepository.deleteById(userId);
		}


	    public Optional<User> getOneUser(Long userId) {
			return userRepository.findById(userId);
	    }

	    public User updateUser(Long id,User user) {
			try {
				User user1 = getOneUser(id).orElseThrow(()-> new RessourceNotFoundException("user not found"));
				user1.setAge(user.getAge());
				user1.setCin(user.getCin());
				user1.setFullName(user.getFullName());
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
		user1.setEnabled(true);
		user1.setRole(Role.USER);
		String encodedPass = passwordEncoder.encode(user.getPassword());
		user1.setPassword(encodedPass);
		return userRepository.save(user1);
    }

}

