package com.eshequ.msa.finance.service;

import java.util.List;

import com.eshequ.msa.finance.model.MsaBaseAcctInfo;

public interface MsaBaseAcctInfoService {

	//根据条件查询指定记录
	List<MsaBaseAcctInfo> getAcctInfo(String entity_name, String company_name, String account_name, String account_no, String status, String data_source);
	
	//根据主键查询信息
	MsaBaseAcctInfo queryAcctInfoById(Long id);
	
	//添加账户实体信息
	int addAcctInfo(MsaBaseAcctInfo msaBaseAcctInfo);
	
	//删除账户实体信息
	int delAcctInfo(Long id);
}
