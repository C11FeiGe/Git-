package cn.appsys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.appsys.dao.appversionMapper;
import cn.appsys.pojo.app_version;
import cn.appsys.service.appversionService;

@Service("appversionService")
public class appversionServiceImpl implements appversionService {

	@Autowired
	appversionMapper appversionmapper;
	
	@Override
	public List<app_version> findSelectVersionInfo(int aid, int vid) {
		
		return appversionmapper.getSelectVersionInfo(aid, vid);
	}

}
