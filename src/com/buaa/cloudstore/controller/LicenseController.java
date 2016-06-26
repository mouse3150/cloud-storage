package com.buaa.cloudstore.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.buaa.cloudstore.dao.base.Page;
import com.buaa.cloudstore.dao.base.QueryProperty;
import com.buaa.cloudstore.entity.Consumer;
import com.buaa.cloudstore.entity.License;
import com.buaa.cloudstore.entity.Product;
import com.buaa.cloudstore.service.ConsumerManager;
import com.buaa.cloudstore.service.LicenseManager;
import com.buaa.cloudstore.service.ProductManager;
import com.buaa.cloudstore.utils.LicenseUtil;
import com.buaa.cloudstore.utils.ProductProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping(value = "/license")
public class LicenseController {
	private static String LICENSE_NAME = "license.dat";

	@Autowired
	private ProductManager productManager;

	@Autowired
	private LicenseManager licenseManager;

	@Autowired
	private ConsumerManager consumerManager;

	/**
	 * 用户license
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void listUserLicense(HttpServletRequest request,
			HttpServletResponse response) {
		Subject subject = SecurityUtils.getSubject();

		String userName = (String) subject.getPrincipal();

		Consumer user = consumerManager.getConsumerByName(userName);

		//Set<License> ls = user.getLicenses();
		
		Page<License> page = new Page<License>();

		String currentPage = request.getParameter("currentPage");
		if (currentPage != null && currentPage != "") {
			int cp = Integer.parseInt(currentPage);
			page.setFrom(cp);
		}
		
		QueryProperty qp = null;
		if(user != null) {
			if (user != null && user.getId() != "") {
				qp = new QueryProperty();
				qp.setHandleType("=");
				qp.setPropertyName("creator");
				qp.setPropertyValue(user.getId());
			}
		}
		if(qp != null) {
			page = licenseManager.getPageLicense(page, qp);
		}
		
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		try {
			json = mapper.writeValueAsString(page);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.write(json);
		if (out != null) {
			out.close();
		}
	}

	@RequestMapping(value = "/lists", method = RequestMethod.GET)
	public void listAllLicense(HttpServletRequest request,
			HttpServletResponse response) {
		
		response.setContentType("text/html;charset=UTF-8");
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		Subject subject = SecurityUtils.getSubject();

		String userName = (String) subject.getPrincipal();

		Consumer user = consumerManager.getConsumerByName(userName);

		Page<License> page = new Page<License>();

		String currentPage = request.getParameter("currentPage");
		if (currentPage != null && currentPage != "") {
			int cp = Integer.parseInt(currentPage);
			page.setFrom(cp);
		}
		
		List<QueryProperty> qps = new ArrayList<QueryProperty>();
		QueryProperty qp;
		
		String productName = request.getParameter("productName");
		if (productName != null && !"".equals(productName.trim())) {
			qp = new QueryProperty();
			qp.setHandleType("=");
			qp.setPropertyName("productName");
			qp.setPropertyValue(productName);
			qps.add(qp);
		}
		
		String projectName = request.getParameter("projectName");
		
		

		if (projectName != null && !"".equals(projectName.trim())) {
			
			try {
				projectName = URLDecoder.decode(projectName,"UTF-8");
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			} 
			
			qp = new QueryProperty();
			qp.setHandleType("like");
			qp.setPropertyName("projectName");
			qp.setPropertyValue(projectName);
			qps.add(qp);
		}

		String orderId = request.getParameter("orderId");

		if (orderId != null && !"".equals(orderId.trim())) {
			
			try {
				orderId = URLDecoder.decode(orderId,"UTF-8");
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			} 
			qp = new QueryProperty();
			qp.setHandleType("like");
			qp.setPropertyName("orderId");
			qp.setPropertyValue(orderId);
			qps.add(qp);
		}
		
		if(userName != null) {
			if ("admin".equalsIgnoreCase(userName)
					|| "administrator".equalsIgnoreCase(userName)
					|| "system".equalsIgnoreCase(userName)) {
				
				if (!qps.isEmpty()) {
					page = licenseManager.getPageLicense(page, qps);
				} else {
					page = licenseManager.getPageLicense(page);
				}
			} else {
				qp = new QueryProperty();
				qp.setHandleType("=");
				qp.setPropertyName("creator");
				qp.setPropertyValue(user.getId());
				qps.add(qp);
				page = licenseManager.getPageLicense(page, qps);
			}
		}


		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		try {
			json = mapper.writeValueAsString(page);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.write(json);
		if (out != null) {
			out.close();
		}
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(License license, HttpServletRequest request, HttpServletResponse response){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		
		Subject subject = SecurityUtils.getSubject();

		String userName = (String) subject.getPrincipal();

		Consumer user = consumerManager.getConsumerByName(userName);
		
		String now = sdf.format(date);
		
		license.setAddTime(now);
			
		if(user != null) {
			license.setCreator(user.getId());;
		}
		
		//user.setLicenseDownloadNum(user.getLicenseDownloadNum() + 1);
		consumerManager.updateConsumer(user);
		licenseManager.addLicense(license);

		//return "redirect:/pages/license";
		return "/pages/license";
	}
	
	@RequestMapping(value="/get", method=RequestMethod.GET)
	public String getLicenseById(String licenseId, HttpServletRequest request, HttpServletResponse response) {
		
		License license = licenseManager.getLicense(licenseId);
		if(license == null) {
			request.setAttribute("resultInfo", "无此license");
		} else {
			Consumer user = consumerManager.getConsumer(license.getCreator());
			request.setAttribute("licenseCreator", user.getName());
			request.setAttribute("license", license);
		}
		return "/pages/licenseDetail";
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public void delLicenseById(String licenseId, HttpServletRequest request, HttpServletResponse response) {
		
		licenseManager.delLicense(licenseId);
		PrintWriter out = null;

		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	out.write("success");
	}


	@RequestMapping(value = "/download", method = RequestMethod.POST)
	public String downloadLicense(String licenseId, HttpServletRequest request,
			HttpServletResponse response) {
		License license = licenseManager.getLicense(licenseId);
				
		if(license == null) {
			request.setAttribute("resultInfo", "此License已失效，无法下载！");
    		return "/pages/license";
		} else {
			String licenseContent = LicenseUtil.base64Encode(generateLicenseInfo(license));
			// System.out.println("--导出内容---" + licenseContent);
			LicenseUtil.printLicenseContent(licenseContent);
			response.reset();
			response.setContentType("application/x-download");
			response.addHeader("Content-Disposition", "attachment;fileName=\""
					+ LICENSE_NAME + "\"");
			
			OutputStream os = null;
			try {
				os = response.getOutputStream();
				os.write(licenseContent.getBytes());
				os.flush();
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}

	/*
	 * 根据产品及版本，创建临时License
	 */
	@RequestMapping(value = "/createtrial", method = RequestMethod.POST)
	public String createLicense(HttpServletRequest request,
			HttpServletResponse response) {
		// 判断用户下载次数
		Subject subject = SecurityUtils.getSubject();

		String userName = (String) subject.getPrincipal();

		Consumer user = consumerManager.getConsumerByName(userName);

		if (user.getLicenseDownloadNum() >= user.getDownloadNumLimit()) {
			request.setAttribute("resultInfo", "对不起，已经超过您的许可证下载次数限制，请及时购买许可！");
			return "/pages/product";
		}

		String proId = request.getParameter("productId");

		Product product = productManager.getProduct(proId);

		if (product == null) {
			request.setAttribute("resultInfo", "您创建的许可证对应的产品不存在，请与管理员联系！");
			return "/pages/product";
		}

		License license = new License();

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());

		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date today = calendar.getTime();

