package com.amol.poc.ShoppingCart.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.amol.poc.ShoppingCart.entity.Product;
import com.amol.poc.ShoppingCart.entity.SalesTax;
import com.amol.poc.ShoppingCart.service.DataService;

public class DataServiceImpl implements DataService{

	@Autowired
	private SessionFactory sessionFactory;

	/*public List<Product> queryProducts(String likeName) {
		String sql = "Select new " + Product.class.getName() //
                + "(p.code, p.name, p.category, p.price) from "//
                + Product.class.getName() + " p ";
        if (likeName != null && likeName.length() > 0) {
            sql += " Where lower(p.name) like :likeName ";
        }
        
		
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(sql);
        if (likeName != null && likeName.length() > 0) {
            query.setParameter("likeName", "%" + likeName.toLowerCase() + "%");
            
        }
        return (List<Product>)query.list();
	}*/

	public Product getProductByCode(String code) {
        Session session = sessionFactory.getCurrentSession();
        Criteria crit = session.createCriteria(Product.class);
        crit.add(Restrictions.eq("code", code));
        return (Product) crit.uniqueResult();
	}

	public List<Product> getProductList() {
		Session session = sessionFactory.getCurrentSession();
		return (List<Product>)session.createCriteria(Product.class).list();
	}
	
	public List<SalesTax> getSalesTaxList() {
		Session session = sessionFactory.getCurrentSession();
		return (List<SalesTax>)session.createCriteria(SalesTax.class).list();
	}

	public SalesTax findSalesTax(String code) {
		Session session = sessionFactory.getCurrentSession();
        Criteria crit = session.createCriteria(SalesTax.class);
        crit.add(Restrictions.eq("category", code));
        return (SalesTax) crit.uniqueResult();
	}
}
