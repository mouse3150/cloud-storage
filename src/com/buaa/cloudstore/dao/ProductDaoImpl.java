package com.buaa.cloudstore.dao;

import org.springframework.stereotype.Repository;

import com.buaa.cloudstore.dao.base.BaseObjectDaoImpl;
import com.buaa.cloudstore.entity.Product;

@Repository
public class ProductDaoImpl extends BaseObjectDaoImpl<Product> implements ProductDao {
	public ProductDaoImpl() {
		super.setPersistClass(Product.class);
	}
}
