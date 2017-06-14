package cn.appsys.service;

import java.util.List;
import java.util.Map;

import cn.appsys.pojo.app_info;

public interface backendinfoService {

		//查询列表
		public List<app_info> getappuserinfo(Map map);
		
		//查询记录数
		public int getCount(app_info appuserinfo);
	
}
