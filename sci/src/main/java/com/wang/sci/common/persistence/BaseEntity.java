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
	
	
	
	
}
