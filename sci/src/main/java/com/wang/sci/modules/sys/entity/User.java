package com.wang.sci.modules.sys.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import com.google.common.collect.Lists;
import com.wang.sci.common.persistence.IdEntity;
import com.wang.sci.common.utils.Collections3;

@Entity
@Table(name = "sys_user")
@DynamicInsert @DynamicUpdate
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
	 * 电子邮件
	 */
	private String email;
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
	
	@Length(min=1,max=100)
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
	@Length(min=1,max=100)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Length(min=1,max=100)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=1,max=100)
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Length(min=1,max=100)
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
	
	@Length(min=1,max=100)
	@Email
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="sys_user_role",joinColumns={ @JoinColumn(name = "user_id") },inverseJoinColumns={@JoinColumn(name="role_id")})
	@OrderBy("id")
	public List<Role> getRoleLists() {
		return roleLists;
	}
	public void setRoleLists(List<Role> roleLists) {
		this.roleLists = roleLists;
	}
	
	@Transient
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
	
	@Transient
	public String getRoleNames() {
		return Collections3.extractToString(roleLists, "name", ",")	;
	}
}
