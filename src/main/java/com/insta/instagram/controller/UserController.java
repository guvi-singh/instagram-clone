package com.insta.instagram.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.insta.instagram.exceptions.UserException;
import com.insta.instagram.model.User;
import com.insta.instagram.response.MessageResponse;
import com.insta.instagram.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	
	@GetMapping("id/{id}")
	public ResponseEntity<User> findUserByIdHandler(@PathVariable Integer id) throws UserException
	{
		User user = userService.findUserById(id);
		return new ResponseEntity<User>(user,HttpStatus.OK);
		
	}
	@GetMapping("username/{username}")
	public ResponseEntity<User> findUserByUserHandler(@PathVariable String userName) throws UserException
	{
		User user = userService.findUserByUsername(userName);
		return new ResponseEntity<User>(user,HttpStatus.OK);
		
	}
	
	@PutMapping("/follow/{followUserId}")
	public ResponseEntity<MessageResponse> followUserHandler(@PathVariable Integer userId)
	{
		return null;
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<User>> searchUserHandler(@RequestParam("q") String query) throws UserException
	{
		List<User> users = userService.searchUser(query);
		return new ResponseEntity<List<User>>(users,HttpStatus.OK);
		
	}
	@GetMapping("/m/{userIds}")
	public ResponseEntity<List<User>> searchUserByUserIds(@PathVariable List<Integer> userIds) throws UserException
	{
		List<User> users = userService.findUserByIds(userIds);
		return new ResponseEntity<List<User>>(users,HttpStatus.OK);
		
	}
	
}
