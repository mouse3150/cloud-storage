package com.buaa.cloudstore.dao.base;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO对象基类
 */
@Transactional
public class BaseDaoImpl implements BaseDao {

	protected final Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	private SessionFactory sessionFacotry;
	/**
	 * 设置sessionFacotry
	 * 
	 * @param sessionFacotry
	 */
	public void setSessionFacotry(SessionFactory sessionFacotry) {
		this.sessionFacotry = sessionFacotry;
	}
	
	public Session getSession() {
		return sessionFacotry.getCurrentSession();
	}
}
