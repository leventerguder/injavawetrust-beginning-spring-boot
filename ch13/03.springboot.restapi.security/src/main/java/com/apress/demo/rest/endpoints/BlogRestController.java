package com.apress.demo.rest.endpoints;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.apress.demo.entities.Post;
import com.apress.demo.rest.model.PostsRequestDTO;
import com.apress.demo.rest.model.PostsResponseDTO;
import com.apress.demo.services.BlogService;

@RestController
public class BlogRestController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BlogRestController.class);

	@Autowired
	private BlogService blogService;

	@GetMapping(value = "/api/posts")
	public PostsResponseDTO findPosts(PostsRequestDTO request) {
		Page<Post> pageData = blogService.findPosts(request);
		return new PostsResponseDTO(pageData);
	}

	@GetMapping(value = "/api/posts/{postId}")
	public Optional<Post> findPostById(@PathVariable(value = "postId") Integer postId) {
		LOGGER.debug("View Post id: " + postId);
		Optional<Post> post = blogService.findPostById(postId);
		return post;
	}

	@PostMapping(value = "/api/admin/posts")
	public ResponseEntity<Post> createPost(@RequestBody Post post,
			HttpServletRequest request) {
		Post createdPost = this.blogService.createPost(post);
		return new ResponseEntity<>(createdPost, HttpStatus.OK);
	}

	@DeleteMapping(value = "/api/admin/posts/{postId}")
	public void deletePostById(@PathVariable(value = "postId") Integer postId) {
		LOGGER.debug("Delete Post id: " + postId);
		blogService.deletePost(postId);
	}

}
