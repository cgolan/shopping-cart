package com.shoppingcart.wrapper;

import com.shoppingcart.model.Product;

import java.math.BigDecimal;
import java.util.Map;

public class ProductWrapper {

    private Map<String, Product> productsMap;
    private BigDecimal total;

    public Map<String, Product> getProductsMap() {
        return productsMap;
    }

    public void setProductsMap(Map<String, Product> productsMap) {
        this.productsMap = productsMap;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
