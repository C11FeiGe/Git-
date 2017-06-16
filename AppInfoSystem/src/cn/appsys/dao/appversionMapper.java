package cn.appsys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.appsys.pojo.app_version;

public interface appversionMapper {

	//版本信息
	public List<app_version> getSelectVersionInfo(@Param("aid") int aid,@Param("vid") int vid);
}
