package com.buaa.cloudstore.utils;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;



public class Configuration extends PropertiesConfiguration {
	private static volatile Configuration instance = null;

	private static final String SYSTEM_CONFIG_FILE = "/config.properties";
	private static final String CUSTOM_CONFIG_FILE = "/custom.properties";

	private Configuration() {
		try {
			loadProps();
		} catch (ConfigurationException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Configuration getInstance() {
		if (instance == null) {
			synchronized (Configuration.class) {
				if(instance == null) {
					instance = new Configuration();
				}
			}
		}
		return instance;
	}
	
	
	public void loadProps() throws ConfigurationException, Exception {
		this.load(this.getClass().getResourceAsStream(SYSTEM_CONFIG_FILE));
		this.loadCustomProperties();
	}

	private void loadCustomProperties() throws Exception {
		InputStream is = this.getClass().getResourceAsStream(CUSTOM_CONFIG_FILE);
		if (is != null) {
			Properties custom = new Properties();
			custom.load(is);

			for (Enumeration<?> e = custom.keys(); e.hasMoreElements(); ) {
				String key = (String)e.nextElement();
				this.clearProperty(key);
				this.addProperty(key, custom.get(key));
			}
		} 
	}

}
