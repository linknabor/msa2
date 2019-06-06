package com.eshequ.msa.finance.mapper.custom;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UnreconcilRelateMchCustMapper {

	public void delRelateMchCust(@Param("mch_id") long mch_id);
}
