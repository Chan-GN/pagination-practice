package org.example.paginationpractice.dto;

import org.example.paginationpractice.entity.Post;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostResponse {

	private Long id;

	private String title;

	private String author;

	private String category;

	private Integer likeCount;

	private Long viewCount;

	private Integer commentCount;

	private String createdAt;

	public static PostResponse from(Post post) {
		return PostResponse.builder()
				.id(post.getId())
				.title(post.getTitle())
				.author(post.getAuthor())
				.category(post.getCategory())
				.likeCount(post.getLikeCount())
				.viewCount(post.getViewCount())
				.commentCount(post.getCommentCount())
				.createdAt(post.getCreatedAt().toString())
				.build();
	}

}
