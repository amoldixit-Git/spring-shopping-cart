package com.amol.poc.ShoppingCart.service.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.amol.poc.ShoppingCart.entity.Product;
import com.amol.poc.ShoppingCart.model.ProductInfo;
import com.amol.poc.ShoppingCart.service.ProductService;

public class ProductServiceImpl implements ProductService{

	@Autowired
	private SessionFactory sessionFactory;

	public List<ProductInfo> queryProducts(String likeName) {
		String sql = "Select new " + ProductInfo.class.getName() //
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
        return (List<ProductInfo>)query.list();
	}

	
	
    public ProductInfo findProductInfo(String code) {
        Product product = this.findProduct(code);
        if (product == null) {
            return null;
        }
        return new ProductInfo(product.getCode(), product.getName(), product.getCategory(), product.getPrice());
    }

	public Product findProduct(String code) {
        Session session = sessionFactory.getCurrentSession();
        Criteria crit = session.createCriteria(Product.class);
        crit.add(Restrictions.eq("code", code));
        return (Product) crit.uniqueResult();
	}

	public List list() {
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Product.class).list();
	}








}
