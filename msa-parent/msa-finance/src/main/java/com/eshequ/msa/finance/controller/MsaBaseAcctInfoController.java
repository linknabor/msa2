package com.eshequ.msa.finance.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eshequ.msa.common.BaseResult;
import com.eshequ.msa.finance.model.MsaBaseAcctInfo;
import com.eshequ.msa.finance.service.MsaBaseAcctInfoService;

@Controller
@RequestMapping("/acctInfo")
public class MsaBaseAcctInfoController {

	@Autowired
	MsaBaseAcctInfoService msaBaseAcctInfoService;
	
	@RequestMapping(value="/acctInfoList", method= RequestMethod.GET)
	@ResponseBody
	public BaseResult<Object> getAllAcctInfo(HttpServletRequest request) {
		List<MsaBaseAcctInfo> list = msaBaseAcctInfoService.getAcctInfoList();
		
		return new BaseResult<Object>("0000", "请求成功", list);
	}
}
