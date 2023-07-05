package com.insta.instagram.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insta.instagram.dto.UserDto;
import com.insta.instagram.exceptions.StoryException;
import com.insta.instagram.exceptions.UserException;
import com.insta.instagram.model.Comment;
import com.insta.instagram.model.Post;
import com.insta.instagram.model.Story;
import com.insta.instagram.model.User;
import com.insta.instagram.repository.StoryRepository;

@Service 
public class StoryServiceImplementation implements StoryService {

	@Autowired
	private StoryRepository storyRepository;
	
	@Autowired
	private UserService userService;
	@Override
	public Story CreateStory(Story story, Integer userId) throws UserException {
		// TODO Auto-generated method stub
		User user = userService.findUserById(userId);
		UserDto userDto = new UserDto();
		
		userDto.setEmail(user.getEmail());
		userDto.setId(user.getId());
		userDto.setName(user.getName());
		userDto.setUserImage(user.getImage());
		userDto.setUsername(user.getUsername());
		story.setUserDto(userDto);
		story.setTimestamp(LocalDateTime.now());
		user.getStories().add(story);
		return storyRepository.save(story);
	}

	@Override
	public List<Story> findStoryByUserId(Integer userId) throws UserException, StoryException {
		// TODO Auto-generated method stub

		List<Story> story = storyRepository.findAllStoryByUserId(userId);
		if(story.size()==0)
		{
			throw new UserException("This user dont have any post");
		}
		return story;
		
	}

}
