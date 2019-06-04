package com.eshequ.msa.finance.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eshequ.msa.finance.mapper.normal.MsaBaseAcctInfoMapper;
import com.eshequ.msa.finance.model.MsaBaseAcctInfo;
import com.eshequ.msa.finance.service.MsaBaseAcctInfoService;

@Service
@Transactional
public class MsaBaseAcctInfoServiceImpl implements MsaBaseAcctInfoService{

	@Autowired
	MsaBaseAcctInfoMapper msaBaseAcctInfoMapper;
	
	@Override
	public List<MsaBaseAcctInfo> getAcctInfoList() {
		return msaBaseAcctInfoMapper.selectAll();
	}
}
