package com.buaa.cloudstore.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.buaa.cloudstore.dao.base.Page;
import com.buaa.cloudstore.dao.base.QueryProperty;
import com.buaa.cloudstore.entity.Product;
import com.buaa.cloudstore.service.ProductManager;
import com.buaa.cloudstore.utils.ByteUnit;
import com.buaa.cloudstore.utils.FileOperateUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Controller
@RequestMapping("/product")
public class ProductController {
	@Autowired()
	private ProductManager productManager;
	
	@RequestMapping("/list")
	public void list(HttpServletRequest request, HttpServletResponse response) {
		Page<Product> page = new Page<Product>();
		
		String currentPage = request.getParameter("currentPage");
		if(currentPage != null && currentPage != "") {
			int cp = Integer.parseInt(currentPage);
			page.setFrom(cp);
		}
		
		List<QueryProperty> qps = new ArrayList<QueryProperty>();
		QueryProperty qp;
		String productName = request.getParameter("productName");
		if(productName != null && productName != "") {
			qp = new QueryProperty();
			qp.setHandleType("=");
			qp.setPropertyName("name");
			qp.setPropertyValue(productName);
			qps.add(qp);
		}
		
		String productVersion = request.getParameter("productVersion");
		
		if(productVersion != null && productVersion != "") {
			qp = new QueryProperty();
			qp.setHandleType("=");
			qp.setPropertyName("version");
			qp.setPropertyValue(productVersion);
			qps.add(qp);
		}
		
		String platform = request.getParameter("platform");
		
		if(platform != null && platform != "") {
			qp = new QueryProperty();
			qp.setHandleType("=");
			qp.setPropertyName("platform");
			qp.setPropertyValue(platform);
			qps.add(qp);
		}
		
		ObjectMapper mapper = new ObjectMapper();
		
		if(!qps.isEmpty()) {
			page = productManager.getPageProduct(page, qps);
		} else {
			page = productManager.getPageProduct(page);
		}
		
		String json = "";
		try {
			json = mapper.writeValueAsString(page);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.write(json);
		if(out != null) {
			out.flush();
		}
		
	}
	
	
    /** 
     * 上传单个文件 
     * @param request 
     * @return 
     * @throws Exception
     * 
     */
	//@RequiresRoles("admin") 
    @RequestMapping(value = "uploadSingle",method=RequestMethod.POST)  
    public String uploadSingle(Product product, HttpServletRequest request) throws Exception {  
		SecurityUtils.getSubject().checkRole("admin");
    	String name = product.getName();
    	String version = product.getVersion();
    	String type = product.getType();
    	
    	if(name == null || version == null || type == null ) {
    		request.setAttribute("result", "添加产品失败!");
    		return "";
    	}
    	
    	StringBuffer sb = new StringBuffer();
        sb.append(name.replaceAll("/", "_").toLowerCase()).append("/").append(version).append("/").append(type); 
  
        Map<String, Object> result = FileOperateUtil.uploadSingleFile(request,  
        		sb.toString());
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		
		String now = sdf.format(date);
        
		product.setCreateTime(now);
		
		long size = (long) result.get(FileOperateUtil.SIZE);
				
		ByteUnit bu = new ByteUnit(size, ByteUnit.B);
		product.setFileSize(bu.getMegaByte().substring(0, bu.getMegaByte().indexOf(".")));
		product.setFilePath((String)result.get(FileOperateUtil.RELATIVE_PATH));
		
		productManager.addProduct(product);
		
		request.setAttribute("result", "添加产品成功!");
		
        return "redirect:/back/productManage.jsp";
    }
    
    
    @RequestMapping(value = "upload",method=RequestMethod.POST)  
    public String upload(Product product, HttpServletRequest request) throws Exception {  
		//SecurityUtils.getSubject().checkRole("admin");
    	String name = product.getName();
    	String version = product.getVersion();
    	String type = product.getType();
    	
    	if(name == null || version == null || type == null ) {
    		request.setAttribute("result", "添加产品失败!");
    		return "";
    	}
    	
    	StringBuffer sb = new StringBuffer();
        sb.append(name.replaceAll("/", "_").toLowerCase()).append("/").append(version).append("/").append(type); 
  
        Map<String, Object> result = FileOperateUtil.uploadSingleFile(request,  
        		sb.toString());
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		
		String now = sdf.format(date);
        
		product.setCreateTime(now);
		
		long size = (long) result.get(FileOperateUtil.SIZE);
				
		ByteUnit bu = new ByteUnit(size, ByteUnit.B);
		product.setFileSize(bu.getMegaByte().substring(0, bu.getMegaByte().indexOf(".")));
		product.setFilePath((String)result.get(FileOperateUtil.RELATIVE_PATH));
		
		productManager.addProduct(product);
		
		request.setAttribute("result", "添加产品成功!");
		
        return "redirect:/back/productManage.jsp";
    }
    
	//@RequiresRoles("admin")
    @RequestMapping(value = "delete",method=RequestMethod.POST)
    public void delete(HttpServletRequest request, HttpServletResponse response) {
		SecurityUtils.getSubject().checkRole("admin");
    	String proId = request.getParameter("productId");
    	String basePath = request.getSession().getServletContext().getRealPath("/");
    	
    	Product product = productManager.getProduct(proId);
    	
    	PrintWriter out = null;

		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	if(product == null) {
    		out.write("no_exist");
    		return;
    	}
    	
    	File file = new File(basePath + product.getFilePath());
    	
    	if(file.isFile() && file.exists()) {
    		file.delete();
    	}
    	
    	
    	productManager.delProduct(product);
    	out.write("success");
    }
	
	/**
	 * 产品下载
	 * @param request
	 * @param response
	 * @return
	 */
    
    @RequestMapping(value = "download",method=RequestMethod.POST)
    public String download(HttpServletRequest request, HttpServletResponse response) {

    	String proId = request.getParameter("productId");
    	String basePath = request.getSession().getServletContext().getRealPath("/");
    	
    	Product product = productManager.getProduct(proId);
    	
//    	PrintWriter out = null;
//
//		try {
//			out = response.getWriter();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//    	
//    	if(product == null) {
//    		out.write("no_product");
//    		return;
//    	}
    	
    	if(product == null) {
    		request.setAttribute("resultInfo", "您下载的产品不存在，请与管理员联系！");
    		return "/pages/product";
    	}
    	
    	File file = new File(basePath + product.getFilePath());
    	
    	if(!file.isFile() || !file.exists()) {
    		//out.write("no_file");
    		request.setAttribute("resultInfo", "您下载的产品包文件不存在，请联系在线客服！");
    		//return new ModelAndView("/pages/product");
    		return "/pages/product";
    	}
    	
    	String contentType = "application/octet-stream"; 
    	
    	String realName = product.getName().replaceAll("/", "") + product.getVersion() + "_" + product.getType() + ".zip";
    	
    	FileOperateUtil.download(request, response, product.getFilePath(), contentType,  
                realName);
    	
    	//return new ModelAndView("/pages/product");
    	request.setAttribute("resultInfo", " ");
		//return new ModelAndView("/pages/product");
		return null;
    	//out.write("success");
    }
    
}
