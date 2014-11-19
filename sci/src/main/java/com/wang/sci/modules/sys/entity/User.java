package com.wang.sci.modules.sys.entity;

import java.util.Date;
import java.util.List;

import com.google.common.collect.Lists;
import com.wang.sci.common.persistence.IdEntity;
import com.wang.sci.common.utils.Collections3;

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
	
	public User(){
		super();
	}
	public User(String id){
		this();
		this.id = id;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getLoginIp() {
		return loginIp;
	}
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	public Date getLoginDate() {
		return loginDate;
	}
	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}
	public Office getOffice() {
		return office;
	}
	public void setOffice(Office office) {
		this.office = office;
	}
	public List<Role> getRoleLists() {
		return roleLists;
	}
	public void setRoleLists(List<Role> roleLists) {
		this.roleLists = roleLists;
	}
	
	public List<String> getRoleIdList(){
		List<String> roleIdList = Lists.newArrayList();
		for(Role role : roleLists){
			roleIdList.add(role.getId());
		}
		return roleIdList;
	}
	
	public void setRoleIdList(List<String> roleIdList){
		roleLists = Lists.newArrayList();
		for(String roleId : roleIdList){
			Role role = new Role();
			role.setId(roleId);
			roleLists.add(role);
		}
	}
	public String getRoleNames() {
		return Collections3.extractToString(roleLists, "name", ",")	;
	}
}
