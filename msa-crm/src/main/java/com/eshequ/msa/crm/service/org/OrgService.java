package com.eshequ.msa.crm.service.org;

import java.util.List;

import com.eshequ.msa.crm.model.org.CrmSysOrgInfo;
import com.eshequ.msa.crm.vo.org.OrgVo;

public interface OrgService {
	 List<CrmSysOrgInfo> orgQuery(OrgVo vo);
	 List<CrmSysOrgInfo> orgQueryByExample(OrgVo vo);
	 List<CrmSysOrgInfo> orgQueryAll();
	 void orgAdd(OrgVo vo);
	 void orgEdit(OrgVo vo);
	 void orgDel(OrgVo vo);
	 void orgDelById(Long id);
}
