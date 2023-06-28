package com.insta.instagram.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.insta.instagram.model.Post;

public interface PostRepository extends JpaRepository<Post,Integer> {

	@Query("Select p from post p where p.user.id=?1")
	public List<Post> findByUserId(Integer userId);
	

	@Query("Select p from post p where p.user.id IN: users ORDER BY p.createdAt DESC")
	public List<Post> findAllPostByUserIds(@Param("users") List<Integer> userIds);
}
