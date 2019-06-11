package com.eshequ.msa.batch.service.reconciliation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eshequ.msa.batch.service.reconciliation.cfg.ReconcilCfg;
import com.eshequ.msa.batch.service.reconciliation.impl.UnionPayReconcilServiceImpl;

@Component
public class ReconcilFactory {
	
	@Autowired
	private UnionPayReconcilServiceImpl unionPayReconcilServiceImpl;
	
	private static final Logger logger = LoggerFactory.getLogger(ReconcilFactory.class);  
	
	public ReconcilService getCollectionInstance(String type) {
		
		ReconcilService service = null;
		
		switch (type) {
		case ReconcilCfg.UnionPay:
			service = unionPayReconcilServiceImpl;
			break;
//		case CollectionCfg.HuiFu;
//			service = huifuReconcilServiceImpl;
//			break;
		default:
			logger.error("no such type instance : " + type);
			break;
		}
		return service;
	
	}
	
}
