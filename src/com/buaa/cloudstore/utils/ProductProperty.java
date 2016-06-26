package com.buaa.cloudstore.utils;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ProductProperty {
	private static final String PRODUCT_PROPERTY_FILE = "product_property.json";
	private static JsonNode productProperty;
	
	static {
		handle();
	}
	
	public static JsonNode getProductProperty() {
		if(productProperty == null) {
			handle();
		}
		return productProperty;
	}
	
	/**
	 * key为版本或"common"
	 * @param prodcut
	 * @param key
	 * @return
	 */
	
	public static JsonNode getProductProperty(String prodcut, String key) {
		if(productProperty == null) {
			handle();
		}
		return productProperty.get(prodcut).get(key);
	}
	
	
	private static JsonNode handle() {
		String path = ProductProperty.class.getResource("/").getPath();
		
		File file = new File(path + PRODUCT_PROPERTY_FILE);
//		FileInputStream in = null;
//		try {
//			in = new FileInputStream(path);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//		
//		InputStreamReader isr = new InputStreamReader(in);
//
//		char[] ch = null;
//		try {
//			ch = new char[in.available()];
//			isr.read(ch);
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
//		
//		String str = new String(ch);
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			productProperty = mapper.readTree(file);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return productProperty;
	}
	
}
