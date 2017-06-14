package cn.appsys.dao;

import java.util.List;
import java.util.Map;

import cn.appsys.pojo.app_info;

public interface appinfodao {
	//查询列表
	public List<app_info> getappinfo(Map map);

	//查询记录数
	public int getCount(app_info appinfo);
}
