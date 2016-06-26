package com.buaa.cloudstore.dao;

import org.springframework.stereotype.Repository;

import com.buaa.cloudstore.dao.base.BaseObjectDaoImpl;
import com.buaa.cloudstore.entity.Consumer;

@Repository
public class ConsumerDaoImpl extends BaseObjectDaoImpl<Consumer> implements ConsumerDao {
	public ConsumerDaoImpl() {
		super.setPersistClass(Consumer.class);
	}
}
