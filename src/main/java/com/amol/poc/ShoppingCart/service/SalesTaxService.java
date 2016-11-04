package com.amol.poc.ShoppingCart.service;

import java.util.List;

import com.amol.poc.ShoppingCart.model.SalesTaxInfo;

public interface SalesTaxService {
	public List list();

	public SalesTaxInfo findSalesTaxInfo(String code);

}
