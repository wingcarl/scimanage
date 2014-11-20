package com.wang.sci.common.persistence;

import javax.persistence.PrePersist;

public class DataEntity<T> extends BaseEntity<T>{

	private static final long serialVersionUID = 1L;

	@PrePersist
	public void prePesist(){
		
	}
	
}
