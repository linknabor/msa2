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
	 * @param reconcilDate
	 * @return
	 */
	List<String> downloadFile(String batchDate);
	
	/**
	 * 解析文件
	 * @param filePath
	 * @return
	 */
	ReconcilFileDTO parseFile(String filePath);
	
	/**
	 * 处理对账文件并保存到数据库
	 * @param dto
	 */
	void saveFile2DB(ReconcilFileDTO dto);
	
	/**
	 * 对账处理
	 */
	void runReconcil();
}
