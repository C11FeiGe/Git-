package cn.appsys.service;



import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.appsys.pojo.app_version;
public interface versionService {
	//查询增加之前的内容
	public List<app_version> selectaddversion( int appId);
	
	
	//增加
	public boolean addversion(app_version appversion);
	
	//查询最新版本
	public int selectid();
	
	//查询最新版本详情
	public app_version selectLast(int appId);
	
	//修改确定
	public boolean editVersion(app_version appversion);
}
