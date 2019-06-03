package com.eshequ.msa.reconciliation.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eshequ.msa.reconciliation.service.cfg.CollectionCfg;
import com.eshequ.msa.reconciliation.service.impl.UnionPayCollectionServiceImpl;

@Component
public class ReconcilFactory {
	
	@Autowired
	private UnionPayCollectionServiceImpl unionPayCollectionServiceImpl;
	
	private static final Logger logger = LoggerFactory.getLogger(ReconcilFactory.class);  
	
	public ReconcilService getCollectionInstance(String type) {
		
		ReconcilService service = null;
		
		switch (type) {
		case CollectionCfg.UnionPay:
			service = unionPayCollectionServiceImpl;
			break;
//		case CollectionCfg.HuiFu;
//			service = huifuCollectionServiceImpl;
//			break;
		default:
			logger.error("no such type instance : " + type);
			break;
		}
		return service;
	
	}
	
}
