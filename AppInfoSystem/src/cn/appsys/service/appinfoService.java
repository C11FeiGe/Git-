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

}
