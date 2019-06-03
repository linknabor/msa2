package com.eshequ.msa.finance.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eshequ.msa.finance.mapper.MsaBaseMchInfoMapper;
import com.eshequ.msa.finance.model.MsaBaseMchInfo;
import com.eshequ.msa.finance.service.MsaBaseMchInfoService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@Transactional
public class MsaBaseMchInfoServiceImpl implements MsaBaseMchInfoService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MsaBaseMchInfoServiceImpl.class);
	
	@Autowired
	MsaBaseMchInfoMapper msaBaseMchInfoMapper;
	
	/**
	 * 查询所有商户列表
	 * @return
	 */
	public List<MsaBaseMchInfo> getMchInfo() {
		return msaBaseMchInfoMapper.selectAll();
	}
}
