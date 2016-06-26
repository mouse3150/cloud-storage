package com.buaa.cloudstore.store.swift;

import java.io.File;
import java.io.InputStream;

import org.javaswift.joss.client.factory.AccountFactory;
import org.javaswift.joss.client.factory.AuthenticationMethod;
import org.javaswift.joss.model.Account;
import org.javaswift.joss.model.Container;
import org.javaswift.joss.model.StoredObject;
import org.springframework.stereotype.Service;

import com.buaa.cloudstore.store.IStore;
import com.buaa.cloudstore.store.StoreException;
import com.buaa.cloudstore.store.StoreObjectInfo;
import com.buaa.cloudstore.store.StoreObjectNotFoundException;
@Service("store")
public class SwiftStore implements IStore {
	
	private static Container container;
	
	static {
		Account account = new AccountFactory()
        .setUsername("swift")
        .setPassword("123456")
        .setTenantName("service")
        .setTenantId("c5ab734274eb479db3c02fa3eadfe80e")
        .setAuthUrl("http://controller:5000/v2.0/tokens")
        .setAuthenticationMethod(AuthenticationMethod.KEYSTONE)
        .createAccount();
		
		container = account.getContainer("mycontainer");
	}

	@Override
	public void cancel(String arg0, String arg1) throws StoreException {
		// TODO Auto-generated method stub

	}

	@Override
	public void commit(String arg0, String arg1) throws StoreException {
		// TODO Auto-generated method stub

	}

	@Override
	public String create(String arg0) throws StoreException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String arg0) throws StoreException, StoreObjectNotFoundException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteTree(String arg0) throws StoreException, StoreObjectNotFoundException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean exists(String arg0) throws StoreException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public InputStream getAsInputStream(String path) throws StoreException, StoreObjectNotFoundException {
		StoredObject object = container.getObject(path);
		
		return object.downloadObjectAsInputStream();
	}

	@Override
	public Object getAsObject(String arg0) throws StoreException, StoreObjectNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StoreObjectInfo getInfo(String arg0) throws StoreException, StoreObjectNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getParts(String arg0, String arg1) throws StoreException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUrl(String arg0) throws StoreException, StoreObjectNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] keys(String arg0) throws StoreException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long length(String arg0) throws StoreException, StoreObjectNotFoundException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void put(String arg0, Object arg1) throws StoreException {
		// TODO Auto-generated method stub

	}

	@Override
	public void put(String arg0, File arg1) throws StoreException {
		// TODO Auto-generated method stub

	}

	@Override
	public void put(String path, InputStream ins) throws StoreException {
		
		StoredObject object = container.getObject(path);
	    object.uploadObject(ins);

	}

	@Override
	public void putPart(String arg0, String arg1, int arg2, InputStream arg3, long arg4) throws StoreException {
		// TODO Auto-generated method stub

	}

	@Override
	public void rename(String arg0, String arg1) throws StoreException, StoreObjectNotFoundException {
		// TODO Auto-generated method stub

	}

}
