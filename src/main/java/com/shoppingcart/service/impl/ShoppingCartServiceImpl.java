package com.shoppingcart.service.impl;

import com.shoppingcart.model.Product;
import com.shoppingcart.service.ShoppingCartService;
import com.shoppingcart.wrapper.ProductWrapper;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Override
    public ProductWrapper getReceipt(HttpServletRequest request) {
        ProductWrapper receipt = new ProductWrapper();
        Map<String, Product> productsMap = computeMap(request);
        receipt.setProductsMap(productsMap);
        receipt.setTotal(getTotalPayment(productsMap));
        return receipt;
    }

    private Map<String, Product> computeMap(HttpServletRequest request) {
        Map<String, Product> productsMap = new HashMap<>();
        String apples = request.getParameter("apples");
        String oranges = request.getParameter("oranges");
        String bananas = request.getParameter("bananas");
        String papayas = request.getParameter("papayas");
        populateMap(productsMap, apples, oranges, bananas, papayas);
        return productsMap;
    }

    private void populateMap(Map<String, Product> productsMap, String apples, String oranges, String bananas, String papayas) {
        if (apples != null && Long.parseLong(apples) > 0) {
            Product product = new Product("Apple", 25, Long.parseLong(apples));
            productsMap.put(product.getName(), product);
        }

        if (oranges != null && Long.parseLong(oranges) > 0) {
            Product product = new Product("Orange", 30, Long.parseLong(oranges));
            productsMap.put(product.getName(), product);
        }

        if (bananas != null && Long.parseLong(bananas) > 0) {
            Product product = new Product("Bananas", 15, Long.parseLong(bananas));
            productsMap.put(product.getName(), product);
        }
        if (papayas != null && Long.parseLong(papayas) > 0) {
            Product product = new Product("Papaya", 50, Long.parseLong(papayas));
            productsMap.put(product.getName(), product);
        }
    }

    private BigDecimal getTotalPayment(Map<String, Product> map) {
        List<Long> price = new ArrayList<>();
        map.forEach(
                (key, value) -> {
                    if ("Papaya".equals(value.getName())) {
                        long papayasAmount;
                        if (value.getQuantity() < 3) {
                            papayasAmount = value.getQuantity() * value.getPrice();
                            price.add(papayasAmount);
                        } else {
                            long quotient = value.getQuantity() / 3 * 2 * value.getPrice();
                            long remainder = value.getQuantity() % 3 * value.getPrice();
                            papayasAmount = quotient + remainder;
                            price.add(papayasAmount);
                        }
                    } else
                        price.add(value.getQuantity() * value.getPrice());
                }
        );
        long totalAmount = price.stream().mapToLong(Long::longValue).sum();
        return new BigDecimal(totalAmount).movePointLeft(2);
    }
}
