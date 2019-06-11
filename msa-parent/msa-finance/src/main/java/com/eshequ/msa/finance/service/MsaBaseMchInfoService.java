package com.eshequ.msa.finance.service;

import java.util.List;
import com.eshequ.msa.finance.model.MsaBaseMchInfo;
import com.eshequ.msa.finance.model.MsaRelateMchProduct;

public interface MsaBaseMchInfoService {

	//根据条件查询指定记录
	List<MsaBaseMchInfo> getMchInfo(String mch_name, String mch_no, Long product_id, String method_type, String mch_status, String data_source);
	
	//根据主键查询信息
	MsaBaseMchInfo queryMchInfoById(Long id);
		
	//添加账户实体信息
	int addMchInfo(MsaBaseMchInfo msaBaseMchInfo, MsaRelateMchProduct msaRelateMchProductKey);
		
	//删除账户实体信息
	int delMchInfo(Long id);
	
}
