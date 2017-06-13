package cn.appsys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.appsys.dao.backendMapper;
import cn.appsys.pojo.backend_user;

@Service("backend_userService")
public class backend_userServiceImp implements backend_userService {

	@Autowired
	public backendMapper backendmapper;
	
	@Override
	public backend_user login(String userCode, String userPassword) {
		
		backend_user backuser = null;
		backuser = backendmapper.getBackendByUserCode(userCode);
		 if(backuser != null){
			 //判断密码是否正确
			 if(!backuser.getUserPassword().equals(userPassword)){
				 backuser = null;
			 }	 
		 }
		return backuser;
	}

}
