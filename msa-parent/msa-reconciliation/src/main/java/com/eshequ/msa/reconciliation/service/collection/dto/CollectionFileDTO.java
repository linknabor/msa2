/**
 * 
 */
package com.eshequ.msa.reconciliation.service.collection.dto;

import java.util.List;

/**
 * @author david
 *
 */
public class CollectionFileDTO {
	
	private CollectionHead head;
	private List<CollectionBody> body;

	/**
	 * 
	 */
	public CollectionFileDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public CollectionFileDTO(CollectionHead head, List<CollectionBody> body) {
		super();
		this.head = head;
		this.body = body;
	}

	public CollectionHead getHead() {
		return head;
	}

	public void setHead(CollectionHead head) {
		this.head = head;
	}

	public List<CollectionBody> getBody() {
		return body;
	}

	public void setBody(List<CollectionBody> body) {
		this.body = body;
	}

	
	

}
