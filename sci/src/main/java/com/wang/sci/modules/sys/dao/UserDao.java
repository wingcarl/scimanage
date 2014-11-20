package com.wang.sci.modules.sys.dao;

import java.util.Date;
import java.util.List;

import com.wang.sci.common.persistence.BaseDao;
import com.wang.sci.common.persistence.Parameter;
import com.wang.sci.modules.sys.entity.User;

public class UserDao extends BaseDao<User> {
	/**
	 * 返回所有的用户
	 * @return
	 */
	public List<User> findAllList() {
		return find("from User where delFlag=:p1 order by id",new Parameter(User.DEL_FLAG_NORMAL));
	}
	
	public User findByLoginName(String loginName){
		return getByHql("from User where loginName=:p1 and delFlag=:p2",new Parameter(loginName,User.DEL_FLAG_NORMAL));
	}
	
	public int updatePasswordById(String newPassword,String id){
		return update("update User set password=:p1 where id=:p2",new Parameter(newPassword,id));
	}

	public int updateLoginInfo(String loginIp, Date loginDate, String id){
		return update("update User set loginIp=:p1,loginDate=:p2 where id=:p3",new Parameter(loginIp,loginDate,id));
	}
}
