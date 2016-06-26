package com.buaa.cloudstore.dao;

import org.springframework.stereotype.Repository;

import com.buaa.cloudstore.dao.base.BaseObjectDaoImpl;
import com.buaa.cloudstore.entity.StoreObject;
@Repository
public class StoreObjectDaoImpl extends BaseObjectDaoImpl<StoreObject> implements StoreObjectDao {
	public StoreObjectDaoImpl() {
		super.setPersistClass(StoreObject.class);
	}
}
