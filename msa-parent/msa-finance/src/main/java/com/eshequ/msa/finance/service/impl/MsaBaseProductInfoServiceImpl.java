package com.eshequ.msa.finance.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.eshequ.msa.finance.mapper.normal.MsaBaseProductInfoMapper;
import com.eshequ.msa.finance.model.MsaBaseProductInfo;
import com.eshequ.msa.finance.service.MsaBaseProductInfoService;

@Service
@Transactional
public class MsaBaseProductInfoServiceImpl implements MsaBaseProductInfoService{

	@Autowired
	MsaBaseProductInfoMapper msaBaseProductInfoMapper;
	
	@Override
	public List<MsaBaseProductInfo> getProductInfoList() {
		return msaBaseProductInfoMapper.selectAll();
	}

}
