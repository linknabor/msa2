package com.eshequ.msa.finance.web.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.eshequ.msa.codes.MchStatus;
import com.eshequ.msa.codes.model.CodeInfo;
import com.eshequ.msa.common.BaseResult;
import com.eshequ.msa.common.ResultCode;
import com.eshequ.msa.finance.model.MsaBaseAcctInfo;
import com.eshequ.msa.finance.service.MsaBaseAcctInfoService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/acctInfo")
public class MsaBaseAcctInfoController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MsaBaseAcctInfoController.class);
	
	@Autowired
	MsaBaseAcctInfoService msaBaseAcctInfoService;
	
	/**
	 * 获取全部实体账户信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/acctInfoList", method= RequestMethod.POST)
	@ResponseBody
	public BaseResult getAllAcctInfo(@RequestBody String data) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		MsaBaseAcctInfo msaBaseAcctInfo = new MsaBaseAcctInfo();
		try {
			if (!StringUtils.isEmpty(data)) {
				msaBaseAcctInfo = mapper.readValue(data, MsaBaseAcctInfo.class);
			}
			
			String entity_name = msaBaseAcctInfo.getEntityName();
			String cust_name = msaBaseAcctInfo.getCustName();
			String account_name = msaBaseAcctInfo.getAccountName();
			String account_no = msaBaseAcctInfo.getAccountNo();
			String status = msaBaseAcctInfo.getStatus();
			
			List<MsaBaseAcctInfo> list = msaBaseAcctInfoService.getAcctInfo(entity_name, cust_name, account_name, account_no, status);
			return BaseResult.success(list);
		} catch (Exception e) {
			return BaseResult.failure(ResultCode.FAILURE(e.getMessage()));
		}
	}
	
	/**
	 * 获取实体账户状态
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getAcctStatusCode", method= RequestMethod.POST)
	@ResponseBody
	public BaseResult getAcctCode(HttpServletRequest request) {
		List<CodeInfo> rspList = new ArrayList<CodeInfo>();
		try {
			rspList = MchStatus.getCodeList();
		}catch (Exception e) {
			BaseResult.failure(ResultCode.FAILURE(e.getMessage()));
		}
		return BaseResult.success(rspList);
	}
	
	/**
	 * 根据主键查询信息
	 * @param data
	 * @return
	 */
	@RequestMapping(value="/getAcctInfoById", method= RequestMethod.POST)
	@ResponseBody
	public BaseResult queryAcctInfoById(@RequestBody String data) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		MsaBaseAcctInfo msaBaseAcctInfo = new MsaBaseAcctInfo();
		try {
			if (!StringUtils.isEmpty(data)) {
				msaBaseAcctInfo = mapper.readValue(data, MsaBaseAcctInfo.class);
			}
			long id = msaBaseAcctInfo.getId();
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
	@RequestMapping(value="/addAcctInfo", method = RequestMethod.POST)
	@ResponseBody
	public BaseResult addAcctInfo(@RequestBody String data) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		MsaBaseAcctInfo msaBaseAcctInfo = new MsaBaseAcctInfo();
		
		try {
			if (!StringUtils.isEmpty(data)) {
				msaBaseAcctInfo = mapper.readValue(data, MsaBaseAcctInfo.class);
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
	 * 删除账户实体
	 * @param data
	 */
	@RequestMapping(value="/deleteAcctInfo", method = RequestMethod.POST)
	@ResponseBody
	public BaseResult deleteAcctInfo(@RequestBody String data) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		MsaBaseAcctInfo msaBaseAcctInfo = new MsaBaseAcctInfo();
		
		try {
			if (!StringUtils.isEmpty(data)) {
				msaBaseAcctInfo = mapper.readValue(data, MsaBaseAcctInfo.class);
			}
			long id = msaBaseAcctInfo.getId();
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
