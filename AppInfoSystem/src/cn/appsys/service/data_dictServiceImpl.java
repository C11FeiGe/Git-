package cn.appsys.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.appsys.dao.data_dictdao;
import cn.appsys.pojo.data_dictionary;

@Service("dictService")
public class data_dictServiceImpl implements data_dictService {
	
	@Autowired
	data_dictdao dictdao;

	@Override
	public List<data_dictionary> getdatalist(String typeName) {
		return dictdao.getdatalist(typeName);
	}
	


}
