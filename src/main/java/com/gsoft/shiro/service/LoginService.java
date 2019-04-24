package com.gsoft.shiro.service;

import com.gsoft.shiro.entity.LoginResult;

public interface LoginService {
	 LoginResult login(String userName,String password);
	 void logout();
}
