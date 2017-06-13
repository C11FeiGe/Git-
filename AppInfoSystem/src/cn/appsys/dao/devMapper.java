package cn.appsys.dao;

import org.apache.ibatis.annotations.Param;

import cn.appsys.pojo.dev_user;

public interface devMapper {
	
	public dev_user getDevByDevCode(@Param("devCode")String devCode);

}
