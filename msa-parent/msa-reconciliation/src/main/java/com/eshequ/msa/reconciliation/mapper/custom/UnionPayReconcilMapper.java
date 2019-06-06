package com.eshequ.msa.reconciliation.mapper.custom;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.eshequ.msa.reconciliation.model.MsaBaseAcctInfo;
import com.eshequ.msa.reconciliation.model.MsaBaseMchInfo;

@Mapper
public interface UnionPayReconcilMapper {

	/**
	 * 获取未清算交易的商户号
	 * @param tranStatus
	 * @param tranDate
	 * @param payChannel
	 * @return
	 */
	public List<MsaBaseMchInfo> getUnreconcilTradeMch(@Param("tranStatus") String tranStatus, @Param("tranDate") String tranDate, 
			@Param("payChannel") String payChannel, @Param("mchStatus") String mchStatus);


	/**
	 * 根据商户ID和小区ID获取清算信息主体
	 * @param status
	 * @param mchId
	 * @param custId
	 * @return
	 */
	public MsaBaseAcctInfo getAcctEntityByTrade(@Param("status") String status, @Param("mchId") String mchId, @Param("custId") String custId);

}
