/**
 * 
 */
package com.eshequ.msa.batch.service.reconciliation.dto;

import java.util.List;

/**
 * @author david
 *
 */
public class ReconcilFileDTO {
	
	private ReconcilFileHead head;
	private List<ReconcilFileBody> body;

	/**
	 * 
	 */
	public ReconcilFileDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public ReconcilFileDTO(ReconcilFileHead head, List<ReconcilFileBody> body) {
		super();
		this.head = head;
		this.body = body;
	}

	public ReconcilFileHead getHead() {
		return head;
	}

	public void setHead(ReconcilFileHead head) {
		this.head = head;
	}

	public List<ReconcilFileBody> getBody() {
		return body;
	}

	public void setBody(List<ReconcilFileBody> body) {
		this.body = body;
	}

	
	

}
