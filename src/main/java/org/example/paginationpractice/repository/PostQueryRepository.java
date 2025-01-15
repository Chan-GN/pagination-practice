package org.example.paginationpractice.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.example.paginationpractice.entity.Post;
import org.example.paginationpractice.entity.QPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PostQueryRepository {

	private final JPAQueryFactory queryFactory;

	public Page<Post> searchPosts(
			String title, String author, String category, Boolean isPublished,
			LocalDateTime startDate, LocalDateTime endDate, Integer minLikes,
			Pageable pageable
	) {
		QPost post = QPost.post;

		BooleanBuilder builder = new BooleanBuilder();

		if (StringUtils.hasText(title)) {
			builder.and(post.title.contains(title));
		}

		if (StringUtils.hasText(author)) {
			builder.and(post.author.eq(author));
		}

		if (StringUtils.hasText(category)) {
			builder.and(post.category.eq(category));
		}

		if (isPublished != null) {
			builder.and(post.isPublished.eq(isPublished));
		}

		if (startDate != null && endDate != null) {
			builder.and(post.createdAt.between(startDate, endDate));
		} else if (startDate != null) {
			builder.and(post.createdAt.goe(startDate));
		} else if (endDate != null) {
			builder.and(post.createdAt.loe(endDate));
		}

		if (minLikes != null && minLikes > 0) {
			builder.and(post.likeCount.goe(minLikes));
		}

		Long totalCount = queryFactory
				.select(post.count())
				.from(post)
				.where(builder)
				.fetchOne();

		List<Post> posts = queryFactory
				.selectFrom(post)
				.where(builder)
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.orderBy(getOrderSpecifier(pageable.getSort()))
				.fetch();

		return new PageImpl<>(posts, pageable, totalCount == null ? 0 : totalCount);
	}

	private OrderSpecifier<?>[] getOrderSpecifier(Sort sort) {
		QPost post = QPost.post;

		return sort.stream()
				.map(order -> switch (order.getProperty()) {
					case "createdAt" -> order.isAscending() ?
							post.createdAt.asc() :
							post.createdAt.desc();
					case "viewCount" -> order.isAscending() ?
							post.viewCount.asc() :
							post.viewCount.desc();
					case "likeCount" -> order.isAscending() ?
							post.likeCount.asc() :
							post.likeCount.desc();
					default -> post.createdAt.desc();
				})
				.toArray(OrderSpecifier[]::new);
	}

}
