/**
 * 
 */
package com.eshequ.msa.common;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author davidhardson
 *
 */
public class BaseModel implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5785805718206190094L;

	@Transient
	@JsonIgnore
	private Integer page = 1;

	@Transient
	@JsonIgnore
	private Integer rows = 10;

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

}
