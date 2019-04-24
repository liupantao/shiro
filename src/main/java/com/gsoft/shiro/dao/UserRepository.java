package com.gsoft.shiro.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gsoft.shiro.entity.SysUser;

public interface UserRepository extends JpaRepository<SysUser,Integer> {
    SysUser findByUsername(String userName);
}
