package org.example.paginationpractice.controller;

import org.example.paginationpractice.dto.PostResponse;
import org.example.paginationpractice.dto.PostSearchCondition;
import org.example.paginationpractice.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

	private final PostService postService;

	@GetMapping("/specification")
	public Page<PostResponse> searchPostsWithSpecification(PostSearchCondition condition) {
		return postService.searchPostsWithSpecification(condition);
	}

	@GetMapping("/querydsl")
	public Page<PostResponse> searchPostsWithQueryDSL(PostSearchCondition condition) {
		return postService.searchPostsWithQueryDSL(condition);
	}

}
