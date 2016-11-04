package com.amol.poc.ShoppingCart.model;

import com.amol.poc.ShoppingCart.entity.Product;

public class ProductInfo {
	private String code;
	private String name;
	private double price;
	private String category;

	public ProductInfo() {

	}

	public ProductInfo(String code, String name, String category, double price) {
		this.code = code;
		this.name = name;
		this.price = price;
		this.category = category;
	}

	public ProductInfo(Product product) {
		this.code = product.getCode();
		this.name = product.getName();
		this.price = product.getPrice();
		this.category = product.getCategory();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj == null || obj.getClass() != this.getClass())
			return false;
		ProductInfo other = (ProductInfo) obj;
		return code.equals(other.getCode()) && 
				name.equals(other.name) && 
				price == other.price && 
				category.equals(other.category);
	}
}
