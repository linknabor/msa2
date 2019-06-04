package com.eshequ.msa.reconciliation.mapper.custom;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
	public List<MsaBaseMchInfo> getUnreconcilTradeMch(@Param("tranStatus")String tranStatus, @Param("tranDate")String tranDate, 
			@Param("payChannel")String payChannel, @Param("mchStatus")String mchStatus);
}
