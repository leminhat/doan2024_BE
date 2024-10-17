package com.nhat.ecommerce.service;

import com.nhat.ecommerce.config.JwtProvider;
import com.nhat.ecommerce.exception.UserException;
import com.nhat.ecommerce.model.User;
import com.nhat.ecommerce.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {

    private UserRepository userRepository;
    private JwtProvider jwtProvider;

    public UserServiceImplementation(final UserRepository userRepository, final JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public User findUserById(Long userId) throws UserException {

        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()) {
            return user.get();
        }
        throw new UserException("User not found with id: " + userId);
    }

    @Override
    public User findUserProfileByJwt(String jwt) throws UserException {
        String email = jwtProvider.getEmailfromToken(jwt);

        User user = userRepository.findByEmail(email);
        if(user == null) {
            throw new UserException("User not found with email: " + email);

        }
        return user;
    }
}
