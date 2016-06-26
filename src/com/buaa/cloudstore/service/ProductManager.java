package com.buaa.cloudstore.service;

import java.util.List;

import com.buaa.cloudstore.dao.base.Page;
import com.buaa.cloudstore.dao.base.QueryProperty;
import com.buaa.cloudstore.entity.Product;

public interface ProductManager {
	
	public Product getProduct(String id);

	public void addProduct(Product product);

	public List<Product> getAllProduct();

	public Page<Product> getPageProduct(Page<Product> format);
	
	public Page<Product> getPageProduct(Page<Product> format, QueryProperty qp);
	
	public Page<Product> getPageProduct(Page<Product> format, List<QueryProperty> qps);

	public boolean delProduct(String id);

	public boolean delProduct(Product product);

	public boolean updateProduct(Product product);
}
