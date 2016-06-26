package com.buaa.cloudstore.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.buaa.cloudstore.entity.Consumer;
import com.buaa.cloudstore.service.ConsumerManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Controller
@RequestMapping("/user")
public class ConsumerController {
	@Autowired
	private ConsumerManager consumerManager;
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public void register(Consumer consumer,HttpServletRequest request, HttpServletResponse response){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		
		String now = sdf.format(date);
		
		consumer.setRegisterTime(now);
		
		if("system".equalsIgnoreCase(consumer.getName()) || "admin".equalsIgnoreCase(consumer.getName())
        		|| "administrator".equalsIgnoreCase(consumer.getName())) {
			consumer.setDownloadNumLimit(100);
        } else {
        	consumer.setDownloadNumLimit(20);
        }
		consumer.setLocked(false);
		
		consumer.setLicenseDownloadNum(0);
		consumerManager.addConsumer(consumer);
		
		PrintWriter out = null;
		
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.write("success");
	}

	@RequestMapping(value="/validateUser",method=RequestMethod.POST)
	public void userValidate(HttpServletRequest request, HttpServletResponse response){
		String userName = request.getParameter("userName");
		Consumer user = null;
		PrintWriter out = null;
		
		if(!"".equals(userName) && userName != null) {
			user = consumerManager.getConsumerByName(userName);
		}
		String result = "";
		if(user != null) {
			result = "exist";
		} else {
			result = "no_exist";
		}
		
		try {
			out = response.getWriter();
		} catch (IOException e) {
			
		}
		
		out.write(result);
	}
	
	@RequestMapping(value="/validateEmail",method=RequestMethod.POST)
	public void validateEmail(HttpServletRequest request, HttpServletResponse response){
		String email = request.getParameter("email");
		Consumer user = null;
		PrintWriter out = null;
		
		if(!"".equals(email) && email != null) {
			user = consumerManager.getConsumerByEmail(email);
		}
		String result = "";
		if(user != null) {
			result = "exist";
		} else {
			result = "no_exist";
		}
		
		try {
			out = response.getWriter();
		} catch (IOException e) {
			
		}
		
		out.write(result);
	}
	
	@RequestMapping(value="/userinfo",method=RequestMethod.GET)
	public void getUserinfo(HttpServletRequest request, HttpServletResponse response) {
		
		ObjectMapper mapper = new ObjectMapper();
		
		Subject subject = SecurityUtils.getSubject();
		
		String userName = (String)subject.getPrincipal();
		
		Consumer consumer = consumerManager.getConsumerByName(userName);
		
		String json = "";
		
		if (consumer != null) {
			try {
				json = mapper.writeValueAsString(consumer);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		
		PrintWriter out = null;

		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		out.write(json);
	}
	
	@RequestMapping(value="/changepw",method=RequestMethod.POST)
	public void changePassword(String password, HttpServletRequest request, HttpServletResponse response) {
		
		//String password = (String)request.getAttribute("password");
		Subject subject = SecurityUtils.getSubject();
		
		String userName = (String)subject.getPrincipal();
		
		Consumer consumer = consumerManager.getConsumerByName(userName);
		
		PrintWriter out = null;

		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String json = "success";
		
		if(password == null || consumer == null) {
			json = "fail";
			out.write(json);
			return;
		}
		
		consumerManager.changePassword(consumer.getId(), password);
		
		out.write(json);
	}
}
