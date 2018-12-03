package com.shopingcart.service.impl;

import com.shoppingcart.service.impl.ShoppingCartServiceImpl;
import com.shoppingcart.wrapper.ProductWrapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

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
        request.addParameter("apples", "1");
        request.addParameter("oranges", "1");
        request.addParameter("bananas", "1");
        request.addParameter("papayas", "1");

        ProductWrapper receipt = service.getReceipt(request);
        assertEquals(Long.parseLong(request.getParameter("apples")), receipt.getProductsMap().get("Apple").getQuantity());
        assertEquals(Long.parseLong(request.getParameter("oranges")), receipt.getProductsMap().get("Orange").getQuantity());
        assertEquals(Long.parseLong(request.getParameter("bananas")), receipt.getProductsMap().get("Banana").getQuantity());
        assertEquals(Long.parseLong(request.getParameter("papayas")), receipt.getProductsMap().get("Papaya").getQuantity());
    }


    @Test
    public void testWhenThreePapayasInTheCart() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addParameter("papayas", "3");

        ProductWrapper receipt = service.getReceipt(request);
        assertEquals(Long.parseLong(request.getParameter("papayas")), receipt.getProductsMap().get("Papaya").getQuantity());
    }

}