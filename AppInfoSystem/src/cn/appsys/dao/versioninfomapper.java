package cn.appsys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.appsys.pojo.app_version;


public interface versioninfomapper {
	//新增版本之前的内容
	public List<app_version> selectaddversion(@Param("appId") int appId);
	
	//新增
	public int addversion(app_version appversion);
	
	//查询最后一个插入语句
	public int selectId();
	
	//修改版本之前的内容
	public app_version selectLast(@Param("appId") int appId);
	
	//修改确定
	public int editVersion(app_version appversion);
	
	

}
