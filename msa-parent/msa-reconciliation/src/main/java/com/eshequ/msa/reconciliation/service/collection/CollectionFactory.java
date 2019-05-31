package com.eshequ.msa.reconciliation.service.collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eshequ.msa.reconciliation.service.collection.cfg.CollectionCfg;
import com.eshequ.msa.reconciliation.service.collection.impl.UnionPayCollectionServiceImpl;

@Component
public class CollectionFactory {
	
	@Autowired
	private UnionPayCollectionServiceImpl unionPayCollectionServiceImpl;
	
	private static final Logger logger = LoggerFactory.getLogger(CollectionFactory.class);  
	
	public CollectionService getCollectionInstance(String type) {
		
		CollectionService service = null;
		
		switch (type) {
		case CollectionCfg.UnionPay:
			service = unionPayCollectionServiceImpl;
			break;
		default:
			logger.error("no such type instance : " + type);
			break;
		}
		return service;
	
	}
	
}
