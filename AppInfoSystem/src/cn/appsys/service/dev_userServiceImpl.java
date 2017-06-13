package cn.appsys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.appsys.dao.devMapper;
import cn.appsys.pojo.backend_user;
import cn.appsys.pojo.dev_user;
@Service("dev_userService")
public class dev_userServiceImpl implements dev_userService {

	@Autowired
	public devMapper devmapper;
	
	@Override
	public dev_user login(String devCode, String devPassword) {
			
		dev_user devuser = null;
		devuser = devmapper.getDevByDevCode(devCode);
		 if(devuser != null){
			 //判断密码是否正确
			 if(!devuser.getDevPassword().equals(devPassword)){
				 devuser = null;
			 }	 
		 }
		return devuser;
	
	}

}
