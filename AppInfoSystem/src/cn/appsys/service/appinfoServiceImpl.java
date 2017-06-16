package cn.appsys.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.appsys.dao.appinfodao;
import cn.appsys.pojo.app_info;

@Service("AppinfoService")
public class appinfoServiceImpl implements appinfoService {
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

	// 是否存在APKName
	@Override
	public boolean getAPKName(String APKName) {
		if (appinfodao.getAPKName(APKName) != null) {
			return true;
		} else {

			return false;
		}
	}

	@Override
	public boolean addinfolist(app_info appinfo) {
		if (appinfodao.addinfolist(appinfo) == 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean editappinfo(app_info appinfo) {
		if (appinfodao.editappinfo(appinfo) == 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public app_info selectedit(int id) {
		return appinfodao.selectedit(id);
	}

	@Override
	public boolean editversion(app_info appinfo) {
		if (appinfodao.editversion(appinfo) == 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public app_info selectinfoById(int id) {
		return appinfodao.selectinfoById(id);
	}

	@Override
	public boolean deleteByid(int id) {
		if (appinfodao.deleteByid(id) == 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean updateshang(int id) {
		if (appinfodao.updateshang(id) == 1) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public boolean updatexia(int id) {
		if (appinfodao.updatexia(id) == 1) {
			return true;
		} else {
			return false;
		}
	}

}
