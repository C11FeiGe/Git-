package cn.appsys.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.appsys.dao.appinfodao;
import cn.appsys.pojo.app_info;
@Service("AppinfoService")
public class appinfoServiceImpl implements appinfoService{
	@Autowired
	appinfodao appinfodao;

	@Override
	public List<app_info> getappinfo(Map map) {
		return appinfodao.getappinfo(map);
	}


	@Override
	public int getCount(app_info appinfo) {
		return appinfodao.getCount(appinfo);
	}




}
