package com.amol.poc.ShoppingCart.service;

import java.util.List;

import com.amol.poc.ShoppingCart.entity.Product;
import com.amol.poc.ShoppingCart.entity.SalesTax;

public interface DataService {
	
//	public List<Product> queryProducts(String likeName);

	public Product getProductByCode(String code);

	public List<Product> getProductList();
	
	public List<SalesTax> getSalesTaxList();

	public SalesTax findSalesTax(String code);

}