		// 许可证创建时间
		String create_date = sdf.format(today);

		license.setCreateDate(create_date);

		// 许可证有效期60天
		calendar.set(Calendar.DAY_OF_MONTH,
				calendar.get(Calendar.DAY_OF_MONTH) + 90);
		Date next = calendar.getTime();

		// 许可证截止时间
		String end_date = sdf.format(next);

		license.setExpireDate(end_date);

		String projectName = "unknown";

		// 许可证类型
		String licenseType = "trial";

		license.setType(licenseType);

		String licenseContent = "";

		licenseContent += "consumer_name=" + userName + "\n" + "project_name="
				+ projectName + "\n" + "license_type=" + licenseType + "\n"
				+ "create_date=" + create_date + "\n" + "end_date=" + end_date
				+ "\n";
		String productName = product.getName();

		license.setProductName(productName);

		if (productName == null) {
			request.setAttribute("resultInfo", "您创建的许可证对应的产品有错，请与管理员联系！");
			return "/pages/product";
		}

		String productVersion = product.getVersion();

		license.setProductVersion(productVersion);
		if (productVersion == null) {
			request.setAttribute("resultInfo", "您创建的许可证对应的产品有错，请与管理员联系！");
			return "/pages/product";
		}
		// 产品公共属性
		JsonNode prductCommPro = ProductProperty.getProductProperty(
				productName, "common");

		Iterator<String> keys = prductCommPro.fieldNames();
		String key = null;
		String value = null;
		while (keys.hasNext()) {
			key = keys.next();
			value = prductCommPro.get(key).asText();
			licenseContent += key + "=" + value + "\n";
		}

