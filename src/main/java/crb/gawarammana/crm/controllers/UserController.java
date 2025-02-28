package crb.gawarammana.crm.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import crb.gawarammana.crm.models.User;
import crb.gawarammana.crm.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	private final UserService userService; 
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/find/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") Long id){
		User userById = userService.findUserById(id);
		return new ResponseEntity<>(userById, HttpStatus.OK);
	}
	
	@GetMapping("/find/currentUser")
	public ResponseEntity<User> getCurrentUser(@RequestHeader("Authorization") String jwt){
		User userByJWT = userService.findUserByJWT(jwt);
		return new ResponseEntity<>(userByJWT, HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<User>> getAllUsers(){
		List<User> users = userService.findAllUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		User newUser = userService.addUser(user);
		return new ResponseEntity<>(newUser, HttpStatus.CREATED);
	}
	
	@PutMapping("/update")
	public ResponseEntity<User> udateUser(@RequestBody User user) {
		User newUser = userService.addUser(user);
		return new ResponseEntity<>(newUser, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable("id") Long id){
		userService.deleteUser(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/profile")
	public ResponseEntity<User> getUserByJWT(@RequestHeader("Authorization") String jwt){
		User newUser = userService.findUserByJWT(jwt);
		return new ResponseEntity<>(newUser, HttpStatus.CREATED);
	}

}
