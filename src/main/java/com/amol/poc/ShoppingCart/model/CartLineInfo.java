package com.amol.poc.ShoppingCart.model;

public class CartLineInfo {
	private ProductInfo productInfo;
    private int quantity;
    private double salesTax;
    private double subTotal;
    private double totalSalesTax;
    public CartLineInfo() {
    	this.quantity = 0;
    	this.salesTax = 0;
    	this.subTotal = 0;
    	this.totalSalesTax = 0;
    }
	public ProductInfo getProductInfo() {
		return productInfo;
	}
	
	public void setProductInfo(ProductInfo productInfo) {
		this.productInfo = productInfo;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public double getSalesTax() {
		return salesTax;
	}
	public void setSalesTax(double salesTax) {
		this.salesTax = salesTax;
	}

	public double getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(double totalCost) {
		this.subTotal = totalCost;
	}
	public double getTotalSalesTax() {
		return totalSalesTax;
	}
	public void setTotalSalesTax(double totalSalesTax) {
		this.totalSalesTax = totalSalesTax;
	}
	
}
