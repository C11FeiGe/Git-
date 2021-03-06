package cn.appsys.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.appsys.pojo.app_info;

public interface appinfodao {
	//查询列表
	public List<app_info> getappinfo(Map map);

	//查询记录数
	public int getCount(app_info appinfo);
	
	
	//查询是否已存在
	public app_info getAPKName(@Param("APKName")String APKName);
	
	//增加
	public int addinfolist(app_info appinfo);
	
	
	//修改
	public int editappinfo(app_info appinfo);
	
	//显示修改之前的内容
	public app_info selectedit(@Param("id") int id);
	
	
	//修改版本信息
	public int editversion(app_info appinfo);
	
	
	//查看APP基础信息
	public app_info selectinfoById(@Param("id") int id);
	
	//删除
	public int deleteByid(@Param("id") int id);

	//上架
	public int updateshang(@Param("id") int id);
	
	//下架
	public int updatexia(@Param("id") int id);
	
}
