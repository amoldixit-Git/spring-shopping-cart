package com.amol.poc.ShoppingCart.model;

public class Summary {
	Double totalAmount;
	Double totalSalesTax;
	Double totalCost;
	public Summary(Double totalAmount, Double totalSalesTax) {
		this.totalAmount = totalAmount;
		this.totalSalesTax = totalSalesTax;
		this.totalCost = totalAmount +  totalSalesTax;
	}
	public Summary() {
		totalAmount = 0.0;
		totalSalesTax = 0.0;
	}
	public Double getTotalAmount() {
		return totalAmount;
	}
	
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	public Double getTotalSalesTax() {
		return totalSalesTax;
	}
	
	public void setTotalSalesTax(Double totalSalesTax) {
		this.totalSalesTax = totalSalesTax;
	}
	public Double getTotalCost() {
		return totalCost;
	}
	
	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
	}
	
	

}
