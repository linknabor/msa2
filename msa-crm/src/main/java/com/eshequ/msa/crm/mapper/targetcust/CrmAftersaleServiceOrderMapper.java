package com.eshequ.msa.crm.mapper.targetcust;

import com.eshequ.msa.crm.model.targetcust.CrmAftersaleServiceOrder;

public interface CrmAftersaleServiceOrderMapper {
    int deleteByPrimaryKey(String serviceOrderId);

    int insert(CrmAftersaleServiceOrder record);

    int insertSelective(CrmAftersaleServiceOrder record);

    CrmAftersaleServiceOrder selectByPrimaryKey(String serviceOrderId);

    int updateByPrimaryKeySelective(CrmAftersaleServiceOrder record);

    int updateByPrimaryKey(CrmAftersaleServiceOrder record);
}