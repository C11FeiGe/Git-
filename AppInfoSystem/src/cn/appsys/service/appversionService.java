package cn.appsys.service;

import java.util.List;


import cn.appsys.pojo.app_version;

public interface appversionService {

	public List<app_version> findSelectVersionInfo(int aid,int vid);
}
