package cn.appsys.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.appsys.pojo.app_info;

public interface appinfoService {
	//查询列表
	public List<app_info> getappinfo(Map map);
	
	//查询记录数
	public int getCount(app_info appinfo);
	//查询是否已存在
	public boolean getAPKName(String APKName);
	//增加
	public boolean addinfolist(app_info appinfo);
	
	//修改
	public boolean editappinfo(app_info appinfo);
	
	//修改之前的内容
	public app_info selectedit(int id);

}
