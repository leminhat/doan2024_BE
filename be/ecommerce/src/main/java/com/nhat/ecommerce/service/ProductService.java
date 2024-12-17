package com.nhat.ecommerce.service;

import com.nhat.ecommerce.exception.ProductException;
import com.nhat.ecommerce.model.Product;
import com.nhat.ecommerce.request.CreateProductRequest;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface ProductService {
    public Product createProduct(CreateProductRequest req);

    public String deleteProduct(Long productId) throws ProductException;

    public Product updateProduct(Long productId,CreateProductRequest req) throws ProductException;

    public Product findProductById(Long id) throws ProductException;

    public List<Product> findProductsByCategory();

    public Page<Product> getAllProduct(String category, List<String> colors,List<String>sizes, Integer minPrice,Integer maxPrice,
                                       Integer minDiscount,String sort, String stoke, Integer pageNumber,Integer pageSize );

    public List<Product> findAllProducts();

}
