package com.eshequ.msa.finance.web.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/liquidate")
public class MsaBaseLiquidateController {
	
	@ApiOperation(value="查询清算汇总信息", notes="查询清算汇总信息")
	@RequestMapping(value="/getLiquidateSum", method= RequestMethod.POST)
	@ResponseBody
	public void getLiquidateSum(@RequestBody String data) {
		
	}
	
	
}
