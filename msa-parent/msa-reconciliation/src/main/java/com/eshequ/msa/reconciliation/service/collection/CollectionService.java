package com.eshequ.msa.reconciliation.service.collection;

import java.util.List;

import com.eshequ.msa.reconciliation.service.collection.dto.CollectionFileDTO;

/**
 * 对账业务逻辑处理
 * @author david
 *
 */
public interface CollectionService {
	
	/**
	 * 下载文件
	 */
	List<String> downloadFile(String collectionDate);
	
	/**
	 * 解析文件
	 * @param <T>
	 */
	CollectionFileDTO paseFile(String filePath);
	
	/**
	 * 保存对账处理
	 * @param <T>
	 */
	void collection(CollectionFileDTO dto); 
}
