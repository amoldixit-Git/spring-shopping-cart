package com.amol.poc.ShoppingCart.model;

public class SalesTaxInfo {
	private String category;
	private double salesTax;
	public SalesTaxInfo(String category, Integer salesTax) {
		this.category = category;
		this.salesTax = salesTax;
	}
	public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public double getSalesTax() {
		return salesTax;
	}
	
	public void setSalesTax(double salesTax) {
		this.salesTax = salesTax;
	}
	
	
}
