package com.nhat.ecommerce.service;

import com.nhat.ecommerce.exception.ProductException;
import com.nhat.ecommerce.model.Product;
import com.nhat.ecommerce.model.Rating;
import com.nhat.ecommerce.request.RatingRequest;
import com.nhat.ecommerce.model.User;
import com.nhat.ecommerce.repository.RatingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RatingServiceImplementation implements RatingService{

    private RatingRepository ratingRepository;
    private ProductService productService;

    public RatingServiceImplementation(RatingRepository ratingRepository,ProductService productService){
        this.ratingRepository = ratingRepository;
        this.productService = productService;
    }

    @Override
    public Rating createRating(RatingRequest req, User user) throws ProductException {

        Product product = productService.findProductById(req.getProductId());

        Rating rating = new Rating();
        rating.setProduct(product);
        rating.setUser(user);
        rating.setRating(req.getRating());
        rating.setCreateAt(LocalDateTime.now());

        return ratingRepository.save(rating);

    }

    @Override
    public List<Rating> getProductRatings(Long productId) {
        return ratingRepository.getAllProductsRating(productId);
    }
}
