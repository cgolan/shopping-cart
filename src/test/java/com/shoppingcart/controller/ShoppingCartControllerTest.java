package com.shoppingcart.controller;

import com.shoppingcart.service.ProductService;
import com.shoppingcart.service.ShoppingCartService;
import com.shoppingcart.wrapper.ProductWrapper;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class ShoppingCartControllerTest {

    @Mock
    private ShoppingCartService shoppingCartService;

    @Mock
    private ProductService productService;

    @InjectMocks
    private ShoppingCartController testSubject;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(testSubject).build();
    }

    @Test
    public void shouldReturnASuccessfulResponseCode() throws Exception {
        when(productService.displayStock()).thenReturn(new ArrayList<>());
        mockMvc.perform(get("/showAll")).andExpect(status().isOk());
        verify(productService).displayStock();
    }

    @Test
    public void shouldReturnASuccessfulPostResponseCodeAfterCheckout() throws Exception {
        when(shoppingCartService.getReceipt(any(MockHttpServletRequest.class))).thenReturn(new ProductWrapper());
        mockMvc.perform(post("/checkout")).andExpect(status().isOk());
        verify(shoppingCartService).getReceipt(any(MockHttpServletRequest.class));
    }

}