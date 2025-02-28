package crb.gawarammana.crm.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import crb.gawarammana.crm.models.User;
import crb.gawarammana.crm.repositories.UserRepositoryInterface;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	private final UserRepositoryInterface userRepository;
	
	@Autowired
	public CustomUserDetailsService(UserRepositoryInterface userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findUserByEmail(username).orElseThrow(()-> new BadCredentialsException("User by id " + username + " was not found"));		
		List<GrantedAuthority> authorities = new ArrayList<>();
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),authorities);
	}

}
