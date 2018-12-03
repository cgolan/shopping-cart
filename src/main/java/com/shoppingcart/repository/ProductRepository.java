package com.shoppingcart.repository;

import com.shoppingcart.model.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> getAllProducts();
}
