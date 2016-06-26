package com.buaa.cloudstore.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.buaa.cloudstore.dao.StoreObjectDao;
import com.buaa.cloudstore.dao.base.Page;
import com.buaa.cloudstore.dao.base.QueryProperty;
import com.buaa.cloudstore.entity.StoreObject;
import com.buaa.cloudstore.store.IStore;
import com.buaa.cloudstore.store.StoreException;
import com.buaa.cloudstore.store.StoreObjectNotFoundException;

@Service
public class StoreObjectManagerImpl implements StoreObjectManager {
	@Autowired
	private IStore store;

	@Autowired
	private StoreObjectDao storeObjectDao;

	@Override
	public List<StoreObject> list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(StoreObject object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Page<StoreObject> getPage(Page<StoreObject> format) {
		return storeObjectDao.listPage(format);
	}

	@Override
	public Page<StoreObject> getPage(Page<StoreObject> format, List<QueryProperty> qps) {
		return storeObjectDao.listPageByProperties(format, qps);
	}

	@Override
	public Page<StoreObject> getPage(Page<StoreObject> format, QueryProperty qp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(StoreObject object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	@Transactional
	public void upload(StoreObject so, InputStream is) {

		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// Date date = new Date();
		//
		// String now = sdf.format(date);

		try {
			store.put(so.getPath(), is);
			storeObjectDao.add(so);
		} catch (StoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void download(String id, HttpServletResponse response) {

		StoreObject storeObject = storeObjectDao.getById(id);

		String mimeType = storeObject.getMimeType();
		String path = storeObject.getPath();
		String name = storeObject.getName();

		InputStream bis = null;
		OutputStream bos = null;
		try {
			response.reset();

			// response.setContentType(mimeType);
			response.setContentType("application/x-download");

			response.setHeader("Content-Disposition", "attachment; filename="
					+ new String(name.getBytes("UTF-8"), "ISO8859-1"));

			response.setHeader("Content-Length", String.valueOf(storeObject.getSize()));

			bis = new BufferedInputStream(store.getAsInputStream(path));

			bos = response.getOutputStream();

			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
			bos.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
