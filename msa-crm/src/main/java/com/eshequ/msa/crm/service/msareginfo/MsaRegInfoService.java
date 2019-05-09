package com.eshequ.msa.crm.service.msareginfo;

import java.util.List;


import com.eshequ.msa.codes.model.CodeInfo;
import com.eshequ.msa.common.BaseResult;
import com.eshequ.msa.common.User;
import com.eshequ.msa.crm.model.msareginfo.MsaRegInfo;

public interface MsaRegInfoService {
	// 添加企业注册信息
	BaseResult<?> addMsaInfo(MsaRegInfo masRegInfo);

	//根据企业注册id获取企业注册信息
	MsaRegInfo getMsaInfoById(Long regEnterpriseId);
	
    //根据type判断审核还是符合保存企业信息
	BaseResult<?> updateMsaInfo(MsaRegInfo msaRegInfo,String type,User user);
    
	//查询所有企业注册信息
	List<MsaRegInfo> getMsaInfoList(MsaRegInfo msaRegInfo);

	//查询产业版本
	List<CodeInfo> getProductVersion(String ciSpClass);
	
}
