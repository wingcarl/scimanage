package com.wang.sci.modules.sys.entity;

import java.util.List;

import com.google.common.collect.Lists;
import com.wang.sci.common.persistence.IdEntity;

public class Office extends IdEntity<Office>{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
/**
 * 父级编号
 */
	private Office parent;
	/**
	 * All parent office ids
	 */
	private String parentIds;
	private String name;
	/**
	 * 机构类型
	 * 0.Teaching Department 教学机构
	 * 1.Executive Department 行政机构	
	 * 2.Research institute 直属机构
	 * 3.Leader 领导机构
	 */
	private String type;
	private String address;
	/**
	*邮政编码
	**/
	private String zipCode;
	/**
	 * leader/management
	 */
	private String master;
	
	private String phone;
	private String fax;
	private String email;
	
	private List<User> userList = Lists.newArrayList();
	private List<Office> childList = Lists.newArrayList();
	
	public Office(){
		super();
	}
	public Office(String id){
		super();
		this.id  = id;
	}
	
	public Office getParent(){
		return parent;
	}
	
	public void setParent(Office parent){
		this.parent = parent;
	}
	public String getParentIds() {
		return parentIds;
	}
	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getMaster() {
		return master;
	}
	public void setMaster(String master) {
		this.master = master;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<User> getUserList() {
		return userList;
	}
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	public List<Office> getChildList() {
		return childList;
	}
	public void setChildList(List<Office> childList) {
		this.childList = childList;
	}
	
	
}
