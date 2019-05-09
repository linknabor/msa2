package com.eshequ.msa.crm.mapper.targetcust;

import com.eshequ.msa.common.CommonMapper;
import com.eshequ.msa.crm.model.targetcust.CrmChannelLink;
import com.eshequ.msa.crm.model.targetcust.CrmChannelLinkKey;

public interface CrmChannelLinkMapper extends CommonMapper<CrmChannelLink> {
    int deleteByPrimaryKey(CrmChannelLinkKey key);

    int insert(CrmChannelLink record);

    int insertSelective(CrmChannelLink record);

    CrmChannelLink selectByPrimaryKey(CrmChannelLinkKey key);

    int updateByPrimaryKeySelective(CrmChannelLink record);

    int updateByPrimaryKey(CrmChannelLink record);
}