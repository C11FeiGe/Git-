package cn.appsys.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.appsys.dao.backendInfoMapper;
import cn.appsys.pojo.app_info;

@Service("BackendinfoService")
public class backendinfoServiceImpl implements backendinfoService {

	@Autowired
	public backendInfoMapper backendInfomapper;
	
	@Override
	public List<app_info> getappuserinfo(Map map) {
		return backendInfomapper.getappuserinfo(map);
	}

	@Override
	public int getCount(app_info appuserinfo) {
		return backendInfomapper.getCount(appuserinfo);
	}

}
