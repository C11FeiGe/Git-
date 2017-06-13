package cn.appsys.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.appsys.pojo.backend_user;
import cn.appsys.service.backend_userService;
import cn.appsys.tools.Constants;

@RequestMapping("/backend")
@Controller
public class backendController {

	@Autowired
	backend_userService backend_userserivce;
		
	//开始的页面
	@RequestMapping(value="/developerbackendlogin.html", method=RequestMethod.GET)
	public String start(){
		return "backendlogin";
	}
	
	
	@RequestMapping(value="/login.html", method=RequestMethod.GET)
	public String login(){	
		return "backendlogin";
	}
	//登录处理页面
	@RequestMapping(value="/dologin.html", method=RequestMethod.POST)
	public String login(@Param("userCode")String userCode, @Param("userPassword")String userPassword,HttpServletRequest request,HttpSession session){
			
		backend_user backuser=backend_userserivce.login(userCode, userPassword);
		if(backuser == null){
			request.setAttribute("error", "用户名或密码错误！");
			return "backendlogin";
		}else{
			//登录成功 			
			session.setAttribute(Constants.BACKENDUSER_SESSION, backuser);
			return "backend/main";
		}
	}
	
	//注销
	@RequestMapping(value="/logout.html")
	public String logout(HttpSession session){
		session.invalidate(); //清除Session
		return "zhuye";
	}
	
}
