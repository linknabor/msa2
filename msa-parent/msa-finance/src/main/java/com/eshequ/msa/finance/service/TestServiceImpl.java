package com.eshequ.msa.finance.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eshequ.msa.codes.mapper.CodeInfoMapper;
import com.eshequ.msa.codes.model.CodeInfo;
import com.eshequ.msa.finance.mapper.MsaBaseAcctInfoMapper;
import com.eshequ.msa.finance.model.MsaBaseAcctInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class TestServiceImpl implements TestService {
	
	private Logger logger = LoggerFactory.getLogger(TestServiceImpl.class);

	@Autowired
	private CodeInfoMapper codeInfoMapper;
	
	/**
	 * 这个用单例，不要NEW，线程安全
	 */
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private MsaBaseAcctInfoMapper msaBaseAcctInfoMapper;
	
	@Override
	public String sayHello() {

		List<CodeInfo> codeList = codeInfoMapper.selectAll();
		List<MsaBaseAcctInfo> acctList = msaBaseAcctInfoMapper.selectAll();
		String codeStr = "";
		String acctStr = "";
		try {
			codeStr = objectMapper.writeValueAsString(codeList);
			acctStr = objectMapper.writeValueAsString(acctList);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("code str is : " + codeStr);
		logger.info("acct str is " + acctStr);
		return codeStr;
	}

}
