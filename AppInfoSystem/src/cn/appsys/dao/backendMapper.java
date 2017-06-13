package cn.appsys.dao;

import org.apache.ibatis.annotations.Param;

import cn.appsys.pojo.backend_user;



public interface backendMapper {
	
	public backend_user getBackendByUserCode(@Param("userCode")String userCode);

}
