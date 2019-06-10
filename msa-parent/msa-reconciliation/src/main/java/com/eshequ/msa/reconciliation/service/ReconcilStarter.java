/**
 * 
 */
package com.eshequ.msa.reconciliation.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.stereotype.Service;

import com.eshequ.msa.reconciliation.service.cfg.ReconcilCfg;

/**
 * @author david
 * @param <T>
 *
 */
@Service
public class ReconcilStarter<T> {
	
	private ReconcilFactory collectionFactory;
	
	/**
	 * 入口程序
	 */
	public void start(String reconcilDate) {
		
		ExecutorService pool = Executors.newFixedThreadPool(ReconcilCfg.collectionQueue.size());	//几种对账就开几个线程。每个线程单独处理自己的任务
		for (String collectionType : ReconcilCfg.collectionQueue) {
			ReconcilService service = collectionFactory.getCollectionInstance(collectionType);
			pool.execute(new ReconcilExecutor<Object>(reconcilDate, service));
		}
		
	}
	
	

}
