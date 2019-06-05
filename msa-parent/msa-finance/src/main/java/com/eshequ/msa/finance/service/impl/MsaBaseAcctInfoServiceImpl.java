package com.eshequ.msa.finance.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.eshequ.msa.finance.mapper.custom.UnreconcilAcctInfoMapper;
import com.eshequ.msa.finance.mapper.normal.MsaBaseAcctInfoMapper;
import com.eshequ.msa.finance.model.MsaBaseAcctInfo;
import com.eshequ.msa.finance.service.MsaBaseAcctInfoService;

@Service
@Transactional
public class MsaBaseAcctInfoServiceImpl implements MsaBaseAcctInfoService{

	private static final Logger LOGGER = LoggerFactory.getLogger(MsaBaseAcctInfoServiceImpl.class);
	
	@Autowired
	MsaBaseAcctInfoMapper msaBaseAcctInfoMapper;
	
	@Autowired
	UnreconcilAcctInfoMapper unreconcilAcctInfoMapper;
	
	@Override
	public List<MsaBaseAcctInfo> getAcctInfo(String entity_name, String cust_name, String account_name,
			String account_no, String status) {
		return unreconcilAcctInfoMapper.getUnreconcilAcctInfo(entity_name, cust_name, account_name, account_no, status);
	}

	@Override
	public MsaBaseAcctInfo queryAcctInfoById(String id) {
		return msaBaseAcctInfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public int addAcctInfo(MsaBaseAcctInfo msaBaseAcctInfo) {
		return msaBaseAcctInfoMapper.insertSelective(msaBaseAcctInfo);
	}

	@Override
	public int delAcctInfo(String id) {
		return msaBaseAcctInfoMapper.deleteByPrimaryKey(id);
	}

}
