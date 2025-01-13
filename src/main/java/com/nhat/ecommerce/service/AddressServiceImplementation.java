package com.nhat.ecommerce.service;

import com.nhat.ecommerce.model.Address;
import com.nhat.ecommerce.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImplementation implements AddressService {

    @Autowired
    AddressRepository addressRepository;

    public AddressServiceImplementation(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public List<Address> findAddressUser(long Id) {
      return   addressRepository.findAddressUser(Id);
    }
}
