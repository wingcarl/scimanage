/**
 * 
 */
package com.wang.sci.common.persistence;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

import com.wang.sci.modules.sys.entity.User;

/**
 * @author dell-pc
 *
 */
@MappedSuperclass
public abstract class BaseEntity<T> implements Serializable {
	public static final long serialVersionUID = 1L;
	
	protected User currentUser;
	protected Page<T> page;
	
	//删除标记（0：正常  1：删除  2：审核）
	public static final String DEL_FLAG_NORMAL = "0";
	public static final String DEL_FLAG_DELETE = "1";
	public static final String DEL_FLAG_AUDIT = "2";
	
	
}
