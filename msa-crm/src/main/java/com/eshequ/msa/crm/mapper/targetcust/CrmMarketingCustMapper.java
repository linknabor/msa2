package com.eshequ.msa.crm.mapper.targetcust;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.eshequ.msa.common.CommonMapper;
import com.eshequ.msa.crm.model.targetcust.CrmMarketingCust;
import com.eshequ.msa.crm.vo.targetcust.TargetCustVo;
@Mapper
public interface CrmMarketingCustMapper extends CommonMapper<CrmMarketingCust> {
    public List<CrmMarketingCust> queryCustByExample(TargetCustVo vo);
}