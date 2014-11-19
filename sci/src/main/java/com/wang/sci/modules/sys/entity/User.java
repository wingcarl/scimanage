package com.wang.sci.modules.sys.entity;

import java.util.Date;
import java.util.List;

import com.google.common.collect.Lists;
import com.wang.sci.common.persistence.IdEntity;

public class User extends IdEntity<User>{

	private static final long serialVersionUID = 1L;
	/**
	 * 登录名
	 */
	private String loginName;
	/**
	 * 登录密码
	 */
	private String password;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 电话号
	 */
	private String phone;
	/**
	 * 手机号
	 */
	private String mobile;
	/**
	 * 最后登录IP
	 */
	private String loginIp;
	/**
	 * 最后登录时间
	 */
	private Date loginDate;
	/**
	 * 所属组织机构
	 */
	private Office office;
	
	/**
	 * 所拥有的角色
	 */
	private List<Role> roleLists = Lists.newArrayList();
	
	
}
