package com.buaa.cloudstore.service;

import java.util.List;

import com.buaa.cloudstore.dao.base.Page;
import com.buaa.cloudstore.entity.Consumer;


public interface ConsumerManager {
	
	public Consumer getConsumer(String id);
	
	public Consumer getConsumerByName(String name);
	
	public Consumer getConsumerByEmail(String email);
	
	public void addConsumer(Consumer consumer);
	
	public List<Consumer> getAllConsumer();
	
	public Page<Consumer> getPageConsumer(Page<Consumer> format);
	
	public boolean delConsumer(String id);
	
	public boolean delConsumer(Consumer consumer);
	
	public boolean updateConsumer(Consumer consumer);
	
	 public void changePassword(String id, String newPassword);
	
}
