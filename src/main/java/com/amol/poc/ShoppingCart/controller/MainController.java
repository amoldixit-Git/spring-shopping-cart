package com.amol.poc.ShoppingCart.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.amol.poc.ShoppingCart.entity.Product;
import com.amol.poc.ShoppingCart.model.CartInfo;
import com.amol.poc.ShoppingCart.model.CartLineInfo;
import com.amol.poc.ShoppingCart.model.ProductInfo;
import com.amol.poc.ShoppingCart.model.Summary;
import com.amol.poc.ShoppingCart.service.ProductService;
import com.amol.poc.ShoppingCart.service.SalesTaxService;
import com.amol.poc.ShoppingCart.util.Utils;

@Controller
// Enable Hibernate Transaction.
@Transactional
// Need to use RedirectAttributes
@EnableWebMvc
public class MainController {

	@Autowired
	private ProductService productService;
	@Autowired
	private SalesTaxService salesTaxService;

	@InitBinder
	public void myInitBinder(WebDataBinder dataBinder) {
		Object target = dataBinder.getTarget();
		if (target == null) {
			return;
		}
		System.out.println("Target=" + target);
		// For Cart Form.
		// (@ModelAttribute("cartForm") @Validated CartInfo cartForm)
		if (target.getClass() == CartInfo.class) {

		}
	}
	
	@RequestMapping("/")
    public String home() {
        return "index";
    }
 
    // Product List page.
    @RequestMapping({"/productList"})
    public String listProductHandler(Model model, //
            @RequestParam(value = "name", defaultValue = "") String likeName) {
 
        List<ProductInfo> result = productService.queryProducts(likeName);
 
        model.addAttribute("listOfProducts", result);
        return "productList";
    }
    
    @RequestMapping({ "/buyProduct" })
    public String buyProductHandler(HttpServletRequest request, Model model, //
            @RequestParam(value = "code", defaultValue = "") String code) {
 
        Product product = null;
        if (code != null && code.length() > 0) {
            product = productService.findProduct(code);
        }
        if (product != null) {
 
            // Cart info stored in Session.
            CartInfo cartInfo = Utils.getCartInSession(request);
 
            ProductInfo productInfo = new ProductInfo(product);
 
            cartInfo.addProduct(productInfo, 1);
        }
        // Redirect to shoppingCart page.
        return "redirect:/shoppingCart";
    }
 
    @RequestMapping({ "/shoppingCartRemoveProduct" })
    public String removeProductHandler(HttpServletRequest request, Model model, //
            @RequestParam(value = "code", defaultValue = "") String code) {
        Product product = null;
        if (code != null && code.length() > 0) {
            product = productService.findProduct(code);
        }
        if (product != null) {
 
            // Cart Info stored in Session.
            CartInfo cartInfo = Utils.getCartInSession(request);
 
            ProductInfo productInfo = new ProductInfo(product);
 
            cartInfo.removeProduct(productInfo);
 
        }
        // Redirect to shoppingCart page.
        return "redirect:/shoppingCart";
    }
 
    // POST: Update quantity of products in cart.
    @RequestMapping(value = { "/shoppingCart" }, method = RequestMethod.POST)
    public String shoppingCartUpdateQty(HttpServletRequest request, //
            Model model, //
            @ModelAttribute("cartForm") CartInfo cartForm) {
 
        CartInfo cartInfo = Utils.getCartInSession(request);
        cartInfo.updateQuantity(cartForm);
 
        // Redirect to shoppingCart page.
        return "redirect:/shoppingCart";
    }
 
    // GET: Show Cart
    @RequestMapping(value = { "/shoppingCart" }, method = RequestMethod.GET)
    public String shoppingCartHandler(HttpServletRequest request, Model model) {
        CartInfo myCart = Utils.getCartInSession(request);
 
        model.addAttribute("cartForm", myCart);
        return "shoppingCart";
    }
 
    // GET: Review Cart to confirm.
    @RequestMapping(value = { "/shoppingCartConfirmation" }, method = RequestMethod.GET)
    public String shoppingCartConfirmationReviewHandler(HttpServletRequest request, Model model) {
        CartInfo cartInfo = Utils.getCartInSession(request);
 
        // Cart have no products.
        if (cartInfo.isEmpty()) {
            // Redirect to shoppingCart page.
            return "redirect:/shoppingCart";
        }
        Double total = 0.0;
        Double tax = 0.0;
        for (CartLineInfo cartlineInfo : cartInfo.getCartLines()){
        	double salesTax = salesTaxService.findSalesTaxInfo(cartlineInfo.getProductInfo().getCategory()).getSalesTax();
        	double price = cartlineInfo.getProductInfo().getPrice();
			int quantity = cartlineInfo.getQuantity();
			
			cartlineInfo.setSalesTax(salesTax);
			double calcSalesTax = Utils.calcSalesTax(salesTax, price, quantity);
			cartlineInfo.setTotalSalesTax(calcSalesTax);
			double calcAmount = Utils.calcSubAmount(price, quantity);
			cartlineInfo.setSubTotal(calcAmount + calcSalesTax);
        	
			total += calcAmount;
			tax += cartlineInfo.getTotalSalesTax();
        }
        Summary summary = new Summary(total,tax);
        
        model.addAttribute("cartForm", cartInfo);
        model.addAttribute("summary", summary);
        return "shoppingCartConfirmation";
    }
}

