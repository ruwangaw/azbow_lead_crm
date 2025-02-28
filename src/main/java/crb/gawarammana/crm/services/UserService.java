package crb.gawarammana.crm.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import crb.gawarammana.crm.config.JWTProviderService;
import crb.gawarammana.crm.exceptions.EmailNotFoundException;
import crb.gawarammana.crm.exceptions.UserNotFoundException;
import crb.gawarammana.crm.models.User;
import crb.gawarammana.crm.repositories.UserRepositoryInterface;

@Service
public class UserService {
	private final UserRepositoryInterface userRepository;
	private final JWTProviderService jwtProviderService;
	
	@Autowired
	public UserService(UserRepositoryInterface userRepository, JWTProviderService jwtProviderService) {
		this.userRepository = userRepository;
		this.jwtProviderService = jwtProviderService;
	}
	
	public User addUser(User user) {
		return userRepository.save(user);
	}
	
	public List<User> findAllUsers(){
		return userRepository.findAll();
	}
	
	public User findUserById(Long id) {
		return userRepository.findUserById(id).orElseThrow(()-> new UserNotFoundException("User by id " + id + " was not found"));
	}
	
	public User findUserByEmail(String email) {
		return userRepository.findUserByEmail(email).orElseThrow(()-> new UserNotFoundException("User by id " + email + " was not found"));
	}
	
	public User findUserByJWT(String jwt) {
		String jwtEmail = this.jwtProviderService.getEmailFromJwtToken(jwt);
		if(!(jwtEmail != null)) {
			throw new EmailNotFoundException("Provide valid token");
		}
		return this.findUserByEmail(jwtEmail);
	}
	
	public void deleteUser(Long id) {
		userRepository.deleteUserById(id);
	}
}
