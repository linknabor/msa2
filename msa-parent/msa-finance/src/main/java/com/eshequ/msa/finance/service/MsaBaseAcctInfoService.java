package com.eshequ.msa.finance.service;

import java.util.List;

import com.eshequ.msa.finance.model.MsaBaseAcctInfo;

public interface MsaBaseAcctInfoService {

	//查询所有账户实体信息
	List<MsaBaseAcctInfo> getAcctInfoList();
}
