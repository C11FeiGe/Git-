package cn.appsys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.appsys.dao.versioninfomapper;
import cn.appsys.pojo.app_version;
@Service("versionService")
public class versionServiceImpl implements versionService {
	@Autowired
	versioninfomapper versioninfodao;

	@Override
	public List<app_version> selectaddversion(int appId) {
		return versioninfodao.selectaddversion(appId);
	}

	@Override
	public boolean addversion(app_version appversion) {
		if(versioninfodao.addversion(appversion)==1){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public int  selectid() {
		return versioninfodao.selectId();
	}

	@Override
	public app_version selectLast(int appId) {
		return versioninfodao.selectLast(appId);
	}

	@Override
	public boolean editVersion(app_version appversion) {
		if(versioninfodao.editVersion(appversion)==1){
			return true;
		}else{
		return false;
		}
	}



}
