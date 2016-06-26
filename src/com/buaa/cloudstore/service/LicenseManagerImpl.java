package com.buaa.cloudstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buaa.cloudstore.dao.LicenseDao;
import com.buaa.cloudstore.dao.base.Page;
import com.buaa.cloudstore.dao.base.QueryProperty;
import com.buaa.cloudstore.entity.License;

@Service
public class LicenseManagerImpl implements LicenseManager {
	
	@Autowired
	private LicenseDao licenseDao;
	
	
	@Override
	public void addLicense(License license) {
		licenseDao.add(license);
	}
	
	@Override
	public boolean delLicense(License license) {
		licenseDao.delete(license);
		return true;
	}
	
	@Override
	public boolean delLicense(String id) {
		licenseDao.deleteById(id);
		return true;
	}
	
	@Override
	public List<License> getAllLicense() {
		List<License> licenses = licenseDao.list();
		return licenses;
	}
	
	@Override
	public License getLicense(String id) {
		License license = licenseDao.getById(id);		
		return license;
	}
	
	@Override
	public Page<License> getPageLicense(Page<License> format) {
		
		Page<License> page = licenseDao.listPage(format);
		return page;
	}
	
	@Override
	public Page<License> getPageLicense(Page<License> format,
			List<QueryProperty> qps) {
		Page<License> page = licenseDao.listPageByProperties(format, qps);
		return page;
	}
	
	@Override
	public Page<License> getPageLicense(Page<License> format, QueryProperty qp) {
		Page<License> page = licenseDao.listPageByProperty(format, qp);
		return page;
	}
	
	@Override
	public boolean updateLicense(License license) {
		licenseDao.update(license);
		return true;
	}
}
