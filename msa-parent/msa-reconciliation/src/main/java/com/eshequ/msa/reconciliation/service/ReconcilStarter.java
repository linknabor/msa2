/**
 * 
 */
package com.eshequ.msa.reconciliation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eshequ.msa.reconciliation.service.cfg.ReconcilCfg;

/**
 * @author david
 * @param <T>
 *
 */
@Service
public class ReconcilStarter {
	
	@Autowired
	private ReconcilFactory collectionFactory;
	
	/**
	 * 入口程序
	 * @throws InterruptedException 
	 */
	public void start(String reconcilDate) throws InterruptedException {
		
		for (String collectionType : ReconcilCfg.collectionQueue) {
			ReconcilService service = collectionFactory.getCollectionInstance(collectionType);
			Thread t = new Thread(new ReconcilExecutor(reconcilDate, service));
			t.start();
			t.join();	//主线程等待子线程结束一起返回
		}
		
	}
	
	/**
	 * 删除对账
	 * @param reconcilDate
	 */
	public void del(String reconcilDate) {
		
		for (String collectionType : ReconcilCfg.collectionQueue) {
			ReconcilService service = collectionFactory.getCollectionInstance(collectionType);
			service.del(reconcilDate);
		}
		
	}
	
	

}
