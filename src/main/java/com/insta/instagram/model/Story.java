package com.insta.instagram.model;

import java.time.LocalDateTime;

import com.insta.instagram.dto.UserDto;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="Stories")
public class Story {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="id" , column=@Column(name="user_Id")),
		@AttributeOverride(name="email" , column=@Column(name="user_email"))
	})
	private UserDto userDto;
	@NotNull
	private String image;
	private String caption;
	private LocalDateTime timestamp;
	public Story(Integer id, UserDto userDto, String image, String caption, LocalDateTime timestamp) {
		super();
		this.id = id;
		this.userDto = userDto;
		this.image = image;
		this.caption = caption;
		this.timestamp = timestamp;
	}
	public Story()
	{
		
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public UserDto getUserDto() {
		return userDto;
	}
	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	
}
