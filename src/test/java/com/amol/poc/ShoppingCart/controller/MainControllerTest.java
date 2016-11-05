package com.amol.poc.ShoppingCart.controller;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import com.amol.poc.ShoppingCart.config.ApplicationContextConfig;
import com.amol.poc.ShoppingCart.entity.Product;
import com.amol.poc.ShoppingCart.model.CartInfo;
import com.amol.poc.ShoppingCart.model.CartLineInfo;
import com.amol.poc.ShoppingCart.service.DataService;
@WebAppConfiguration
@ContextConfiguration (classes = {ApplicationContextConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class MainControllerTest {
	@Autowired
	private WebApplicationContext wac;
	private MockMvc mockMvc;
	@Autowired
	private DataService dataServiceMock;

    private MainController mainController;
	private static Product productOne;
	private static Product productTwo;
	private static CartInfo cartInfo;

    public MainControllerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    	productOne = new Product("PD1", "ProductOne","A", 100.00);
    	productTwo = new Product("PD2", "ProductTwo","B", 100.00);
        
        cartInfo = new CartInfo();
        cartInfo.addProduct(productOne, 10);
        cartInfo.addProduct(productTwo, 10);
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    	this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    	mainController = new MainController();
    	dataServiceMock = mock(DataService.class);
        ReflectionTestUtils.setField(mainController, "dataService", dataServiceMock);
    }

    @After
    public void tearDown() {

    }

    @Test
    public void listProductHandlerTest() throws Exception {
        
		MvcResult mvcResult  = mockMvc.perform(get("/productList"))
                .andExpect(status().isOk())
                .andExpect(view().name("productList"))
                .andExpect(model().attributeExists("listOfProducts"))
                .andReturn();
		List<Product> productList = (List<Product>)(mvcResult.getModelAndView().getModel().get("listOfProducts"));
		Product result = (Product) productList.get(0);
        assertEquals(result, productOne);
    }
    
    @Test
    public void buyProductHandlerTest() throws Exception {
    	mockMvc.perform(get("/buyProduct")
				.param("code", "PD1"))
				.andExpect(view().name("redirect:/shoppingCart"));
    }
    
    @Test
    public void shoppingCartConfirmationReviewHandlerTest() throws Exception {
    	
    	MvcResult mvcResult = mockMvc.perform(get("/shoppingCartConfirmation")
    			.sessionAttr("myCart", cartInfo))
                .andExpect(view().name("shoppingCartConfirmation"))
                .andExpect(model().attributeExists("cartForm"))
                .andExpect(model().attributeExists("summary"))
    		.andReturn();
    	CartInfo resultCartInfo = (CartInfo)mvcResult.getModelAndView().getModel().get("cartForm");
    	assertEquals(2, resultCartInfo.getCartLines().size());
    	CartLineInfo resultCartLineInfo = (CartLineInfo) resultCartInfo.getCartLines().get(0);
    	assertEquals(1100.00, resultCartLineInfo.getSubTotal(),0.01);
    }

}
