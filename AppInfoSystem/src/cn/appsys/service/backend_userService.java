package cn.appsys.service;

import cn.appsys.pojo.backend_user;

public interface backend_userService {

	public backend_user login(String userCode , String userPassword);//登录
}
