package com.gsoft.shiro.service.serviceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gsoft.shiro.dao.UserRepository;
import com.gsoft.shiro.entity.SysUser;
import com.gsoft.shiro.service.SysUserService;

@Service
public class SysUserServiceImpl implements SysUserService{
  
	
	@Resource
    private UserRepository userRepository;
	
	@Override
	public SysUser findByUserName(String userName) {
		// TODO Auto-generated method stub
		return userRepository.findByUsername(userName);
	}

}
