package com.shoppingcart.service;

import com.shoppingcart.wrapper.ProductWrapper;

import javax.servlet.http.HttpServletRequest;

public interface ShoppingCartService {
    ProductWrapper getReceipt(HttpServletRequest request);
}
