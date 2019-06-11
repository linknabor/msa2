package com.eshequ.msa.finance.service;

import java.util.List;

import com.eshequ.msa.finance.model.MsaLiquidateOwnerSum;

public interface MsaBaseLiquidateService {

	//查询清算汇总
	List<MsaLiquidateOwnerSum> getLiquidateList();
}
