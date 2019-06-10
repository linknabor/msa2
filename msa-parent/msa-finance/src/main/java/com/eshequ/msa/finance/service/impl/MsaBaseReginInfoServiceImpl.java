package com.eshequ.msa.finance.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eshequ.msa.finance.mapper.custom.UnreconcilReginInfoMapper;
import com.eshequ.msa.finance.model.MsaBaseReginInfo;
import com.eshequ.msa.finance.service.MsaBaseReginInfoService;

@Service
@Transactional
public class MsaBaseReginInfoServiceImpl implements MsaBaseReginInfoService{

	@Autowired
	UnreconcilReginInfoMapper unreconcilReginInfoMapper;
	
	@Override
	public List<MsaBaseReginInfo> getReginInfoList(String regin_type, Long super_id) {
		return unreconcilReginInfoMapper.getReginInfoList(regin_type, super_id);
	}

}
