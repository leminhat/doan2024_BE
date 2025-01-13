package com.nhat.ecommerce.service;

import com.nhat.ecommerce.exception.ProductException;
import com.nhat.ecommerce.model.Rating;
import com.nhat.ecommerce.request.RatingRequest;
import com.nhat.ecommerce.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RatingService {

    public Rating createRating(RatingRequest req, User user) throws ProductException;
    public List<Rating> getProductRatings(Long productId) ;
}
