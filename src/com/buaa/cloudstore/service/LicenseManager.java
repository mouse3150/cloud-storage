package com.buaa.cloudstore.service;

import java.util.List;

import com.buaa.cloudstore.dao.base.Page;
import com.buaa.cloudstore.dao.base.QueryProperty;
import com.buaa.cloudstore.entity.License;

public interface LicenseManager {
	public License getLicense(String id);

	public void addLicense(License license);

	public List<License> getAllLicense();

	public Page<License> getPageLicense(Page<License> format);
	
	public Page<License> getPageLicense(Page<License> format, QueryProperty qp);
	
	public Page<License> getPageLicense(Page<License> format, List<QueryProperty> qps);

	public boolean delLicense(String id);

	public boolean delLicense(License license);

	public boolean updateLicense(License license);
}
