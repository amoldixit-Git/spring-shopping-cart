package com.amol.poc.ShoppingCart.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="taxcategory")
public class SalesTax {
	private String category;
	private Integer taxPercentage;
	public SalesTax() {
	}
	@Id
	@Column(name = "category", length = 1, nullable = false)
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	@Column(name = "salestax", nullable = false)
	public Integer getTaxPercentage() {
		return taxPercentage;
	}
	public void setTaxPercentage(Integer taxPercentage) {
		this.taxPercentage = taxPercentage;
	}
	

}
