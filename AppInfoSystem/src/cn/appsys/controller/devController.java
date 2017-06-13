package cn.appsys.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.appsys.pojo.dev_user;
import cn.appsys.service.dev_userService;
import cn.appsys.tools.Constants;

@RequestMapping("/developer")
@Controller
public class devController {
	
	

	@Autowired
	dev_userService dev_userservice;
	//登陆跳转
	@RequestMapping(value="/login.html", method=RequestMethod.GET)
	public String login(){
		return "devlogin";
	}
	
	//开始的页面
	@RequestMapping(value="/developerlogin.html", method=RequestMethod.GET)
	public String start(){
		return "devlogin";
	}
	

	//登陆处理页面
	@RequestMapping(value="/dologin.html", method=RequestMethod.POST)
	public String login(@Param("devCode")String devCode, @Param("devPassword")String devPassword,HttpServletRequest request,HttpSession session){
			
		dev_user devuser=dev_userservice.login(devCode, devPassword);
		if(devuser == null){
			request.setAttribute("error", "用户名或密码错误！");
			return "devlogin";
		}else{
			//登录成功 			
			session.setAttribute(Constants.DEVUSER_SESSION, devuser);
			return "developer/main";
		}
	}
}
