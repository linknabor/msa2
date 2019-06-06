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
import com.eshequ.msa.codes.ReginType;
import com.eshequ.msa.common.BaseResult;
import com.eshequ.msa.common.ResultCode;
import com.eshequ.msa.finance.model.MsaBaseReginInfo;
import com.eshequ.msa.finance.service.MsaBaseReginInfoService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/regin")
public class MsaBaseReginInfoController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MsaBaseReginInfoController.class);
	
	@Autowired
	MsaBaseReginInfoService msaBaseReginInfoService;
	/**
	 * 获取全部实体账户信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getReginInfo", method= RequestMethod.POST)
	@ResponseBody
	public BaseResult getAllAcctInfo(@RequestBody String data) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		MsaBaseReginInfo msaBaseReginInfo = new MsaBaseReginInfo();
		try {
			if (!StringUtils.isEmpty(data)) {
				msaBaseReginInfo = mapper.readValue(data, MsaBaseReginInfo.class);
			}
			
			String regin_type = msaBaseReginInfo.getReginType(); //区域名称
			long super_id = msaBaseReginInfo.getSuperId(); //上级ID
			
			//regin_type = 1时，忽略super_id
			if (ReginType.Sheng.toString().equals(regin_type)) {
				super_id = -1;
			}
			List<MsaBaseReginInfo> list = msaBaseReginInfoService.getReginInfoList(regin_type, super_id);
			return BaseResult.success(list);
		} catch (Exception e) {
			return BaseResult.failure(ResultCode.FAILURE(e.getMessage()));
		}
	}
}