		JsonNode prductPro = ProductProperty.getProductProperty(productName,
				productVersion);

		keys = prductPro.fieldNames();
		while (keys.hasNext()) {
			key = keys.next();
			value = prductPro.get(key).asText();
			licenseContent += key + "=" + value + "\n";
		}

		// String licenseContent2 = "";
		// licenseContent2 += "consumer_name=" + "chenhao" + "\n"
		// + "project_name=" + "Yunnan" + "\n"
		// + "license_type=" + "Test" + "\n"
		// + "create_date=" + "2015-01-01" + "\n"
		// + "end_date=" + "2015-10-12" + "\n"
		// + "product_name=" + "GCloud" + "\n"
		// + "product_version=" + "5.0" + "\n";
		// System.out.println("--License内容---" + licenseContent);
		licenseContent = LicenseUtil.base64Encode(licenseContent);
		// System.out.println("--导出内容---" + licenseContent);
		response.reset();
		response.setContentType("application/x-download");
		response.addHeader("Content-Disposition", "attachment;fileName=\""
				+ LICENSE_NAME + "\"");

		OutputStream os = null;
		try {
			os = response.getOutputStream();
			os.write(licenseContent.getBytes());
			os.flush();
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(user != null) {
			license.setCreator(user.getId());
		}
		
		user.setLicenseDownloadNum(user.getLicenseDownloadNum() + 1);
		consumerManager.updateConsumer(user);
		licenseManager.addLicense(license);
		return null;
	}
	
	private String generateLicenseInfo(License license) {
		StringBuffer licenseInfo = new StringBuffer("");
		if(license != null) {
			if(license.getProductName() != null && !"".equals(license.getProductName())) {
				licenseInfo.append("product_name=").append(license.getProductName()).append("\n");
			}
			
			if(license.getProductVersion() != null && !"".equals(license.getProductVersion())) {
				licenseInfo.append("product_version=").append(license.getProductVersion()).append("\n");
			}
			
			if(license.getProjectName() != null && !"".equals(license.getProjectName())) {
				licenseInfo.append("project_name=").append(license.getProjectName()).append("\n");
			}
			
			if(license.getConsumerName() != null && !"".equals(license.getConsumerName())) {
				licenseInfo.append("consumer_name=").append(license.getConsumerName()).append("\n");
			}
			
			if(license.getOrderId() != null && !"".equals(license.getOrderId())) {
				licenseInfo.append("order_id=").append(license.getOrderId()).append("\n");
			}
			
			if(license.getType() != null && !"".equals(license.getType())) {
				licenseInfo.append("license_type=").append(license.getType()).append("\n");
			}
			
			if(license.getLevel() != null && !"".equals(license.getLevel())) {
				licenseInfo.append("license_level=").append(license.getLevel()).append("\n");
			}
			
			if(license.getCreateDate() != null && !"".equals(license.getCreateDate())) {
				licenseInfo.append("create_date=").append(license.getCreateDate()).append("\n");
			}
			
			if(license.getExpireDate() != null && !"".equals(license.getExpireDate())) {
				licenseInfo.append("end_date=").append(license.getExpireDate()).append("\n");
			}
			
			if(license.getMaxCpuNum() != null && !"".equals(license.getMaxCpuNum())) {
				licenseInfo.append("max_cpu_num=").append(license.getMaxCpuNum()).append("\n");
			}
			
			if(license.getIsBoundHostname()) {
				if(license.getBoundHostname() != null && !"".equals(license.getBoundHostname())) {
					licenseInfo.append("is_bound_hostname=").append("true").append("\n");
					licenseInfo.append("bound_hostname=").append(license.getBoundHostname()).append("\n");
				} else {
					licenseInfo.append("is_bound_hostname=").append("false").append("\n");
				}
				
			}
			
			if(license.getIsBoundIpAddress()) {
				if(license.getBoundIpAddress() != null && !"".equals(license.getBoundIpAddress())) {
					licenseInfo.append("is_bound_ip_address=").append("true").append("\n");
					licenseInfo.append("bound_ip_address=").append(license.getBoundIpAddress()).append("\n");
				} else {
					licenseInfo.append("is_bound_ip_address=").append("false").append("\n");
				}
				
			}
			
			if(license.getIsBoundMacAddress()) {
				if(license.getBoundMacAddress() != null && !"".equals(license.getBoundMacAddress())) {
					licenseInfo.append("is_bound_mac_address=").append("true").append("\n");
					licenseInfo.append("bound_mac_address=").append(license.getBoundMacAddress()).append("\n");
				} else {
					licenseInfo.append("is_bound_mac_address=").append("false").append("\n");
				}
				
			}
		}
		return licenseInfo.toString();
	}
}
