package com.insta.instagram.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.insta.instagram.dto.UserDto;
import com.insta.instagram.exceptions.UserException;
import com.insta.instagram.model.User;
import com.insta.instagram.repository.UserRepository;
import com.insta.instagram.security.JwtTokenClaims;
import com.insta.instagram.security.JwtTokenProvider;

@Service
public class UserServiceImplementation implements UserService {

	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	@Override
	public User registerUser(User user) throws UserException {
		// TODO Auto-generated method stub
		Optional<User> isEmailExist = userRepository.findByEmail(user.getEmail());
		if(isEmailExist.isPresent())
		{
			throw new UserException("Email Already Exist");
		}	
		
		Optional<User> isUserNameExist = userRepository.findByUsername(user.getUsername());
		if(isUserNameExist.isPresent())
		{
			throw new UserException("User Name Already Exist");
		}
		
		if(user.getEmail()==null || user.getPassword()==null || user.getName()==null)
		{
			throw new UserException("All fields are required");
		}
		
		User newUser = new User();
		newUser.setEmail(user.getEmail());
		newUser.setPassword(passwordEncoder.encode(user.getPassword()));
		newUser.setUsername(user.getUsername());
		newUser.setName(user.getName());
		
		return userRepository.save(newUser);
		

	}

	@Override
	public User findUserById(Integer userId) throws UserException {
		// TODO Auto-generated method stub

		Optional<User> opt = userRepository.findById(userId);
		if(opt.isPresent())
		{
			return opt.get();
		}
		throw new UserException("User not exist with id: "+userId);
		
	}

	@Override
	public User findUserProfile(String token) throws UserException {
		// TODO Auto-generated method stub
		 token = token.substring(7);
		 JwtTokenClaims jwtTokenClaims = jwtTokenProvider.getClaimsFromToken(token);
		 String email = jwtTokenClaims.getUsername();
		 Optional<User> opt = userRepository.findByEmail(email);
		 if(opt.isPresent())
		 {
			 return opt.get();
		 }
		throw new UserException("invalid token");
	}

	@Override
	public User findUserByUsername(String username) throws UserException {
		// TODO Auto-generated method stub
	
		
		return null;
	}

	@Override
	public String followUser(Integer regUserId, int followUserId) throws UserException {
		// TODO Auto-generated method stub
		User reqUser = findUserById(regUserId);
		User followUser = findUserById(followUserId);
		UserDto follower = new UserDto();
		follower.setEmail(reqUser.getEmail());
		follower.setId(reqUser.getId());
		follower.setName(reqUser.getName());
		follower.setUserImage(reqUser.getImage());
		follower.setUsername(reqUser.getEmail());
		
		UserDto following = new UserDto();
		following.setEmail(follower.getEmail());
		following.setId(follower.getId());
		following.setName(follower.getName());
		following.setUserImage(follower.getUserImage());
		following.setUsername(follower.getUsername());
		reqUser.getFollowing().add(following);
		followUser.getFollower().add(follower);
		userRepository.save(followUser);
		userRepository.save(reqUser);
		
		return "You are following "+followUser.getUsername();
	}

	@Override
	public String unFollowUser(Integer regUserId, int followUserId) throws UserException {
		// TODO Auto-generated method stub
		User reqUser = findUserById(regUserId);
		User followUser = findUserById(followUserId);
		UserDto follower = new UserDto();
		follower.setEmail(reqUser.getEmail());
		follower.setId(reqUser.getId());
		follower.setName(reqUser.getName());
		follower.setUserImage(reqUser.getImage());
		follower.setUsername(reqUser.getEmail());
		
		UserDto following = new UserDto();
		following.setEmail(follower.getEmail());
		following.setId(follower.getId());
		following.setName(follower.getName());
		following.setUserImage(follower.getUserImage());
		following.setUsername(follower.getEmail());
		reqUser.getFollowing().remove(following);
		followUser.getFollower().remove(follower);
		userRepository.save(followUser);
		userRepository.save(reqUser);
		
		return "You have unfollowed "+followUser.getUsername();
	}

	@Override
	public List<User> findUserByIds(List<Integer> userIds) throws UserException {
		// TODO Auto-generated method stub
		List<User> users = userRepository.findAllUsersByUserIds(userIds);
		return users;
	}

	@Override
	public List<User> searchUser(String query) throws UserException {
		// TODO Auto-generated method stub
		List<User> users = userRepository.findByQuery(query);
		if(users.size()==0) {
			throw new UserException("user not found");
		}
		return users;
	}

	@Override
	public User updateUserDetails(User updatedUser, User existingUser) throws UserException {
		// TODO Auto-generated method stub
		if(updatedUser.getEmail()!=null)
		{
			existingUser.setEmail(updatedUser.getEmail());
		}
		return null;
	}

}
