package com.nhat.ecommerce.service;

import com.nhat.ecommerce.exception.ProductException;
import com.nhat.ecommerce.model.Product;
import com.nhat.ecommerce.request.CreateProductRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    public Product createProduct(CreateProductRequest req);

    public String deleteProduct(Long productId) throws ProductException;

    public Product updateProduct(Long productId,Product req) throws ProductException;

    public Product findProductById(Long id) throws ProductException;

    public List<Product> findProductsByCategory();

    public Page<Product> getAllProduct(String category, List<String> colors,List<String>sizes, Integer page, Integer minPrice,Integer maxPrice,
                                       Integer minDiscount,String sort, String stoke, Integer pageNumber,Integer pageSize );



}
