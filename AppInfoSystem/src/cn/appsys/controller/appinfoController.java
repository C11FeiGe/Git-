package cn.appsys.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.appsys.pojo.app_info;
import cn.appsys.service.appinfoService;
@RequestMapping(value="/developer")
@Controller
public class appinfoController {
	@Autowired
	appinfoService appinfoServ;
	@RequestMapping(value="/appinfolist.html")
	public String getinfo(HttpServletRequest request){
		List<app_info> lists=appinfoServ.getappinfo();
		request.setAttribute("statusList", lists);
		return "/developer/appinfolist";
		
	}

}
