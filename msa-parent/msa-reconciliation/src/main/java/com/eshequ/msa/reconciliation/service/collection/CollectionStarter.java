/**
 * 
 */
package com.eshequ.msa.reconciliation.service.collection;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eshequ.msa.reconciliation.service.collection.cfg.CollectionCfg;

/**
 * @author david
 *
 */
@Service
public class CollectionStarter {
	
	@Autowired
	private CollectionFactory collectionFactory;
	
	/**
	 * 入口程序
	 */
	public void start(String collectionDate) {
		
		ExecutorService pool = Executors.newFixedThreadPool(CollectionCfg.collectionQueue.size());	//几种对账就开几个线程。每个线程单独处理自己的任务
		for (String collectionType : CollectionCfg.collectionQueue) {
			CollectionService service = collectionFactory.getCollectionInstance(collectionType);
			pool.execute(new CollectionExecutor<Object>(collectionDate, service));
		}
		
	}
	
	

}
