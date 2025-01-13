package com.nhat.ecommerce.service;

import com.nhat.ecommerce.exception.ProductException;
import com.nhat.ecommerce.model.Cart;
import com.nhat.ecommerce.model.User;
import com.nhat.ecommerce.request.AddItemRequest;
import org.springframework.stereotype.Service;

@Service
public interface CartService {

    public Cart createCart(User user);

    public String addCartItem (Long userId, AddItemRequest req) throws ProductException;

    public Cart findUserCart(Long userId);


}
