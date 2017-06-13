package cn.appsys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.appsys.dao.app_categorydao;
import cn.appsys.pojo.app_category;

@Service("categoryservice")
public class categoryServiceImpl implements categoryService {
	@Autowired
	app_categorydao categorydao;

	@Override
	public List<app_category> getjibie(Integer parentId) {
		return categorydao.getjibie(parentId);
	}

}
