package cn.appsys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.appsys.pojo.app_category;

public interface app_categorydao {
	
	public List<app_category> getjibie(@Param("parentId")Integer parentId);

}
