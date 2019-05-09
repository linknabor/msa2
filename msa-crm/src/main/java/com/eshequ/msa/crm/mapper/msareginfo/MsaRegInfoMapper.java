package com.eshequ.msa.crm.mapper.msareginfo;

import java.util.List;

import com.eshequ.msa.common.CommonMapper;
import com.eshequ.msa.crm.model.msareginfo.MsaRegInfo;

public interface MsaRegInfoMapper extends CommonMapper<MsaRegInfo>{
	List<MsaRegInfo> getMsaInfoList(MsaRegInfo msaRegInfo);

}
