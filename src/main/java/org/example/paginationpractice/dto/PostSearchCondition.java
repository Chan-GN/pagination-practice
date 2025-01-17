package org.example.paginationpractice.dto;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class PostSearchCondition {

	private String title;

	private String author;

	private String category;

	private Boolean isPublished;

	private String sortBy = "createdAt";  // 기본 정렬 기준

	private String sortDirection = "DESC"; // 기본 정렬 방향

	private Integer pageSize = 10;         // 기본 페이지 크기

	private Integer pageNumber = 0;        // 기본 페이지 번호

	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime startDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime endDate;

	private Integer minLikes;

}
