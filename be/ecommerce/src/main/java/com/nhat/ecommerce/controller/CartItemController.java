package com.nhat.ecommerce.controller;

import com.nhat.ecommerce.exception.CartItemException;
import com.nhat.ecommerce.exception.UserException;
import com.nhat.ecommerce.model.CartItem;
import com.nhat.ecommerce.model.User;
import com.nhat.ecommerce.response.ApiResponse;
import com.nhat.ecommerce.service.CartItemService;
import com.nhat.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart_items")
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private UserService userService;

    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable Long cartItemId
    , @RequestHeader("Authorization") String jwt) throws UserException, CartItemException {



        User user = userService.findUserProfileByJwt(jwt);
        cartItemService.removeCartItem(user.getId(), cartItemId);

        ApiResponse res = new ApiResponse();
        res.setMessage("deleted item to cart");
        res.setStatus(true);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PutMapping("/update/{cartItemId}")
    public ResponseEntity<ApiResponse> updateCartItem(@PathVariable Long cartItemId
            , @RequestHeader("Authorization") String jwt, @RequestBody CartItem cartItem) throws UserException, CartItemException {

        User user = userService.findUserProfileByJwt(jwt);
        cartItemService.updateCartItem(user.getId(), cartItemId, cartItem);

        System.out.println("cartItem" + cartItem.getQuantity());

        ApiResponse res = new ApiResponse();
        res.setMessage("update cart item successfully");
        res.setStatus(true);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
