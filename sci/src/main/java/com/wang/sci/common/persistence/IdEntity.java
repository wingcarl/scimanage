package com.wang.sci.common.persistence;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;

@MappedSuperclass
public abstract class IdEntity<T> implements Serializable{
	
	private static final long serialVersionUID = 1L;
	protected String id;
	
	public IdEntity(){
		super();
	}
	
	@PrePersist
	public void prePesist(){
		//super.prePesist();
		//this.id = "0";
	}

	@Id
	@GeneratedValue
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
