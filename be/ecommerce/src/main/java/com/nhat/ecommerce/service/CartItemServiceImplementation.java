package com.nhat.ecommerce.service;

import com.nhat.ecommerce.exception.CartItemException;
import com.nhat.ecommerce.exception.UserException;
import com.nhat.ecommerce.model.Cart;
import com.nhat.ecommerce.model.CartItem;
import com.nhat.ecommerce.model.Product;
import com.nhat.ecommerce.model.User;
import com.nhat.ecommerce.repository.CartItemRepository;
import com.nhat.ecommerce.repository.CartRepository;

public class CartItemServiceImplementation implements CartItemService{

    public CartItemRepository cartItemRepository;
    public UserService userService;
    public CartRepository cartRepository;

    public CartItemServiceImplementation( CartItemRepository cartItemRepository,
                                          UserService userService,CartRepository cartRepository){
        this.cartItemRepository = cartItemRepository;
        this.userService = userService;
        this.cartRepository = cartRepository;
    }


    @Override
    public CartItem createCartItem(CartItem cartItem) {
        cartItem.setQuantity(1);
        cartItem.setPrice(cartItem.getProduct().getPrice()*cartItem.getQuantity());
        cartItem.setDiscountedPrice(cartItem.getProduct().getDiscountedPrice()*cartItem.getQuantity());

        CartItem createdCartItem = cartItemRepository.save(cartItem);

        return createdCartItem;
    }

    @Override
    public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException {

        CartItem item = findCartItemById(id);
        User user = userService.findUserById(item.getUserId());

        if(user.getId()==userId){
            item.setQuantity(cartItem.getQuantity());
            item.setPrice(cartItem.getProduct().getPrice()*cartItem.getQuantity());
            item.setDiscountedPrice(item.getProduct().getDiscountedPrice()*cartItem.getQuantity());
        }

        return null;
    }

    @Override
    public CartItem isCardItemExist(Cart cart, Product product, String size, Long UserId) throws CartItemException {
        return null;
    }

    @Override
    public CartItem removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException {
        return null;
    }
}
