package com.eshequ.msa.finance.web.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.eshequ.msa.common.BaseResult;
import com.eshequ.msa.common.ResultCode;
import com.eshequ.msa.finance.model.MsaBaseMchInfo;
import com.eshequ.msa.finance.model.MsaRelateMchProduct;
import com.eshequ.msa.finance.service.MsaBaseMchInfoService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/mchInfo")
public class MsaBaseMchInfoController {

	@Autowired
	MsaBaseMchInfoService msaBaseMchInfoService;
	@Autowired
	ObjectMapper objectMapper;
	/**
	 * 查询商户信息
	 * @param data
	 * @return
	 */
	@ApiOperation(value="查询商户信息", notes="根据条件查询商户信息")
	@RequestMapping(value="/queryMchInfo", method= RequestMethod.POST)
	@ResponseBody
	public BaseResult queryMchInfo(@RequestBody String data) {
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		MsaBaseMchInfo msaBaseMchInfo = new MsaBaseMchInfo();
		MsaRelateMchProduct msaRelateMchProduct = new MsaRelateMchProduct();
		try {
			if (!StringUtils.isEmpty(data)) {
				msaBaseMchInfo = objectMapper.readValue(data, MsaBaseMchInfo.class);
				msaRelateMchProduct = objectMapper.readValue(data, MsaRelateMchProduct.class);
			}
			
			String mch_name = msaBaseMchInfo.getMchName();
			String mch_no = msaBaseMchInfo.getMchNo();
			Long product_id = msaRelateMchProduct.getProductId();
			String method_type = msaBaseMchInfo.getMethodType();
			String mch_status = msaBaseMchInfo.getMchStatus();
			String data_source = msaBaseMchInfo.getDataSource();
			List<MsaBaseMchInfo> list = msaBaseMchInfoService.getMchInfo(mch_name, mch_no, product_id, method_type, mch_status, data_source);
			
			
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
	@ApiOperation(value="查询指定商户信息", notes="根据主键ID查询指定商户信息")
	@RequestMapping(value="/getMchInfoById", method= RequestMethod.POST)
	@ResponseBody
	public BaseResult queryMchInfoById(@RequestBody String data) {
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		MsaBaseMchInfo msaBaseMchInfo = new MsaBaseMchInfo();
		try {
			if (!StringUtils.isEmpty(data)) {
				msaBaseMchInfo = objectMapper.readValue(data, MsaBaseMchInfo.class);
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
	@ApiOperation(value="新增商户信息", notes="新增商户信息")
	@RequestMapping(value="/addMchInfo", method = RequestMethod.POST)
	@ResponseBody
	public BaseResult addMchInfo(@RequestBody String data) {
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		MsaBaseMchInfo msaBaseMchInfo = new MsaBaseMchInfo();
		MsaRelateMchProduct msaRelateMchProduct = new MsaRelateMchProduct();
		try {
			if (!StringUtils.isEmpty(data)) {
				msaBaseMchInfo = objectMapper.readValue(data, MsaBaseMchInfo.class);
				msaRelateMchProduct = objectMapper.readValue(data, MsaRelateMchProduct.class);
			}
			int i = msaBaseMchInfoService.addMchInfo(msaBaseMchInfo, msaRelateMchProduct);
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
	@ApiOperation(value="删除指定商户信息", notes="根据主键ID删除商户信息")
	@RequestMapping(value="/deleteMchInfo", method = RequestMethod.POST)
	@ResponseBody
	public BaseResult deleteMchInfo(@RequestBody String data) {
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		MsaBaseMchInfo msaBaseMchInfo = new MsaBaseMchInfo();
		try {
			if (!StringUtils.isEmpty(data)) {
				msaBaseMchInfo = objectMapper.readValue(data, MsaBaseMchInfo.class);
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
