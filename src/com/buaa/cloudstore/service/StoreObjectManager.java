package com.buaa.cloudstore.service;

import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.buaa.cloudstore.dao.base.Page;
import com.buaa.cloudstore.dao.base.QueryProperty;
import com.buaa.cloudstore.entity.StoreObject;

public interface StoreObjectManager {
	public List<StoreObject> list();
	
	public void upload(StoreObject so, InputStream is);
	
	public void download(String id,  HttpServletResponse response);
	
	public Page<StoreObject> getPage(Page<StoreObject> format);
	
	public Page<StoreObject> getPage(Page<StoreObject> format, QueryProperty qp);
	
	public Page<StoreObject> getPage(Page<StoreObject> format, List<QueryProperty> qps);

	public boolean delete(String id);

	public boolean delete(StoreObject object);

	public boolean update(StoreObject object);
}
