package com.wang.sci.modules.sys.entity;

import java.util.List;

import com.google.common.collect.Lists;
import com.wang.sci.common.persistence.IdEntity;

public class Office extends IdEntity<Office>{

	private Office parent;
	private String name;
	/**
	 * 机构类型
	 */
	private String type;
	private String address;
	private String zipCode;
	private String master;
	private String phone;
	private String fax;
	private String email;
	
	private List<User> userList = Lists.newArrayList();
	private List<Office> officeList = Lists.newArrayList();
}
