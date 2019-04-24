package com.gsoft.shiro.service;

import com.gsoft.shiro.entity.SysUser;

public interface SysUserService {

	SysUser findByUserName(String userName);

}
