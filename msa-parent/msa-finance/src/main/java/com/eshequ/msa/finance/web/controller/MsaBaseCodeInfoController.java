package com.eshequ.msa.finance.web.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.eshequ.msa.codes.DataSource;
import com.eshequ.msa.codes.MchStatus;
import com.eshequ.msa.codes.MethodType;
import com.eshequ.msa.codes.PayChannel;
import com.eshequ.msa.codes.ReginType;
import com.eshequ.msa.codes.model.CodeInfo;
import com.eshequ.msa.common.BaseResult;
import com.eshequ.msa.common.ResultCode;
import com.eshequ.msa.finance.model.MsaBaseProductInfo;
import com.eshequ.msa.finance.service.MsaBaseProductInfoService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/code")
public class MsaBaseCodeInfoController {

	@Autowired
	MsaBaseProductInfoService msaBaseProductInfoService;
	
	/**
	 * 商户状态
	 * @param request
	 * @return
	 */
	@ApiOperation(value="商户状态", notes="商户状态")
	@RequestMapping(value="/getMchStatus", method= RequestMethod.POST)
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
	 * 数据来源
	 * @param request
	 * @return
	 */
	@ApiOperation(value="数据来源", notes="数据来源")
	@RequestMapping(value="/getDataSource", method= RequestMethod.POST)
	@ResponseBody
	public BaseResult getDataSource(HttpServletRequest request) {
		List<CodeInfo> rspList = new ArrayList<CodeInfo>();
		try {
			rspList = DataSource.getCodeList();
		}catch (Exception e) {
			BaseResult.failure(ResultCode.FAILURE(e.getMessage()));
		}
		return BaseResult.success(rspList);
	}
	
	/**
	 * 区域类别
	 * @param request
	 * @return
	 */
	@ApiOperation(value="区域类别", notes="区域类别")
	@RequestMapping(value="/getReginType", method= RequestMethod.POST)
	@ResponseBody
	public BaseResult getReginType(HttpServletRequest request) {
		List<CodeInfo> rspList = new ArrayList<CodeInfo>();
		try {
			rspList = ReginType.getCodeList();
		}catch (Exception e) {
			BaseResult.failure(ResultCode.FAILURE(e.getMessage()));
		}
		return BaseResult.success(rspList);
	}
	
	/**
	 * 渠道类型
	 * @param request
	 * @return
	 */
	@ApiOperation(value="渠道类型", notes="渠道类型")
	@RequestMapping(value="/getMethodType", method= RequestMethod.POST)
	@ResponseBody
	public BaseResult getMethodType(HttpServletRequest request) {
		List<CodeInfo> rspList = new ArrayList<CodeInfo>();
		try {
			rspList = MethodType.getCodeList();
		}catch (Exception e) {
			BaseResult.failure(ResultCode.FAILURE(e.getMessage()));
		}
		return BaseResult.success(rspList);
	}
	
	/**
	 * 支付渠道
	 * @param request
	 * @return
	 */
	@ApiOperation(value="支付渠道", notes="支付渠道")
	@RequestMapping(value="/getPayChannel", method= RequestMethod.POST)
	@ResponseBody
	public BaseResult getPayChannel(HttpServletRequest request) {
		List<CodeInfo> rspList = new ArrayList<CodeInfo>();
		try {
			rspList = PayChannel.getCodeList();
		}catch (Exception e) {
			BaseResult.failure(ResultCode.FAILURE(e.getMessage()));
		}
		return BaseResult.success(rspList);
	}
	
	/**
	 * 支付产品
	 * @param request
	 * @return
	 */
	@ApiOperation(value="支付产品", notes="支付产品")
	@RequestMapping(value="/getProductItem", method= RequestMethod.POST)
	@ResponseBody
	public BaseResult getProductItem(HttpServletRequest request) {
		List<CodeInfo> rspList = new ArrayList<CodeInfo>();
		try {
			
			List<MsaBaseProductInfo> list = msaBaseProductInfoService.getProductInfoList();
			for (MsaBaseProductInfo msaBaseProductInfo : list) {
				long id = msaBaseProductInfo.getId();
				String product_name= msaBaseProductInfo.getProductName();
				CodeInfo codeInfo = new CodeInfo();
				codeInfo.setCiSpClassname("ProductItem");
				codeInfo.setCiSpCode(id+"");
				codeInfo.setCiSpName(product_name);
				rspList.add(codeInfo);
			}
		}catch (Exception e) {
			BaseResult.failure(ResultCode.FAILURE(e.getMessage()));
		}
		return BaseResult.success(rspList);
	}
	
}
