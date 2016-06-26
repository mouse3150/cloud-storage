package com.buaa.cloudstore.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.buaa.cloudstore.dao.base.Page;
import com.buaa.cloudstore.dao.base.QueryProperty;
import com.buaa.cloudstore.entity.StoreObject;
import com.buaa.cloudstore.model.Directory;
import com.buaa.cloudstore.service.StoreObjectManager;
import com.buaa.cloudstore.utils.FileOperateUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/store")
public class StoreObjectController {
	@Autowired()
	private StoreObjectManager storeObjectManager;
	
	
	@RequestMapping(method = RequestMethod.GET)
	public void list(HttpServletRequest request, HttpServletResponse response) {
		Page<StoreObject> page = new Page<StoreObject>();
		
		String currentPage = request.getParameter("currentPage");
		if(currentPage != null && currentPage != "") {
			int cp = Integer.parseInt(currentPage);
			page.setFrom(cp);
		}
		
		List<QueryProperty> qps = new ArrayList<QueryProperty>();
		QueryProperty qp;
		String objectName = request.getParameter("objectName");
		if(objectName != null && objectName != "") {
			qp = new QueryProperty();
			qp.setHandleType("=");
			qp.setPropertyName("name");
			qp.setPropertyValue(objectName);
			qps.add(qp);
		}
		
		String objectVersion = request.getParameter("objectVersion");
		
		if(objectVersion != null && objectVersion != "") {
			qp = new QueryProperty();
			qp.setHandleType("=");
			qp.setPropertyName("version");
			qp.setPropertyValue(objectVersion);
			qps.add(qp);
		}
		
		String type = request.getParameter("type");
		
		if(type != null && type != "") {
			qp = new QueryProperty();
			qp.setHandleType("=");
			qp.setPropertyName("mimeType");
			qp.setPropertyValue(type);
			qps.add(qp);
		}
		
		ObjectMapper mapper = new ObjectMapper();
		
		if(!qps.isEmpty()) {
			page = storeObjectManager.getPage(page, qps);
		} else {
			page = storeObjectManager.getPage(page);
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
	
	@RequestMapping(value = "uploadSingle", method = RequestMethod.POST)
	public String uploadSingle(HttpServletRequest request) throws Exception {
		
		MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
		MultipartFile mFile = mRequest.getFile("file");
		
		String fileName = null;

		fileName = mFile.getOriginalFilename();
		String contentType = mFile.getContentType();
		String name = mFile.getName();
		long size = mFile.getSize();
		String storeName = FileOperateUtil.rename(fileName);
		
		StoreObject so = new StoreObject();
		so.setMimeType(contentType);
		so.setPath(storeName);
		so.setSize(size);
		so.setName(fileName);
		
		storeObjectManager.upload(so, mFile.getInputStream());
		
		request.setAttribute("result", "添加成功!");
		
        return "redirect:/back/objectManage.jsp";
	}
	
	@RequestMapping(value = "createDir", method = RequestMethod.POST)
	public String createDir(HttpServletRequest request, Directory dir) {
		request.setAttribute("result", "添加成功!");
		return "redirect:/back/objectManage.jsp";
	}
	
	
	 @RequestMapping(value = "/{id}", method =RequestMethod.POST)
	 public String download(@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response) {
		    storeObjectManager.download(id, response);
		    
		    return null;
	 }
	 
	 @RequestMapping(value = "/download", method=RequestMethod.POST)
	 public String download(HttpServletRequest request, HttpServletResponse response) throws IOException {
		 
		 String objectId = request.getParameter("objectId");
		 storeObjectManager.download(objectId, response);
		   
		 return null;
	 }
}
