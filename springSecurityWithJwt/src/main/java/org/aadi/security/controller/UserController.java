package org.aadi.security.controller;

import java.security.Principal;

import org.aadi.security.model.User;
import org.aadi.security.model.UserRequest;
import org.aadi.security.model.UserResponse;
import org.aadi.security.service.UserService;
import org.aadi.security.util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
	
	private final UserService userService;
	private final JwtUtil jwtUtil;
	private final AuthenticationManager authenticationManager;
	
	@PostMapping("/save")
	public ResponseEntity<String> saveUser(@RequestBody User user) {
		Integer id = userService.saveUser(user);
		String body="User with userId : '"+id+"', is successfully created and saved in Database.";
		return ResponseEntity.ok(body);		
	}
	
	@PostMapping("/token")
	public ResponseEntity<UserResponse> loginUser(@RequestBody UserRequest userRequest){
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						userRequest.getUsername(), 
						userRequest.getPassword()
						)
				);
		String token = jwtUtil.generateToken(userRequest.getUsername());
		return ResponseEntity.ok(new UserResponse(token, "Your token hasbeen successfully generated. Use it within 15 minute."));
		
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> accessUserData(Principal p) {
		return ResponseEntity.ok("Welcome! User : "+p.getName()+". You are now successfully logged in inside a secure Application.");
	}

}