package com.eshequ.msa.finance.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.eshequ.msa.finance.model.MsaBaseMchInfo;
import com.eshequ.msa.finance.model.MsaRelateMchCust;
import com.eshequ.msa.finance.service.MsaBaseMchInfoService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/mchInfo")
public class MsaBaseMchInfoController {

	@Autowired
	MsaBaseMchInfoService msaBaseMchInfoService;
	
	/**
	 * 查询商户信息
	 * @param data
	 * @return
	 */
	@RequestMapping(value="/queryMchInfo", method= RequestMethod.POST)
	@ResponseBody
	public BaseResult queryMchInfo(@RequestBody String data) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		MsaBaseMchInfo msaBaseMchInfo = new MsaBaseMchInfo();
		try {
			if (!StringUtils.isEmpty(data)) {
				msaBaseMchInfo = mapper.readValue(data, MsaBaseMchInfo.class);
			}
			
			String mch_name = msaBaseMchInfo.getMchName();
			String mch_no = msaBaseMchInfo.getMchNo();
			String pay_product = "";
			String method_type = msaBaseMchInfo.getMethodType();
			String mch_status = msaBaseMchInfo.getMchStatus();
			String data_source = msaBaseMchInfo.getDataSource();
			List<MsaBaseMchInfo> list = msaBaseMchInfoService.getMchInfo(mch_name, mch_no, pay_product, method_type, mch_status, data_source);
			return BaseResult.success(list);
		} catch (Exception e) {
			return BaseResult.failure(ResultCode.FAILURE(e.getMessage()));
		}
	}
	
	/**
	 * 获取商户状态
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getMchStatusCode", method= RequestMethod.POST)
	@ResponseBody
	public BaseResult getMchCode(HttpServletRequest request) {
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
	@RequestMapping(value="/getMchInfoById", method= RequestMethod.POST)
	@ResponseBody
	public BaseResult queryMchInfoById(@RequestBody String data) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		MsaBaseMchInfo msaBaseMchInfo = new MsaBaseMchInfo();
		try {
			if (!StringUtils.isEmpty(data)) {
				msaBaseMchInfo = mapper.readValue(data, MsaBaseMchInfo.class);
			}
			long id = msaBaseMchInfo.getId();
			if (StringUtils.isEmpty(id)) {
				return BaseResult.failure(ResultCode.FAILURE("查询的信息ID不能为空"));
			}
			msaBaseMchInfo = msaBaseMchInfoService.queryMchInfoById(id);
			return BaseResult.success(msaBaseMchInfo);
		} catch (Exception e) {
			return BaseResult.failure(ResultCode.FAILURE(e.getMessage()));
		}
	}
	
	/**
	 * 添加商户信息
	 * @param data
	 * @return
	 */
	@RequestMapping(value="/addMchInfo", method = RequestMethod.POST)
	@ResponseBody
	public BaseResult addMchInfo(@RequestBody String data) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		MsaBaseMchInfo msaBaseMchInfo = new MsaBaseMchInfo();
		MsaRelateMchCust msaRelateMchCust = new MsaRelateMchCust();
		try {
			if (!StringUtils.isEmpty(data)) {
				msaBaseMchInfo = mapper.readValue(data, MsaBaseMchInfo.class);
				msaRelateMchCust = mapper.readValue(data, MsaRelateMchCust.class);
			}
			int i = msaBaseMchInfoService.addMchInfo(msaBaseMchInfo, msaRelateMchCust);
			if (i<=0) {
				return BaseResult.failure(ResultCode.FAILURE("新增商户信息失败"));
			}
			return BaseResult.success();
		} catch (Exception e) {
			return BaseResult.failure(ResultCode.FAILURE(e.getMessage()));
		}
	}
	
	/**
	 * 删除商户信息
	 * @param data
	 */
	@RequestMapping(value="/deleteMchInfo", method = RequestMethod.POST)
	@ResponseBody
	public BaseResult deleteMchInfo(@RequestBody String data) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		MsaBaseMchInfo msaBaseMchInfo = new MsaBaseMchInfo();
		try {
			if (!StringUtils.isEmpty(data)) {
				msaBaseMchInfo = mapper.readValue(data, MsaBaseMchInfo.class);
			}
			long id = msaBaseMchInfo.getId();
			if (StringUtils.isEmpty(id)) {
				return BaseResult.failure(ResultCode.FAILURE("查询的信息ID不能为空"));
			}
			
			int i = msaBaseMchInfoService.delMchInfo(id);
			if (i<=0) {
				return BaseResult.failure(ResultCode.FAILURE("删除商户信息失败"));
			}
			return BaseResult.success();
		} catch (Exception e) {
			return BaseResult.failure(ResultCode.FAILURE(e.getMessage()));
		}
	}
}
