package cn.appsys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.appsys.dao.appinfodao;
import cn.appsys.pojo.app_info;
@Service("AppinfoService")
public class appinfoServiceImpl implements appinfoService{
	@Autowired
	appinfodao appinfo;
	@Override
	public List<app_info> getappinfo() {
		return appinfo.getappinfo();
	}

}
