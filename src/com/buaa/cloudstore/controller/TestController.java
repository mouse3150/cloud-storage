package com.buaa.cloudstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.buaa.cloudstore.dao.base.Page;
import com.buaa.cloudstore.entity.Consumer;
import com.buaa.cloudstore.service.ConsumerManager;

@Controller
@Service
@RequestMapping("/test")
public class TestController {
	
	@Autowired()
	private ConsumerManager consumerManager;
	
	@RequestMapping("/get")
	public List<Consumer> consumerTest() {
		List<Consumer> cons = consumerManager.getAllConsumer();
		return cons;
	}
	
	@RequestMapping("/add")
	public void addConsumer() {
		Consumer con = new Consumer();
		con.setName("chenhao");
		con.setEmail("chenhao@t.com");
		con.setPassword("chenhao");
		consumerManager.addConsumer(con);
	}

	@RequestMapping("/delete")
	public void delConsumer() {
		String id = "8a8a84dd488763c70148876421db0000";
		consumerManager.delConsumer(id);
	}
	
	@RequestMapping("/update")
	public void updateConsumer() {
		List<Consumer> cons = consumerManager.getAllConsumer();
		cons.get(0).setName("已修改");
		consumerManager.updateConsumer(cons.get(0));
	}
	
	@RequestMapping("/page")
	public void pageConsumer() {
		Page<Consumer> page = new Page<Consumer>();
		
		page.setFrom(1);
		page.setLimit(3);
		Page<Consumer> rePage = consumerManager.getPageConsumer(page);
		List<Consumer> data = rePage.getData();
		
		for(Consumer c:data) {
			System.out.println("用户名" + c.getName());
		}
		
		System.out.println("页总数：" + rePage.getTotalPage());
		System.out.println("记录总数：" + rePage.getTotalRecords());
	}
	
	public ConsumerManager getConsumerManager() {
		return consumerManager;
	}

	public void setConsumerManager(ConsumerManager consumerManager) {
		this.consumerManager = consumerManager;
	}
}
