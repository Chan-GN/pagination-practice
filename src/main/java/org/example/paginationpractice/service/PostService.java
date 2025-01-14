package org.example.paginationpractice.service;

import org.example.paginationpractice.dto.PostResponse;
import org.example.paginationpractice.dto.PostSearchCondition;
import org.example.paginationpractice.repository.PostRepository;
import org.example.paginationpractice.repository.PostSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

	private final PostRepository postRepository;

	public Page<PostResponse> searchPosts(PostSearchCondition condition) {
		// 정렬 조건 생성
		Sort sort = Sort.by(
				Sort.Direction.fromString(condition.getSortDirection()),
				condition.getSortBy()
		);

		// 페이징 정보 생성
		Pageable pageable = PageRequest.of(
				condition.getPageNumber(),
				condition.getPageSize(),
				sort
		);

		// 검색 실행 및 결과 반환
		return postRepository.findAll(
						PostSpecification.searchBy(
								condition.getTitle(),
								condition.getAuthor(),
								condition.getCategory(),
								condition.getIsPublished(),
								condition.getStartDate(),
								condition.getEndDate(),
								condition.getMinLikes()
						),
						pageable)
				.map(PostResponse::from);  // Entity를 DTO로 변환
	}

}
