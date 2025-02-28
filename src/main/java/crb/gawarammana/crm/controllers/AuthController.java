package crb.gawarammana.crm.controllers;

import org.hibernate.NonUniqueResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import crb.gawarammana.crm.config.CustomUserDetailsService;
import crb.gawarammana.crm.config.JWTProviderService;
import crb.gawarammana.crm.exceptions.UserNotFoundException;
import crb.gawarammana.crm.models.User;
import crb.gawarammana.crm.requests.LoginRequest;
import crb.gawarammana.crm.responses.AuthResponse;
import crb.gawarammana.crm.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private final UserService userService;
	private final CustomUserDetailsService customUserDetailService;
	private final JWTProviderService jwtProviderService;
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public AuthController(UserService userService, CustomUserDetailsService customUserDetailService, JWTProviderService jwtProviderService, PasswordEncoder passwordEncoder) {
		this.userService = userService;
		this.customUserDetailService = customUserDetailService;
		this.jwtProviderService = jwtProviderService;
		this.passwordEncoder = passwordEncoder;
	}
	
	@PostMapping("/signup")
	public AuthResponse createUser(@RequestBody User user) {
		User createdUser = new User();
		User registeredUser = new User();
		User savedUser = new User();

		String email = user.getEmail();
		String password = user.getPassword();
		String firstName = user.getFirstname();
		String lastName = user.getLastname();
		try {
			registeredUser = this.userService.findUserByEmail(email);
			if(registeredUser != null) {
				throw new BadCredentialsException("email already exists");
			}
		} catch(UserNotFoundException e){
			createdUser.setEmail(email);
			createdUser.setPassword(passwordEncoder.encode(password));
			createdUser.setFirstname(firstName);
			createdUser.setLastname(lastName);
			savedUser = this.userService.addUser(createdUser);
		} catch (NonUniqueResultException e) {
			throw new BadCredentialsException("email already exists");
		}
		
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = this.jwtProviderService.generateToken(authentication);
		AuthResponse authResponse = new AuthResponse();
		authResponse.setJwt(token);
		authResponse.setMessage("Sign up successful");
		return authResponse;
	}

	@PostMapping("/login")
	public AuthResponse signInHandler(@RequestBody LoginRequest loginRequest) {
		AuthResponse authResponse = new AuthResponse();
		String username = loginRequest.getEmail();
		String password = loginRequest.getPassword();
		
		UsernamePasswordAuthenticationToken authentication = authenticate(username, password);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = this.jwtProviderService.generateToken(authentication);
		authResponse.setJwt(token);
		authResponse.setMessage("Sign in successful");
		return authResponse;
	}

	private UsernamePasswordAuthenticationToken authenticate(String username, String password) {
		UserDetails userDetails = this.customUserDetailService.loadUserByUsername(username);
		if(!passwordEncoder.matches(password, userDetails.getPassword())){
			throw new BadCredentialsException("Invalid password");
		}
		return new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
	}
}
