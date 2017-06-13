package cn.appsys.dao;

import java.util.List;

import cn.appsys.pojo.data_dictionary;

public interface data_dictdao {
	
	//查询列表
	public List<data_dictionary> getdatalist(String typeName);
	

}
