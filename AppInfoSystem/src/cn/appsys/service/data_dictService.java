package cn.appsys.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.appsys.pojo.data_dictionary;

public interface data_dictService {
	public List<data_dictionary> getdatalist(String typeName);

}
