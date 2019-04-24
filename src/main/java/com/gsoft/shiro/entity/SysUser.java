/*
 * Gsoft开发框架
 * Copyright 2015-2020 the original author or authors.
 */
package com.gsoft.shiro.entity;

import com.alibaba.fastjson.annotation.JSONField;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 实体: 系统用户
 * 
 * @author 代码生成器
 * @date 2018-04-13
 * 
 */
@Entity
@Table(name = "SYS_USER")
public class SysUser implements Serializable {
	
	
	private static final long serialVersionUID = 1L;

	/**
	 * @Fields ID
	 */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid.hex")
	@Column(name = "ID")
	@Length(max = 32)
	private String id;

	/**
	 * @Fields 用户账号
	 */
	@Column(name = "USERNAME", nullable = false)
	@Length(max = 40)
	private String username;
	
	/**
	 * @Fields 密码
	 */
	@JSONField(serialize=false)
	@Column(name = "PASSWORD", nullable = false)
	@Length(max = 40)
	private String password;
	
	/**
	 * @Fields 帐号状态
	 */
	@Column(name = "STATUS", nullable = false)
	@Length(max = 1)
	private String status;
	
	/**
	 * @Fields 创建时间
	 */
	@Column(name = "CREATE_TIME", nullable = false)
	@Length(max=20)
	private String createTime;

	/**
	 * @Fields 手机号
	 */

	@Column(name = "TELEPHONE", nullable = false)
	@Length(max=11)
	private String telephone;

	/**
	 * @Fields 用户昵称
	 */
	@Column(name = "NICKNAME", nullable = false)
	@Length(max=10)
	private String nickName;

	/**
	 * @Fields 邮箱地址
	 */
	@Column(name = "EMAIL", nullable = false)
	@Length(max=30)
	private String email;

	
	
	/**
	 * @Fields 加密密码的盐
	 */
	@Column(name = "salt", nullable = false)
	@Length(max=255)
	private String salt;//加密密码的盐

	/**
	 * 角色集合
	 */
	@ManyToMany(targetEntity = SysRole.class, fetch = FetchType.EAGER)
	@JoinTable(
			name = "SYS_USER_ROLE", 
			joinColumns = { @JoinColumn(name = "USER_ID") }, 
			inverseJoinColumns = @JoinColumn(name = "ROLE_ID")
		)
	private List<SysRole> roles=new ArrayList<>();
	
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}



	

	public List<String> roleIds() {
		List<String> roleIds = new ArrayList<>();
		if (roles != null) {
			for (SysRole role : roles) {
				roleIds.add(role.getRoleId());
			}
		}
		return roleIds;
	}

	public List<SysRole> getRoles() {
		return roles;
	} 

	public void setRoles(List<SysRole> roles) {
		this.roles = roles;
	}

	
	public String getUserId() {
		return this.id;
	}
	
	  /**
     * 密码盐. 重新对盐重新进行了定义，用户名+salt，这样就不容易被破解，可以采用多种方式定义加盐
     * @return
     */
    public String getCredentialsSalt(){
        return this.username+this.salt;
    }

}