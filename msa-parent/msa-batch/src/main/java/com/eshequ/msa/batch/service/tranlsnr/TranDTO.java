package com.eshequ.msa.batch.service.tranlsnr;

public class TranDTO<T> {
	
	private String entityName;
	private T entity;
	public String getEntityName() {
		return entityName;
	}
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	public T getEntity() {
		return entity;
	}
	public void setEntity(T entity) {
		this.entity = entity;
	}
	
	
}
