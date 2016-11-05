package com.amol.poc.ShoppingCart.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "product")
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;
	private String code;
    private String name;
    private double price;
    private String category;
    
    public Product() {
    }
    
    public Product(String code, String name, String category, double price) {
    	this.code= code;
    	this.name=name;
    	this.category=category;
    	this.price=price;
	}

	@Id
    @Column(name = "code", length = 20, nullable = false)
    public String getCode() {
        return code;
    }
 
    public void setCode(String code) {
        this.code = code;
    }
 
    @Column(name = "name", length = 20, nullable = false)
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    @Column(name = "price", nullable = false)
    public double getPrice() {
        return price;
    }
 
    public void setPrice(double price) {
        this.price = price;
    }

    @Column(name = "category", length = 1, nullable = false)
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
		Product other = (Product) obj;
		return code.equals(other.getCode()) && 
				name.equals(other.name) && 
				price == other.price && 
				category.equals(other.category);
	}

}
