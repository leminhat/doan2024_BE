package com.nhat.ecommerce.service;

import com.nhat.ecommerce.model.Address;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AddressService {

    public List<Address> findAddressUser(long Id) ;
}
