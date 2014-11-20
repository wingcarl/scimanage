package com.wang.sci.common.persistence;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;

import com.wang.sci.common.utils.IdGen;

@MappedSuperclass
public abstract class IdEntity<T> extends DataEntity<T> implements Serializable{
	
	private static final long serialVersionUID = 1L;
	protected String id;
	
	public IdEntity(){
		super();
	}
	
	@PrePersist
	public void prePesist(){
		super.prePesist();
		this.id = IdGen.uuid();
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
