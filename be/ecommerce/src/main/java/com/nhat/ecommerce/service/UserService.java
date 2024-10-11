package com.nhat.ecommerce.service;

import com.nhat.ecommerce.exception.UserException;
import com.nhat.ecommerce.model.User;


public interface UserService {
    public User findUserById(Long userId) throws UserException;

    public User findUserProfileByJwt(String jwt) throws UserException;
}
