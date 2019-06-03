package com.eshequ.msa.finance.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eshequ.msa.codes.MergerStatus;
import com.eshequ.msa.codes.PayChannel;
import com.eshequ.msa.codes.mapper.CodeInfoMapper;
import com.eshequ.msa.codes.model.CodeInfo;
import com.eshequ.msa.finance.mapper.MsaBaseAcctInfoMapper;
import com.eshequ.msa.finance.mapper.customize.UnreconcilTradeMchMapper;
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
	
	@Autowired
	private UnreconcilTradeMchMapper unreconcilTradeMchMapper;
	
	@Override
	public String sayHello() {

		List<CodeInfo> codeList = codeInfoMapper.selectAll();
		List<Map<String, String>> mapList = unreconcilTradeMchMapper.listUnreconcilTradeMch(MergerStatus.YiZhiFu.toString(), "20190501", PayChannel.UnionPay.toString());
		String codeStr = "";
		String acctStr = "";
		try {
			codeStr = objectMapper.writeValueAsString(codeList);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("code str is : " + codeStr);
		logger.info("acct str is " + acctStr);
		return codeStr;
	}

}
