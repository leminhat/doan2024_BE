package com.nhat.ecommerce.controller;


import com.nhat.ecommerce.config.JwtProvider;
import com.nhat.ecommerce.model.Cart;
import com.nhat.ecommerce.model.User;
import com.nhat.ecommerce.repository.UserRepository;
import com.nhat.ecommerce.request.LoginRequest;
import com.nhat.ecommerce.response.AuthRespone;
import com.nhat.ecommerce.service.CartService;
import com.nhat.ecommerce.service.CustomerUserServiceImplementation;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private JwtProvider jwtProvider;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private CustomerUserServiceImplementation customerUserService;
    private CartService cartService;

    public AuthController(UserRepository userRepository, CustomerUserServiceImplementation customerUserService,
                          PasswordEncoder passwordEncoder, JwtProvider jwtProvider,  CartService cartService) {
        this.userRepository = userRepository;
        this.customerUserService = customerUserService;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider=jwtProvider;
        this.cartService=cartService;
    }


    @PostMapping("signup")
    public ResponseEntity<AuthRespone> createUserHandler(@RequestBody User user) throws Exception{

        String email=user.getEmail();
        String password=user.getPassword();
        String firstString = user.getFirstname();
        String lastString = user.getLastname();

        User isEmailExist=userRepository.findByEmail(email);

        if(isEmailExist!=null){
            throw new Exception("Email Already Exist");
        }

        User createdUser=new User();
        createdUser.setEmail(email);
        createdUser.setPassword(passwordEncoder.encode(password));
        createdUser.setFirstname(firstString);
        createdUser.setLastname(lastString);

        User savedUser=userRepository.save(createdUser);
        Cart cart = cartService.createCart(savedUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(), savedUser.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        String token = jwtProvider.generateToken(authentication);

        AuthRespone authRespone = new AuthRespone();
        authRespone.setJwt(token);
        authRespone.setMessage("Signup Success");

        return new ResponseEntity<AuthRespone>(authRespone, HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthRespone> loginUserHandler(@RequestBody LoginRequest loginRequest) throws Exception{

        String username = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        Authentication authentication = authenctiate(username,password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);

        AuthRespone authRespone = new AuthRespone(token,"Signin Sucess");

        return new ResponseEntity<AuthRespone>(authRespone, HttpStatus.CREATED);

    }

    private Authentication authenctiate(String username, String password) {

        UserDetails userDetails = customerUserService.loadUserByUsername(username);
        if (userDetails==null){
            throw new BadCredentialsException("Invalid Username");
        }

        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            throw new BadCredentialsException("Invalid Password");
        }


        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }

}
