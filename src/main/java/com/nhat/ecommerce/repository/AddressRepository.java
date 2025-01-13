package com.nhat.ecommerce.repository;

import com.nhat.ecommerce.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AddressRepository  extends JpaRepository<Address, Long> {

    @Query("SELECT a FROM Address a where a.user.id = :userId")
    public List<Address> findAddressUser(@Param("userId") long userId);

    @Query("SELECT a FROM Address a where a.id = :id")
    public Address findAddressById(Long id);
}
