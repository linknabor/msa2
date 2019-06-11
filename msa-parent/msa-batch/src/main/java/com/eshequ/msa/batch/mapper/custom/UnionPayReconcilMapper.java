package com.eshequ.msa.batch.mapper.custom;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.eshequ.msa.batch.model.MsaBaseAcctInfo;
import com.eshequ.msa.batch.model.MsaBaseMchInfo;

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
	
	/**
	 * 根据对账汇总ID获取对账明细的汇总
	 * @param checkId
	 * @return
	 */
	public Map<String,Object> getSumDetailByCheckId(@Param("checkId") long checkId);

}
