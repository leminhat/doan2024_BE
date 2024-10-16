package com.nhat.ecommerce.service;

import com.nhat.ecommerce.exception.UserException;
import com.nhat.ecommerce.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public User findUserById(Long userId) throws UserException;

    public User findUserProfileByJwt(String jwt) throws UserException;
}
