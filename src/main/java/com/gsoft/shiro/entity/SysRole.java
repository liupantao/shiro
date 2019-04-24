/*
 * 
 */
package com.gsoft.shiro.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;



/**
 * 角色
 * 
 * @author liupeng
 *
 */
@Entity
@Table(name = "SYS_ROLE")
public class SysRole implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8648701341250942105L;

	/**
	 * 角色ID
	 */
	@Id
	@Column(name = "ID")
	@Length(max=32)
	private String roleId;

	/**
	 * 角色名称
	 */
	@Column(name = "ROLE_NAME")
	@Length(max = 80)
	private String roleName;

	/**
	 * 角色描述
	 */
	@Column(name = "ROLE_CAPTION")
	@Length(max = 512)
	private String roleCaption;

	/**
	 * 角色菜单
	 */
	@ManyToMany(targetEntity = SysMenu.class, fetch = FetchType.LAZY)
	@JoinTable(
			name = "SYS_ROLE_MENU", joinColumns = {
			@JoinColumn(name = "ROLE_ID") }, 
			inverseJoinColumns = @JoinColumn(name = "MENU_ID")
	)
	private List<SysMenu> menus;

	/**
	 * 角色权限
	 */ 
	@ManyToMany(targetEntity = SysPermission.class, fetch = FetchType.LAZY)
	@JoinTable(
			name = "SYS_ROLE_PERM", joinColumns = {
			@JoinColumn(name = "ROLE_ID") }, 
			inverseJoinColumns = @JoinColumn(name = "PERM_ID")
	)
	private List<SysPermission> perms;
	
	
	public String getRoleId() {
		return roleId;
	}

	
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	
	public String getRoleCaption() {
		return roleCaption;
	}

	
	public void setRoleCaption(String roleCaption) {
		this.roleCaption = roleCaption;
	}

	public List<SysMenu> getMenus() {
		return menus;
	}

	public void setMenus(List<SysMenu> menus) {
		this.menus = menus;
	}

	public List<SysPermission> getPerms() {
		return perms;
	}

	public void setPerms(List<SysPermission> perms) {
		this.perms = perms;
	}

	@Override
	public String toString() {
		return super.toString();
	}
}