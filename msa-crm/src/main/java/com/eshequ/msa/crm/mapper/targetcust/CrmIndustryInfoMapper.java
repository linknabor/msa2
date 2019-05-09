package com.eshequ.msa.crm.mapper.targetcust;

import com.eshequ.msa.common.CommonMapper;
import com.eshequ.msa.crm.model.targetcust.CrmIndustryInfo;

public interface CrmIndustryInfoMapper extends CommonMapper<CrmIndustryInfo> {
    int deleteByPrimaryKey(Long industryId);

    int insert(CrmIndustryInfo record);

    int insertSelective(CrmIndustryInfo record);

    CrmIndustryInfo selectByPrimaryKey(Long industryId);

    int updateByPrimaryKeySelective(CrmIndustryInfo record);

    int updateByPrimaryKey(CrmIndustryInfo record);
}