package com.buaa.cloudstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buaa.cloudstore.dao.ConsumerDao;
import com.buaa.cloudstore.dao.base.Page;
import com.buaa.cloudstore.dao.base.QueryProperty;
import com.buaa.cloudstore.entity.Consumer;

@Service
public class ConsumerManagerImpl implements ConsumerManager {
	
	@Autowired
	private  ConsumerDao consumerDao;
	
	@Autowired
	private  PasswordHandler passwordHandler;
	
	public ConsumerManagerImpl() {
		
	}
	
	@Override
	public void addConsumer(Consumer consumer) {
		passwordHandler.encryptPassword(consumer);
		consumerDao.add(consumer);
	}
	
	  /**
     * 修改密码
     * @param id
     * @param newPassword
     */
	@Override
    public void changePassword(String id, String newPassword) {
    	Consumer consumer =consumerDao.getById(id);
    	consumer.setPassword(newPassword);
    	passwordHandler.encryptPassword(consumer);
    	updateConsumer(consumer);
    }
	
	@Override
	public boolean delConsumer(String id) {
		consumerDao.deleteById(id);
		return true;
	}
	
	@Override
	public boolean delConsumer(Consumer consumer) {
		consumerDao.delete(consumer);
		return true;
	}
	
	@Override
	public boolean updateConsumer(Consumer consumer) {
		consumerDao.update(consumer);
		return true;
	}
	
	@Override
	public List<Consumer> getAllConsumer() {
		return consumerDao.list();
	}
	
	@Override
	public Consumer getConsumer(String id) {
		return consumerDao.getById(id);
	}
	
	@Override
	public Page<Consumer> getPageConsumer(Page<Consumer> format) {
		Page<Consumer> page = consumerDao.listPage(format);
		return page;
	}

	public ConsumerDao getConsumerDao() {
		return consumerDao;
	}

	public void setConsumerDao(ConsumerDao consumerDao) {
		this.consumerDao = consumerDao;
	}
	
	@Override
	public Consumer getConsumerByName(String name) {
		if(name == null) {
			return null;
		}
		QueryProperty qp = new QueryProperty();
		qp.setHandleType("=");
		qp.setPropertyName("name");
		qp.setPropertyValue(name);
		
		List<Consumer> consumers = consumerDao.listByProperty(qp);
		if (!consumers.isEmpty()) {
			return consumers.get(0);
		}
		return null;
	}
	
	@Override
	public Consumer getConsumerByEmail(String email) {
		if(email == null) {
			return null;
		}
		QueryProperty qp = new QueryProperty();
		qp.setHandleType("=");
		qp.setPropertyName("email");
		qp.setPropertyValue(email);
		
		List<Consumer> consumers = consumerDao.listByProperty(qp);
		if (!consumers.isEmpty()) {
			return consumers.get(0);
		}
		return null;
	}
}
