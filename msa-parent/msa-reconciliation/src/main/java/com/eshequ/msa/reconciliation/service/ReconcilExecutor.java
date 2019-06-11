/**
 * 
 */
package com.eshequ.msa.reconciliation.service;

import java.util.List;

import com.eshequ.msa.reconciliation.service.dto.ReconcilFileDTO;

/**
 * @author david
 * @param <T>
 *
 */
public class ReconcilExecutor implements Runnable {

	private ReconcilService reconcilService;
	private String collectionDate;
	
	
	public ReconcilExecutor(String collectionDate, ReconcilService reconcilService) {
		super();
		this.collectionDate = collectionDate;
		this.reconcilService = reconcilService;
	}


	@Override
	public void run() {
		
		//1.下载文件
		List<String> pathList = reconcilService.downloadFile(collectionDate);
		for (String filePath : pathList) {
			//2.解析文件
			ReconcilFileDTO dto = reconcilService.parseFile(filePath);
			if (dto == null) {
				continue;
			}
			//3.文件落表
			reconcilService.saveFile2DB(dto);
		}
		
		
	}

}
