package com.nhat.ecommerce.repository;

import com.nhat.ecommerce.model.Cart;
import com.nhat.ecommerce.model.CartItem;
import com.nhat.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartRepository extends JpaRepository<Cart,Long> {
    @Query("SELECT c From Cart c where c.user.id =:userId")
    public Cart findByUserId(@Param("userId") Long userId);

}
