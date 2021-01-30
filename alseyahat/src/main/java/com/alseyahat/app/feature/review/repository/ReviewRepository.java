package com.alseyahat.app.feature.review.repository;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.alseyahat.app.feature.review.repository.entity.Review;

public interface ReviewRepository extends PagingAndSortingRepository<Review, Long>, QuerydslPredicateExecutor<Review> {

}
