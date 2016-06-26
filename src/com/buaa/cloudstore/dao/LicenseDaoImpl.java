package com.buaa.cloudstore.dao;

import org.springframework.stereotype.Repository;

import com.buaa.cloudstore.dao.base.BaseObjectDaoImpl;
import com.buaa.cloudstore.entity.License;

@Repository
public class LicenseDaoImpl extends BaseObjectDaoImpl<License> implements LicenseDao {
	public LicenseDaoImpl() {
		super.setPersistClass(License.class);
	}
}
