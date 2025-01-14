package org.example.paginationpractice.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.example.paginationpractice.entity.Post;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;

public class PostSpecification {

	public static Specification<Post> searchBy(
			String title, String author, String category, Boolean isPublished,
			LocalDateTime startDate, LocalDateTime endDate, Integer minLikes
	) {
		return (root, query, criteriaBuilder) -> {
			List<Predicate> predicates = new ArrayList<>();

			// 제목 검색 조건: 부분 일치
			if (title != null && !title.isBlank()) {
				predicates.add(criteriaBuilder.like(root.get("title"), "%" + title + "%"));
			}

			// 작성자 검색 조건: 정확히 일치
			if (author != null && !author.isBlank()) {
				predicates.add(criteriaBuilder.equal(root.get("author"), author));
			}

			// 카테고리 검색 조건
			if (category != null && !category.isBlank()) {
				predicates.add(criteriaBuilder.equal(root.get("category"), category));
			}

			// 공개/비공개 상태 조건
			if (isPublished != null) {
				predicates.add(criteriaBuilder.equal(root.get("isPublished"), isPublished));
			}

			// 작성일 범위 검색
			if (startDate != null && endDate != null) {
				predicates.add(criteriaBuilder.between(root.get("createdAt"), startDate, endDate));
			} else if (startDate != null) {
				predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createdAt"), startDate));
			} else if (endDate != null) {
				predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("createdAt"), endDate));
			}

			// 최소 좋아요 수 조건
			if (minLikes != null && minLikes > 0) {
				predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("likeCount"), minLikes));
			}

			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
		};
	}

}
