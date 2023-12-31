package com.insta.instagram.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import com.insta.instagram.exceptions.UserException;
import com.insta.instagram.model.User;
import com.insta.instagram.repository.UserRepository;
import com.insta.instagram.service.UserService;

@RestController
public class AuthController {

	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/signup")
	public ResponseEntity<User> signUp(@RequestBody User user) throws UserException {
		
			User registeredUser = userService.registerUser(user);
			return ResponseEntity.ok(registeredUser);
		
	}
	
	@GetMapping("/signin")
	public ResponseEntity<User> signInHandler(Authentication auth) {
		try {
			Optional<User> opt = userRepository.findByEmail(auth.getName());
			if (opt.isPresent()) {
				return ResponseEntity.accepted().body(opt.get());
			} else {
				throw new UsernameNotFoundException("User not found");
			}
		} catch (UsernameNotFoundException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}
}
