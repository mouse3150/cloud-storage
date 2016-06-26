package com.buaa.cloudstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buaa.cloudstore.dao.ProductDao;
import com.buaa.cloudstore.dao.base.Page;
import com.buaa.cloudstore.dao.base.QueryProperty;
import com.buaa.cloudstore.entity.Product;

@Service
public class ProductManagerImpl implements ProductManager {
	
	@Autowired
	private ProductDao pruductDao;
	
	public ProductManagerImpl() {
	}
	
	@Override
	public void addProduct(Product product) {
		pruductDao.add(product);
	}
	
	@Override
	public boolean delProduct(Product product) {
		
		pruductDao.delete(product);
		return true;
	}
	
	@Override
	public boolean delProduct(String id) {
		pruductDao.deleteById(id);
		return true;
	}
	
	@Override
	public List<Product> getAllProduct() {
		List<Product> products = pruductDao.list();
		return products;
	}
	
	@Override
	public Page<Product> getPageProduct(Page<Product> format) {
		
		Page<Product> page = pruductDao.listPage(format);
		return page;
	}
	
	@Override
	public Page<Product> getPageProduct(Page<Product> format, QueryProperty qp) {
		Page<Product> page = pruductDao.listPageByProperty(format, qp);
		return page;
	}
	
	@Override
	public Page<Product> getPageProduct(Page<Product> format, List<QueryProperty> qps) {
		Page<Product> page = pruductDao.listPageByProperties(format, qps);
		return page;
	}
	
	@Override
	public Product getProduct(String id) {
		Product product = pruductDao.getById(id);
		return product;
	}
	
	@Override
	public boolean updateProduct(Product product) {
		
		pruductDao.update(product);
		return true;
	}
	
	
}
