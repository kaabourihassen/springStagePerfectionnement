package org.gestion.bp.service;

import java.util.List;
import java.util.Optional;

import org.gestion.bp.dao.UserRepository;
import org.gestion.bp.entities.Role;
import org.gestion.bp.entities.User;
import org.gestion.bp.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
	@Autowired
	private UserRepository ur;
	@Autowired
	private UserRepository userRepository;

		
		public User insertUser(User user) {	
			return ur.save(user);
		}
		
		public List<User> findAllUsers() {	
			return ur.findAll();
		}
		
		public User UpdateHardUser(User user) {	
			return ur.save(user);
		}

		public void deleteUser(Long userId) {
			ur.deleteById(userId);
		}


	    public Optional<User> getOneUser(Long userId) {
			return ur.findById(userId);
	    }

	    public User updateUser(Long id,User user) {
			try {
				User user1 = getOneUser(id).orElseThrow(()-> new ResourceNotFoundException("user not found"));
				user1.setAge(user.getAge());
				user1.setCin(user.getCin());
				user1.setPrenom(user.getPrenom());
				return userRepository.save(user1);
			}catch(Exception e) {
				e.printStackTrace();
			}
			return user;
	    }
	    
	    
	    public List<User> findUsers(){
	    	return  ur.findUsersByRole(Role.USER);
	    }
	    
	    
	    public List<User> findAdmins(){
	    	return  ur.findUsersByRole(Role.ADMIN);
	    }

	@Override
	public User loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException(String.format("User not found", username)));
	}

	    
	}

