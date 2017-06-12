package cn.appsys.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.appsys.pojo.app_info;
import cn.appsys.service.appinfoService;
@RequestMapping(value="/developer")
@Controller
public class appinfoController {
	ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext-mybatis.xml");
	/*@Autowired
	appinfoService appservice;*/
	
	@RequestMapping(value="/appinfolist.html")
	public String getinfo(HttpServletRequest request){
		System.out.println("************");
		appinfoService appservice=(appinfoService)context.getBean("AppinfoService");
		List<app_info> lists=appservice.getappinfo();
		request.setAttribute("statusList", lists);
		return "/developer/appinfolist";
		
	}

}
