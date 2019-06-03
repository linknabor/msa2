package com.eshequ.msa.reconciliation.service;

import java.util.List;

import com.eshequ.msa.reconciliation.service.dto.ReconcilFileDTO;

/**
 * 对账业务逻辑处理
 * @author david
 *
 */
public interface ReconcilService {
	
	/**
	 * 下载文件
	 */
	List<String> downloadFile(String collectionDate);
	
	/**
	 * 解析文件
	 * @param <T>
	 */
	ReconcilFileDTO paseFile(String filePath);
	
	/**
	 * 保存文件到表
	 * @param <T>
	 */
	void collection(ReconcilFileDTO dto);
	
	/**
	 * 对账跑批
	 */
	void runReconcil();
}
