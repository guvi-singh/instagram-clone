package com.insta.instagram.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insta.instagram.exceptions.CommentException;
import com.insta.instagram.exceptions.PostException;
import com.insta.instagram.exceptions.UserException;
import com.insta.instagram.model.Comment;
import com.insta.instagram.model.User;
import com.insta.instagram.service.CommentService;
import com.insta.instagram.service.UserService;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("create/{postId}")
	public ResponseEntity<Comment> createCommentHandler(@RequestBody Comment comment , @PathVariable Integer postId , @RequestHeader("Authorization") String token  ) throws UserException , CommentException, PostException
	{
		
		User user = userService.findUserProfile(token);
		Comment comment1 = commentService.createComment(comment, postId, user.getId());
		return new ResponseEntity<Comment>(comment1,HttpStatus.OK);
		
	}
	
	@PutMapping("/like/{commentId}")
	public ResponseEntity<Comment> likeCommentHandler(@RequestHeader("Authorization")  String token  , @PathVariable Integer commentId) throws UserException, CommentException{
		User user = userService.findUserProfile(token);
		Comment comment1 = commentService.likeComment(commentId,user.getId());
		return new ResponseEntity<Comment>(comment1,HttpStatus.OK);
		
	}
	
	@PutMapping("/unlike/{commentId}")
	public ResponseEntity<Comment> unlikeCommentHandler(@RequestHeader("Authorization")  String token  , @PathVariable Integer commentId) throws UserException, CommentException{
		User user = userService.findUserProfile(token);
		Comment comment1 = commentService.unLikeComment(commentId,user.getId());
		return new ResponseEntity<Comment>(comment1,HttpStatus.OK);
		
	}
	
}
