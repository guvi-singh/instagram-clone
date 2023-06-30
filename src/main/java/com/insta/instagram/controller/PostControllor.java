package com.insta.instagram.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.insta.instagram.exceptions.PostException;
import com.insta.instagram.exceptions.UserException;
import com.insta.instagram.model.Post;
import com.insta.instagram.model.User;
import com.insta.instagram.response.MessageResponse;
import com.insta.instagram.service.PostService;
import com.insta.instagram.service.UserService;

@RestController
@RequestMapping("/api/posts")
public class PostControllor {

	@Autowired
	private PostService postService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/create")
	public ResponseEntity<Post> createPostHandler(@RequestBody Post post ,@RequestHeader("Authorization")  String token) throws UserException
	{
		User user = userService.findUserProfile(token);
		Post createdPost = postService.createPost(post, user.getId());
		
		return new ResponseEntity<Post>(createdPost , HttpStatus.OK);
		
		
	}
	
	@GetMapping("/all/{id}")
	public ResponseEntity<List<Post>> findPostByUserIdHandler(@PathVariable Integer id) throws UserException
	{
		List<Post> posts = postService.findPostByUserId(id);
		
		return new ResponseEntity<List<Post>>(posts  , HttpStatus.OK);
		
		
	}
	@GetMapping("/following/{id}")
	public ResponseEntity<List<Post>> findAllPostByUserIds(@PathVariable("ids") List<Integer> userIds) throws UserException, PostException
	{
		List<Post> posts = postService.findAllPostByUserIds(userIds);
		
		return new ResponseEntity<List<Post>>(posts  , HttpStatus.OK);
		
		
	}

	
	@GetMapping("/{postId}")
	public ResponseEntity<Post> findPostByIdHandler(@PathVariable Integer postId) throws UserException, PostException
	{
		Post post = postService.findPostById(postId);
		
		return new ResponseEntity <Post>(post  , HttpStatus.OK);
		
		
	}
	@PutMapping("/like/{postId}")
	public ResponseEntity<Post> likePostHandler(@PathVariable Integer postId , @RequestHeader("Authorization")  String token) throws UserException, PostException
	{
		User user = userService.findUserProfile(token);
		Post likedPost = postService.likePost(postId, user.getId());
		Post post = postService.findPostById(postId);
		
		return new ResponseEntity <Post>(post  , HttpStatus.OK);
		
		
	}
	@PutMapping("/unLike/{postId}")
	public ResponseEntity<Post> unLikePostHandler(@PathVariable Integer postId , @RequestHeader("Authorization")  String token) throws UserException, PostException
	{
		User user = userService.findUserProfile(token);
		Post likedPost = postService.unLikePost(postId, user.getId());
		Post post = postService.findPostById(postId);
		
		return new ResponseEntity <Post>(post  , HttpStatus.OK);
		
		
	}
	@PutMapping("/save_post/{postId}")
	public ResponseEntity<MessageResponse> savedPostHandler(@PathVariable Integer postId  ,@RequestHeader("Authorization")  String token) throws UserException, PostException
	{
		User user = userService.findUserProfile(token);
		String message = postService.savedPost(postId,user.getId());
		MessageResponse res = new MessageResponse(message);
		return new ResponseEntity<MessageResponse>(res, HttpStatus.ACCEPTED);
		
	}
	
	@DeleteMapping("/delete/{postId}")
	public ResponseEntity<MessageResponse> deletePostHandler(@PathVariable Integer postId  ,@RequestHeader("Authorization")  String token) throws UserException, PostException
	{
		User user = userService.findUserProfile(token);
		String message = postService.deletePost(postId,user.getId());
		MessageResponse res = new MessageResponse(message);
		return new ResponseEntity<MessageResponse>(res, HttpStatus.ACCEPTED);
		
	}
	@PutMapping("/unsave_post/{postId}")
	public ResponseEntity<MessageResponse> unSavedPostHandler(@PathVariable Integer postId  ,@RequestHeader("Authorization")  String token) throws UserException, PostException
	{
		User user = userService.findUserProfile(token);
		String message = postService.unsavedPost(postId,user.getId());
		MessageResponse res = new MessageResponse(message);
		return new ResponseEntity<MessageResponse>(res, HttpStatus.ACCEPTED);
		
	}
//	public ResponseEntity<MessageResponse> deletePostHandler (Integer postId , @RequestHeader("Authorization")  String token) throws UserException, PostException
//	{
//		User user = userService.findUserProfile(token);
//		String message = postService.deletePost(postId,user.getId());
//		MessageResponse res = new MessageResponse(message);
//		return new ResponseEntity<MessageResponse>(res,HttpStatus.ACCEPTED);
//	
//		
//	}
}
