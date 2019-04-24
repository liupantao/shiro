/*
 * 
 */
package com.gsoft.shiro.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;


/**
 * 权限
 * 
 * @author liupeng
 *
 */
@Entity
@Table(name = "SYS_PERMISSION")
public class SysPermission implements Serializable {

	private static final long serialVersionUID = -4142006602728130644L;

	/**
	 * ID
	 */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid.hex")
	@Column(name = "ID")
	@Length(max = 32)
	private String id;

	/**
	 * 所属菜单
	 */
	@Column(name = "MENU_ID")
	@Length(max = 32)
	private String menuId;
	/**
	 * 权限标识
	 */
	@Column(name = "PERM")
	@Length(max = 20)
	private String perm;

	/**
	 * 权限名称
	 */
	@Column(name = "NAME")
	@Length(max = 60)
	private String name;

	/**
	 * 权限描述
	 */
	@Column(name = "CAPTION")
	@Length(max = 512)
	private String caption;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getPerm() {
		return perm;
	}

	public void setPerm(String perm) {
		this.perm = perm;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	@Override
	public String toString() {
		return super.toString();
	}
}