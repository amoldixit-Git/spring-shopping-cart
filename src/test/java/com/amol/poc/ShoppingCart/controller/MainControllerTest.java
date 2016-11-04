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
import com.amol.poc.ShoppingCart.model.CartInfo;
import com.amol.poc.ShoppingCart.model.CartLineInfo;
import com.amol.poc.ShoppingCart.model.ProductInfo;
import com.amol.poc.ShoppingCart.service.ProductService;
import com.amol.poc.ShoppingCart.service.SalesTaxService;
@WebAppConfiguration
@ContextConfiguration (classes = {ApplicationContextConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class MainControllerTest {
	@Autowired
	private WebApplicationContext wac;
	private MockMvc mockMvc;
	@Autowired
	private ProductService productServiceMock;
	@Autowired
	private SalesTaxService salesTaxServiceMock;

    private MainController mainController;
	private static ProductInfo productInfoOne;
	private static ProductInfo productInfoTwo;
	private static CartInfo cartInfo;

    public MainControllerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    	productInfoOne = new ProductInfo("PD1", "ProductOne","A", 100);
    	productInfoTwo = new ProductInfo("PD2", "ProductTwo","B", 100);
        
        cartInfo = new CartInfo();
        cartInfo.addProduct(productInfoOne, 10);
        cartInfo.addProduct(productInfoTwo, 10);
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    	this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    	mainController = new MainController();
    	productServiceMock = mock(ProductService.class);
    	salesTaxServiceMock = mock(SalesTaxService.class);
        ReflectionTestUtils.setField(mainController, "productService", productServiceMock);
        ReflectionTestUtils.setField(mainController, "salesTaxService", salesTaxServiceMock);
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
		List productList = (List<ProductInfo>)mvcResult.getModelAndView().getModel().get("listOfProducts");
		ProductInfo result = (ProductInfo) productList.get(0);
        assertEquals(result, productInfoOne);
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
