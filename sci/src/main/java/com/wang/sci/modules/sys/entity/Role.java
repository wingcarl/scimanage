package com.wang.sci.modules.sys.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.google.common.collect.Lists;
import com.wang.sci.common.persistence.IdEntity;


public class Role extends IdEntity<Role> {

	private static final long serialVersionUID = 1L;
	private String name;
	private String dataScope;
	
	private List<User> userList = Lists.newArrayList();
	/**
	 * 按照明细设置数据权限的范围
	 */
	private List<Office> officeList = Lists.newArrayList();
	
	public static final String DATA_SCOPE_ALL = "1";
	public static final String DATA_SCOPE_OFFICE_AND_CHILD="2";
	public static final String DATA_SCOPE_OFFICE="3";
	public static final String DATA_SCOPE_SELF="4";
	public static final String DATA_SCOPE_CUSTOM="5";
	
	public Role(){
		super();
		this.dataScope = DATA_SCOPE_CUSTOM;
	}
	
	public Role(String id,String name){
		this();
		this.id = id;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDataScope() {
		return dataScope;
	}

	public void setDataScope(String dataScope) {
		this.dataScope = dataScope;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public List<String> getUserIdList(){
		List<String> userIdList = Lists.newArrayList();
		for(User user : userList){
			userIdList.add(user.getId());
		}
		return userIdList;
	}
	
	public String getUserIds(){
		List<String> userIdList = Lists.newArrayList();
		for(User user : userList){
			userIdList.add(user.getId());
		}
		return StringUtils.join(userIdList,",");
	}
	public List<Office> getOfficeList() {
		return officeList;
	}
	
	public String getOfficeIds(){
		List<String> officeIdList = Lists.newArrayList();
		for(Office office : officeList){
			officeIdList.add(office.getId());
		}
		return StringUtils.join(officeIdList,",");
	}
	
	public List<String> getOfficeIdList(){
		List<String> officeIdList = Lists.newArrayList();
		for(Office office : officeList){
			officeIdList.add(office.getId());
		}
		return officeIdList;
	}
	
	/**
	 * 通过前端获取到officeId的列表来设置该角色拥有的数据权限范围。
	 * @param officeIdList
	 */
	public void setOfficeIdList(List<String> officeIdList){
		officeList = Lists.newArrayList();
		for(String officeId : officeIdList){
			Office office = new Office();
			office.setId(officeId);
			officeList.add(office);
		}
	}
	
	/**
	 * 根据传入的一个ID串来设置该角色的数据范围,每次传入都要先清空原先的officeList
	 * @param officeIds
	 */
	public void setOfficeIds(String officeIds){
		officeList = Lists.newArrayList();
		String[] officeIdList = StringUtils.split(officeIds,",");
		for(String officeId : officeIdList){
			Office office = new Office();
			office.setId(officeId);
			officeList.add(office);
		}
		
	}
	public void setOfficeList(List<Office> officeList) {
		this.officeList = officeList;
	}
	
	
	
}
