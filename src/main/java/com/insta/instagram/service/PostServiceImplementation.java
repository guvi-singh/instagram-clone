package com.insta.instagram.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insta.instagram.exceptions.PostException;
import com.insta.instagram.exceptions.UserException;
import com.insta.instagram.model.Post;
import com.insta.instagram.repository.PostRepository;

@Service
public class PostServiceImplementation implements PostService {

	@Autowired
	private PostRepository postRepository;
	@Override
	public Post createPost(Post post) throws UserException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deletePost(Integer postId, Integer userId) throws UserException, PostException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post> findPostByUserId(Integer userId) throws UserException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Post findPostById(Integer postId) throws PostException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post> findAllPostByUserIds(List<Integer> userIds) throws PostException, UserException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String savedPost(Integer postId, Integer userId) throws PostException, UserException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String unsavedPost(Integer postId, Integer userId) throws PostException, UserException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Post likePost(Integer postId, Integer userId) throws UserException, PostException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Post unLikePost(Integer postId, Integer userId) throws UserException, PostException {
		// TODO Auto-generated method stub
		return null;
	}

}
