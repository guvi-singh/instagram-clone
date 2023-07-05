package com.insta.instagram.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insta.instagram.dto.UserDto;
import com.insta.instagram.exceptions.PostException;
import com.insta.instagram.exceptions.UserException;
import com.insta.instagram.model.Post;
import com.insta.instagram.model.User;
import com.insta.instagram.repository.PostRepository;
import com.insta.instagram.repository.UserRepository;

@Service
public class PostServiceImplementation implements PostService {

	@Autowired
	private PostRepository postRepository;
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public Post createPost(Post post, Integer userId) throws UserException {
		// TODO Auto-generated method stub
		try {
			User user= userService.findUserById(userId);
			UserDto userDto = new UserDto();
			userDto.setEmail(user.getEmail());
			userDto.setId(user.getId());
			userDto.setName(user.getName());
			userDto.setUserImage(user.getImage()
				)
			;
			
			userDto.setUsername(user.getUsername());
			
			post.setUser(userDto);
			Post createdPost = postRepository.save(post);
			return createdPost;
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String deletePost(Integer postId, Integer userId) throws UserException, PostException {
		// TODO Auto-generated method stub
		Post post = findPostById(postId);
		User user =  userService.findUserById(userId);
		if(post.getUser().getId()==user.getId())
		{
			postRepository.deleteById(postId);
			return "Successfully deleted post";
		}
		throw new PostException("You cant delete post of another user");
		
	}

	@Override
	public List<Post> findPostByUserId(Integer userId) throws UserException {

		List<Post> posts = postRepository.findByUserId(userId);
		if(posts.size()==0)
		{
			throw new UserException("This user dont have any post");
		}
		return posts;
	}

	@Override
	public Post findPostById(Integer postId) throws PostException {
		// TODO Auto-generated method stub
		Optional<Post> opt = postRepository.findById(postId);
		if(opt.isPresent())
		{
			return opt.get();
		}
		throw new PostException("Post not found with id "+postId);
	}

	@Override
	public List<Post> findAllPostByUserIds(List<Integer> userIds) throws PostException, UserException {
		// TODO Auto-generated method stub
		List<Post> posts = postRepository.findAllPostByUserIds(userIds);
		if(posts.size()==0)
		{
			throw new UserException("Post not available");
		}
		return posts;
	}

	@Override
	public String savedPost(Integer postId, Integer userId) throws PostException, UserException {
		// TODO Auto-generated method stub
		Post post = findPostById(postId);
		User user =  userService.findUserById(userId);
		if(!user.getSavedPost().contains(post))
		{
			user.getSavedPost().add(post);
			userRepository.save(user);
		}
		return "Post Saved Successfully";
	}

	@Override
	public String unsavedPost(Integer postId, Integer userId) throws PostException, UserException {
		Post post = findPostById(postId);
		User user =  userService.findUserById(userId);
		if(user.getSavedPost().contains(post))
		{
			user.getSavedPost().remove(post);
			userRepository.save(user);
		}
		return "Post Saved Successfully";
	}

	@Override
	public Post likePost(Integer postId, Integer userId) throws UserException, PostException {
		// TODO Auto-generated method stub
		Post post = findPostById(postId);
		User user =  userService.findUserById(userId);
		UserDto userDto = new UserDto();
		userDto.setEmail(user.getEmail());
		userDto.setId(user.getId());
		userDto.setName(user.getName());
		userDto.setUserImage(user.getImage());
		userDto.setUsername(user.getUsername());
		post.getLikedByUsers().add(userDto);
		return postRepository.save(post);
		
	}

	@Override
	public Post unLikePost(Integer postId, Integer userId) throws UserException, PostException {
		// TODO Auto-generated method stub
		Post post = findPostById(postId);
		User user =  userService.findUserById(userId);
		UserDto userDto = new UserDto();
		userDto.setEmail(user.getEmail());
		userDto.setId(user.getId());
		userDto.setName(user.getName());
		userDto.setUserImage(user.getImage());
		userDto.setUsername(user.getUsername());
		post.getLikedByUsers().remove(userDto);
		return postRepository.save(post);
		
	}



}
