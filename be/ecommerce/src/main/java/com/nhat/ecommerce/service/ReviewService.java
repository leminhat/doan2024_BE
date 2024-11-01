package com.nhat.ecommerce.service;

import com.nhat.ecommerce.exception.ProductException;
import com.nhat.ecommerce.model.Review;
import com.nhat.ecommerce.request.ReviewRequest;
import com.nhat.ecommerce.model.User;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface ReviewService {
    public Review createReview(ReviewRequest req, User user) throws ProductException;
    public List<Review> getAllReview(Long productId);
}
