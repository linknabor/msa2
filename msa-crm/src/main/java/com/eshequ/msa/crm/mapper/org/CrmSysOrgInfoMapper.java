package com.eshequ.msa.crm.mapper.org;

import java.util.List;

import com.eshequ.msa.common.CommonMapper;
import com.eshequ.msa.crm.model.org.CrmSysOrgInfo;
import com.eshequ.msa.crm.vo.org.OrgVo;

public interface CrmSysOrgInfoMapper extends CommonMapper<CrmSysOrgInfo>{
	public List<CrmSysOrgInfo> queryOrgByExample(OrgVo vo);
}
