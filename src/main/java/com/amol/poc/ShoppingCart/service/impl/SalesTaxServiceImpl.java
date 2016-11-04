package com.amol.poc.ShoppingCart.service.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.amol.poc.ShoppingCart.entity.SalesTax;
import com.amol.poc.ShoppingCart.model.SalesTaxInfo;
import com.amol.poc.ShoppingCart.service.SalesTaxService;

public class SalesTaxServiceImpl implements SalesTaxService {
	@Autowired
	private SessionFactory sessionFactory;

	public List list() {
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(SalesTax.class).list();
	}

	public SalesTaxInfo findSalesTaxInfo(String code) {
		SalesTax salesTax = this.findSalesTax(code);
        if (salesTax == null) {
            return null;
        }
        return new SalesTaxInfo(salesTax.getCategory(),salesTax.getTaxPercentage());
	}

	public SalesTax findSalesTax(String code) {
		Session session = sessionFactory.getCurrentSession();
        Criteria crit = session.createCriteria(SalesTax.class);
        crit.add(Restrictions.eq("category", code));
        return (SalesTax) crit.uniqueResult();
	}

}
