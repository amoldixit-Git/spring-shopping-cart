package com.amol.poc.ShoppingCart.service;

import java.util.List;

import com.amol.poc.ShoppingCart.entity.Product;
import com.amol.poc.ShoppingCart.model.ProductInfo;

public interface ProductService {
	
	public List<ProductInfo> queryProducts(String likeName);

	public ProductInfo findProductInfo(String code);

	public Product findProduct(String code);

	public List list();

}
