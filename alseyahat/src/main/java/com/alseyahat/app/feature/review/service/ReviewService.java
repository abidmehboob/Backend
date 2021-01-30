package com.alseyahat.app.feature.review.service;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.alseyahat.app.feature.review.repository.entity.Review;
import com.querydsl.core.types.Predicate;

public interface ReviewService {
	Review save(final Review entity);

	Review findOne(final Predicate predicate);

    Optional<Review> find_One(final Predicate predicate);

    Page<Review> findAll(final Predicate predicate, final Pageable pageable);


}