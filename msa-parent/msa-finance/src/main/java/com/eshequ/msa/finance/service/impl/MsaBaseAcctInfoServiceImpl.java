package com.eshequ.msa.finance.service.impl;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.eshequ.msa.codes.MchStatus;
import com.eshequ.msa.finance.mapper.custom.UnreconcilAcctInfoMapper;
import com.eshequ.msa.finance.mapper.normal.MsaBaseAcctInfoMapper;
import com.eshequ.msa.finance.model.MsaBaseAcctInfo;
import com.eshequ.msa.finance.service.MsaBaseAcctInfoService;
import com.eshequ.msa.util.SnowFlake;

@Service
@Transactional
public class MsaBaseAcctInfoServiceImpl implements MsaBaseAcctInfoService{

	private static final Logger LOGGER = LoggerFactory.getLogger(MsaBaseAcctInfoServiceImpl.class);
	
	@Autowired
	MsaBaseAcctInfoMapper msaBaseAcctInfoMapper;
	
	@Autowired
	UnreconcilAcctInfoMapper unreconcilAcctInfoMapper;
	
	@Autowired
	SnowFlake snowFlake;
	
	@Override
	public List<MsaBaseAcctInfo> getAcctInfo(String entity_name, String cust_name, String account_name,
			String account_no, String status) {
		return unreconcilAcctInfoMapper.getUnreconcilAcctInfo(entity_name, cust_name, account_name, account_no, status);
	}

	@Override
	public MsaBaseAcctInfo queryAcctInfoById(long id) {
		return msaBaseAcctInfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public int addAcctInfo(MsaBaseAcctInfo msaBaseAcctInfo) {
		msaBaseAcctInfo.setId(snowFlake.nextId());
		msaBaseAcctInfo.setStatus(MchStatus.WeiQiYong.toString());
		return msaBaseAcctInfoMapper.insertSelective(msaBaseAcctInfo);
	}

	@Override
	public int delAcctInfo(long id) {
		return msaBaseAcctInfoMapper.deleteByPrimaryKey(id);
	}

}
