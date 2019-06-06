package com.eshequ.msa.finance.mapper.custom;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.eshequ.msa.finance.model.MsaBaseAcctInfo;

@Mapper
public interface UnreconcilAcctInfoMapper {

	public List<MsaBaseAcctInfo> getUnreconcilAcctInfo(@Param("entity_name") String entity_name, @Param("company_name") String company_name, 
			@Param("account_name") String account_name, @Param("account_no") String account_no, @Param("status") String status, 
			@Param("data_source") String data_source);
}
