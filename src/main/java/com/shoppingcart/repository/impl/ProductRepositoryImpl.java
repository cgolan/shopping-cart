package com.shoppingcart.repository.impl;

import com.shoppingcart.model.Product;
import com.shoppingcart.repository.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.unmodifiableList;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    @Override
    public List<Product> getAllProducts() {
        return getCurrentStock();
    }

    //DB simulation
    private List<Product> getCurrentStock() {
        Product apple = new Product("Apple", 25);
        Product orange = new Product("Orange", 30);
        Product banana = new Product("Banana", 15);
        Product papaya = new Product("Papaya", 50);
        return unmodifiableList(Arrays.asList(apple, orange, banana, papaya));
    }
}
