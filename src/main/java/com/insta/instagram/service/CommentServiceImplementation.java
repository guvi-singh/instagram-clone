package com.insta.instagram.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insta.instagram.dto.UserDto;
import com.insta.instagram.exceptions.CommentException;
import com.insta.instagram.exceptions.PostException;
import com.insta.instagram.exceptions.UserException;
import com.insta.instagram.model.Comment;
import com.insta.instagram.model.Post;
import com.insta.instagram.model.User;
import com.insta.instagram.repository.CommentRepository;
import com.insta.instagram.repository.PostRepository;

@Service
public class CommentServiceImplementation implements CommentService {

	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	private UserService userService;
	
	@Autowired
	private PostService postService;
	
	@Autowired
	PostRepository postRepository;
	@Override
	public Comment createComment(Comment comment, Integer postId, Integer userId) throws UserException, PostException {
		// TODO Auto-generated method stub
		User user = userService.findUserById(userId);
		Post post = postService.findPostById(postId);
		UserDto userDto = new UserDto();
		
		userDto.setEmail(user.getEmail());
		userDto.setId(user.getId());
		userDto.setName(user.getName());
		userDto.setUserImage(user.getImage());
		comment.setUserDto(userDto);
		comment.setCreatedAt(LocalDateTime.now());
		Comment createdComment = commentRepository.save(comment);
		post.getComments().add(createdComment);
		postRepository.save(post);
		
		
		return createdComment;
	}

	@Override
	public Comment findCommentById(Integer commentId) throws CommentException {
		// TODO Auto-generated method stub
		
		Optional<Comment> opt = commentRepository.findById(commentId);
		if(opt.isPresent())
		{
			return (Comment) opt.get();
		}
		throw new CommentException("comment dont exist " +commentId);
		
	}

	@Override
	public Comment likeComment(Integer commentId, Integer userId) throws CommentException, UserException {
		// TODO Auto-generated method stub
		User user = userService.findUserById(userId);
		Comment comment = findCommentById(commentId);
		UserDto userDto = new UserDto();
		userDto.setEmail(user.getEmail());
		userDto.setId(user.getId());
		userDto.setName(user.getName());
		userDto.setUserImage(user.getImage());
		comment.getLikedByUsers().add(userDto);
		return commentRepository.save(comment);
	}

	@Override
	public Comment unLikeComment(Integer commentId, Integer userId) throws CommentException, UserException {
		// TODO Auto-generated method stub
		User user = userService.findUserById(userId);
		Comment comment = findCommentById(commentId);
		UserDto userDto = new UserDto();
		userDto.setEmail(user.getEmail());
		userDto.setId(user.getId());
		userDto.setName(user.getName());
		userDto.setUserImage(user.getImage());
		comment.getLikedByUsers().remove(userDto);
		return commentRepository.save(comment);
	}

}
