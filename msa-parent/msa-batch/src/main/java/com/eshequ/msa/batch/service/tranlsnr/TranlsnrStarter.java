package com.eshequ.msa.batch.service.tranlsnr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TranlsnrStarter {
	
	@Autowired
	private TranlsnrService tranlsnrService;
	
	public void startLisnter() {
		
		while(true) {
			tranlsnrService.syncData();
		}
		
	}

}
