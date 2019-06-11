package com.eshequ.msa.finance.web.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.eshequ.msa.common.BaseResult;
import com.eshequ.msa.common.ResultCode;
import com.eshequ.msa.finance.model.MsaBaseAcctInfo;
import com.eshequ.msa.finance.service.MsaBaseAcctInfoService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/acctInfo")
public class MsaBaseAcctInfoController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MsaBaseAcctInfoController.class);
	
	@Autowired
	MsaBaseAcctInfoService msaBaseAcctInfoService;
	@Autowired
	ObjectMapper objectMapper;
	/**
	 * 获取全部主体账户信息
	 * @param request
	 * @return
	 */
	@ApiOperation(value="查询清算主体信息", notes="根据条件查询清算主体信息")
	@RequestMapping(value="/acctInfoList", method= RequestMethod.POST)
	@ResponseBody
	public BaseResult getAllAcctInfo(@RequestBody String data) {
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		MsaBaseAcctInfo msaBaseAcctInfo = new MsaBaseAcctInfo();
		try {
			if (!StringUtils.isEmpty(data)) {
				msaBaseAcctInfo = objectMapper.readValue(data, MsaBaseAcctInfo.class);
			}
			
			String entity_name = msaBaseAcctInfo.getEntityName();
			String company_name = msaBaseAcctInfo.getCompanyName();
			String account_name = msaBaseAcctInfo.getAccountName();
			String account_no = msaBaseAcctInfo.getAccountNo();
			String status = msaBaseAcctInfo.getStatus();
			String data_source = msaBaseAcctInfo.getDataSource();
			List<MsaBaseAcctInfo> list = msaBaseAcctInfoService.getAcctInfo(entity_name, company_name, account_name, account_no, status, data_source);
			return BaseResult.success(list);
		} catch (Exception e) {
			return BaseResult.failure(ResultCode.FAILURE(e.getMessage()));
		}
	}
	
	/**
	 * 根据主键查询信息
	 * @param data
	 * @return
	 */
	@ApiOperation(value="根据主键查询主体信息", notes="根据主键ID查询主体信息")
	@RequestMapping(value="/getAcctInfoById", method= RequestMethod.POST)
	@ResponseBody
	public BaseResult queryAcctInfoById(@RequestBody String data) {
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		MsaBaseAcctInfo msaBaseAcctInfo = new MsaBaseAcctInfo();
		try {
			if (!StringUtils.isEmpty(data)) {
				msaBaseAcctInfo = objectMapper.readValue(data, MsaBaseAcctInfo.class);
			}
			Long id = msaBaseAcctInfo.getId();
			if (StringUtils.isEmpty(id)) {
				return BaseResult.failure(ResultCode.FAILURE("查询的信息ID不能为空"));
			}
			msaBaseAcctInfo = msaBaseAcctInfoService.queryAcctInfoById(id);
			return BaseResult.success(msaBaseAcctInfo);
		} catch (Exception e) {
			return BaseResult.failure(ResultCode.FAILURE(e.getMessage()));
		}
	}
	
	/**
	 * 添加账户实体
	 * @param data
	 * @return
	 */
	@ApiOperation(value="添加清算主体信息", notes="添加清算主体信息")
	@RequestMapping(value="/addAcctInfo", method = RequestMethod.POST)
	@ResponseBody
	public BaseResult addAcctInfo(@RequestBody String data) {
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		MsaBaseAcctInfo msaBaseAcctInfo = new MsaBaseAcctInfo();
		
		try {
			if (!StringUtils.isEmpty(data)) {
				msaBaseAcctInfo = objectMapper.readValue(data, MsaBaseAcctInfo.class);
			}
			int i = msaBaseAcctInfoService.addAcctInfo(msaBaseAcctInfo);
			if (i<=0) {
				return BaseResult.failure(ResultCode.FAILURE("新增账户实体信息失败"));
			}
			return BaseResult.success();
		} catch (Exception e) {
			return BaseResult.failure(ResultCode.FAILURE(e.getMessage()));
		}
	}
	
	/**
	 * 删除清算主体实体
	 * @param data
	 */
	@ApiOperation(value="删除清算主体信息", notes="根据主键ID删除清算主体信息")
	@RequestMapping(value="/deleteAcctInfo", method = RequestMethod.POST)
	@ResponseBody
	public BaseResult deleteAcctInfo(@RequestBody String data) {
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		MsaBaseAcctInfo msaBaseAcctInfo = new MsaBaseAcctInfo();
		
		try {
			if (!StringUtils.isEmpty(data)) {
				msaBaseAcctInfo = objectMapper.readValue(data, MsaBaseAcctInfo.class);
			}
			Long id = msaBaseAcctInfo.getId();
			if (StringUtils.isEmpty(id)) {
				return BaseResult.failure(ResultCode.FAILURE("查询的信息ID不能为空"));
			}
			
			int i = msaBaseAcctInfoService.delAcctInfo(id);
			if (i<=0) {
				return BaseResult.failure(ResultCode.FAILURE("删除账户实体信息失败"));
			}
			return BaseResult.success();
		} catch (Exception e) {
			return BaseResult.failure(ResultCode.FAILURE(e.getMessage()));
		}
	}
	
}
