package com.buaa.cloudstore.store;

import java.io.File;
import java.io.InputStream;

public interface IStore {
	public void cancel(String arg0, String arg1) throws StoreException;

	public void commit(String arg0, String arg1) throws StoreException;

	public String create(String arg0) throws StoreException;

	public void delete(String arg0) throws StoreException, StoreObjectNotFoundException;

	public void deleteTree(String arg0) throws StoreException, StoreObjectNotFoundException;

	public boolean exists(String arg0) throws StoreException;

	public InputStream getAsInputStream(String arg0) throws StoreException, StoreObjectNotFoundException;

	public Object getAsObject(String arg0) throws StoreException, StoreObjectNotFoundException;

	public StoreObjectInfo getInfo(String arg0) throws StoreException, StoreObjectNotFoundException;

	public int[] getParts(String arg0, String arg1) throws StoreException;

	public String getUrl(String arg0) throws StoreException, StoreObjectNotFoundException;
	
	public String[] keys(String arg0) throws StoreException;

	public long length(String arg0) throws StoreException, StoreObjectNotFoundException;

	public void put(String arg0, Object arg1) throws StoreException;

	public void put(String arg0, File arg1) throws StoreException;

	public void put(String arg0, InputStream arg1) throws StoreException;
	
	public void putPart(String arg0, String arg1, int arg2, InputStream arg3, long arg4) throws StoreException;

	public void rename(String arg0, String arg1) throws StoreException, StoreObjectNotFoundException;
}
