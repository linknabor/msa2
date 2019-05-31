/**
 * 
 */
package com.eshequ.msa.reconciliation.service.collection;

import java.util.List;

import com.eshequ.msa.reconciliation.service.collection.dto.CollectionFileDTO;

/**
 * @author david
 * @param <T>
 *
 */
public class CollectionExecutor<T> implements Runnable {

	private CollectionService collectionService;
	private String collectionDate;
	
	
	public CollectionExecutor(String collectionDate, CollectionService collectionService) {
		super();
		this.collectionDate = collectionDate;
		this.collectionService = collectionService;
	}


	@Override
	public void run() {

		List<String> pathList = collectionService.downloadFile(collectionDate);
		for (String filePath : pathList) {
			CollectionFileDTO dto = collectionService.paseFile(filePath);
			collectionService.collection(dto);
		}
		
		
	}

}
