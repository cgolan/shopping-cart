package com.shoppingcart.service.impl;

import com.shoppingcart.wrapper.ProductWrapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import java.util.Map;

import static java.util.Objects.requireNonNull;
import static org.junit.Assert.assertEquals;

public class ShoppingCartServiceImplTest {

    private ShoppingCartServiceImpl service;

    @Before
    public void setUp() {
        service = new ShoppingCartServiceImpl();
    }

    @Test
    public void shouldReturnAFullShoppingCart() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addParameter("Apple", "1");
        request.addParameter("Orange", "1");
        request.addParameter("Banana", "1");
        request.addParameter("Papaya", "1");

        ProductWrapper receipt = service.getReceipt(request);
        assertEquality(request, receipt);
    }

    @Test
    public void testWhenThreePapayasInTheCart() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addParameter("Papaya", "3");

        ProductWrapper receipt = service.getReceipt(request);
        assertEquals(Long.parseLong(requireNonNull(request.getParameter("Papaya"))), receipt.getProductsMap().get("Papaya").getQuantity());
        assertEquals(100L, receipt.getProductsMap().get("Papaya").getSubTotal());
    }

    private void assertEquality(MockHttpServletRequest request, ProductWrapper receipt) {
        Map<String, String[]> map = request.getParameterMap();
        map.forEach(
                (key, value) ->
                {
                    if (value.length > 0)
                        assertEquals(Long.parseLong(value[0]), receipt.getProductsMap().get(key).getQuantity());
                }
        );
    }
}