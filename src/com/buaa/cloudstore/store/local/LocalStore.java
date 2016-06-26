package com.buaa.cloudstore.store.local;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import com.buaa.cloudstore.store.IStore;
import com.buaa.cloudstore.store.StoreException;
import com.buaa.cloudstore.store.StoreObjectInfo;
import com.buaa.cloudstore.store.StoreObjectNotFoundException;
import com.buaa.cloudstore.utils.FileOperateUtil;

//@Service("store")
public class LocalStore implements IStore {
	private static String storeDir;
	static {
		StringBuffer relativePath = new StringBuffer(FileOperateUtil.UPLOADDIR);
		
		String uploadDir = "E:/" + relativePath.toString();
		
		File file = new File(uploadDir);

		if (!file.exists()) {
			file.mkdirs();
		}
		
		storeDir = uploadDir;
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
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(storeDir + File.separator + path);
		} catch (FileNotFoundException e) {
			throw new StoreObjectNotFoundException(path + "not found.");
		}
		return fis;
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
	public void put(String path, InputStream is) throws StoreException {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(storeDir + File.separator + path);
			FileCopyUtils.copy(is, fos);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if(is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

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
