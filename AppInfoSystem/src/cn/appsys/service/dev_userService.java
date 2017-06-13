package cn.appsys.service;


import cn.appsys.pojo.dev_user;

public interface dev_userService {

	public dev_user login(String devCode , String devPassword);
}
