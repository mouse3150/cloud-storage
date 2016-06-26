package com.buaa.cloudstore.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buaa.cloudstore.dao.UserDao;
import com.buaa.cloudstore.entity.User;

@Service
public class UserManagerImpl implements UserManager {
	@Autowired
	private UserDao userDao2;
	
	public void setUserDao2(UserDao userDao2) {
		this.userDao2 = userDao2;
	}

	@Override
	public User getUser(String id) {
		return userDao2.getUser(id);
	}

	@Override
	public List<User> getAllUser() {
		return userDao2.getAllUser();
	}

	@Override
	public void addUser(User user) {
		userDao2.addUser(user);
	}

	@Override
	public boolean delUser(String id) {
		return userDao2.delUser(id);
	}

	@Override
	public boolean updateUser(User user) {
		return userDao2.updateUser(user);
	}

}