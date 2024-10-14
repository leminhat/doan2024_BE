package com.nhat.ecommerce.service;

import com.nhat.ecommerce.exception.CartItemException;
import com.nhat.ecommerce.exception.UserException;
import com.nhat.ecommerce.model.Cart;
import com.nhat.ecommerce.model.CartItem;
import com.nhat.ecommerce.model.Product;

public interface CartItemService {
    public CartItem createCartItem(CartItem cartItem);

    public CartItem updateCartItem(Long userId,Long id,CartItem cartItem) throws CartItemException, UserException;

    public CartItem isCardItemExist(Cart cart, Product product,String size, Long UserId) throws CartItemException;

    public CartItem removeCartItem(Long userId,Long cartItemId) throws CartItemException, UserException;
}
