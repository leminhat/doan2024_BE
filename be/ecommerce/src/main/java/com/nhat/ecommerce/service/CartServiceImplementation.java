package com.nhat.ecommerce.service;


import com.nhat.ecommerce.exception.ProductException;
import com.nhat.ecommerce.model.Cart;
import com.nhat.ecommerce.model.User;
import com.nhat.ecommerce.request.AddItemRequest;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImplementation implements CartService {



    @Override
    public Cart createCart(User user) {
        return null;
    }

    @Override
    public String addCartItem(Long userId, AddItemRequest req) throws ProductException {
        return "";
    }

    @Override
    public Cart findUserCart(Long userId) {
        return null;
    }
}
