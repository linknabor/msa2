package com.eshequ.msa.finance.web.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.eshequ.msa.codes.ReginType;
import com.eshequ.msa.common.BaseResult;
import com.eshequ.msa.common.ResultCode;
import com.eshequ.msa.finance.model.MsaBaseReginInfo;
import com.eshequ.msa.finance.service.MsaBaseReginInfoService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/regin")
public class MsaBaseReginInfoController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MsaBaseReginInfoController.class);
	
	@Autowired
	MsaBaseReginInfoService msaBaseReginInfoService;
	@Autowired
	ObjectMapper objectMapper;
	
	/**
	 * 查询地区信息
	 * @param request
	 * @return
	 */
	@ApiOperation(value="查询地区信息", notes="查询地区信息")
	@RequestMapping(value="/getReginInfo", method= RequestMethod.POST)
	@ResponseBody
	public BaseResult getReginInfo(@RequestBody String data) {
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		MsaBaseReginInfo msaBaseReginInfo = new MsaBaseReginInfo();
		try {
			if (!StringUtils.isEmpty(data)) {
				msaBaseReginInfo = objectMapper.readValue(data, MsaBaseReginInfo.class);
			}
			
			String regin_type = msaBaseReginInfo.getReginType(); //区域名称
			Long super_id = msaBaseReginInfo.getSuperId(); //上级ID
			
			if (StringUtils.isEmpty(regin_type)) {
				return BaseResult.failure(ResultCode.FAILURE("区域类别不能为空"));
			}
			//regin_type = 1时，忽略super_id
			if (ReginType.Sheng.toString().equals(regin_type)) {
				super_id = null;
			}
			List<MsaBaseReginInfo> list = msaBaseReginInfoService.getReginInfoList(regin_type, super_id);
			return BaseResult.success(list);
		} catch (Exception e) {
			return BaseResult.failure(ResultCode.FAILURE(e.getMessage()));
		}
	}
}
