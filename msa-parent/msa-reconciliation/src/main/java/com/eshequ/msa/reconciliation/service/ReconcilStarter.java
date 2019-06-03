/**
 * 
 */
package com.eshequ.msa.reconciliation.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eshequ.msa.reconciliation.service.cfg.CollectionCfg;

/**
 * @author david
 *
 */
@Service
public class ReconcilStarter {
	
	@Autowired
	private ReconcilFactory collectionFactory;
	
	/**
	 * 入口程序
	 */
	public void start(String collectionDate) {
		
		ExecutorService pool = Executors.newFixedThreadPool(CollectionCfg.collectionQueue.size());	//几种对账就开几个线程。每个线程单独处理自己的任务
		for (String collectionType : CollectionCfg.collectionQueue) {
			ReconcilService service = collectionFactory.getCollectionInstance(collectionType);
			pool.execute(new ReconcilExecutor<Object>(collectionDate, service));
		}
		
	}
	
	public void test() {
		
		ReconcilService service = collectionFactory.getCollectionInstance(CollectionCfg.collectionQueue.get(0));
		service.runReconcil();
	}
	
	

}
