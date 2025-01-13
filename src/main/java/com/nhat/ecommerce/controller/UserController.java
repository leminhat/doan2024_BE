package com.nhat.ecommerce.controller;

import com.nhat.ecommerce.exception.UserException;
import com.nhat.ecommerce.model.Address;
import com.nhat.ecommerce.model.User;
import com.nhat.ecommerce.service.AddressService;
import com.nhat.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private  AddressService addressService;

    @GetMapping("/profile")
    public ResponseEntity<User> getUserProfileHandler(@RequestHeader("Authorization") String jwt) throws UserException {

        System.out.println("jwt: " + jwt);
        User user = userService.findUserProfileByJwt(jwt);

        return new ResponseEntity<User>(user, HttpStatus.ACCEPTED);
    }

    @GetMapping("/address")
    public ResponseEntity<List<Address>> getAddressUser(@RequestHeader("Authorization") String jwt) throws Exception {


        User user = userService.findUserProfileByJwt(jwt);
        List<Address> address = addressService.findAddressUser(user.getId());
        return new ResponseEntity<List<Address>>(address, HttpStatus.ACCEPTED);
    }


}
