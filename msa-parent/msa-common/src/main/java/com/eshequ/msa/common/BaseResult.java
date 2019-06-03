package com.eshequ.msa.common;

import java.io.Serializable;

public class BaseResult<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private String respCode;
	private String respDesc;
	private T result;
	
	public BaseResult (String respCode, String respDesc, T obj) {
		this.respCode = respCode;
		this.respDesc = respDesc;
		this.result = obj;
	}
	
	public String getRespCode() {
		return respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	public String getRespDesc() {
		return respDesc;
	}

	public void setRespDesc(String respDesc) {
		this.respDesc = respDesc;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

	
}
