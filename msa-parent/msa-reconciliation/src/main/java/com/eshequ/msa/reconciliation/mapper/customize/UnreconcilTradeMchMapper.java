package com.eshequ.msa.reconciliation.mapper.customize;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UnreconcilTradeMchMapper {

	public List<Map<String, String>> listUnreconcilTradeMch(String tranStatus, String tranDate, String payChannel);
}
