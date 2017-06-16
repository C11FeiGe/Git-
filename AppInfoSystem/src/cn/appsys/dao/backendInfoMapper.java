package cn.appsys.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.appsys.pojo.app_info;

public interface backendInfoMapper {

	
	//查询列表   后台
	public List<app_info> getappuserinfo(Map map);

	//查询记录数
	public int getCount(app_info appuserinfo);
		
	//App审核
	public List<app_info> getSelectAppCheck(@Param("aid") int aid,@Param("vid") int vid);
	
	//App审核通过、未通过审核
	public int getUpdataStatus(@Param("status") int status,@Param("id") int id);
}
