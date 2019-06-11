package com.eshequ.msa.finance.mapper.custom;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.eshequ.msa.finance.model.MsaBaseMchInfo;

@Mapper
public interface UnreconcilMchInfoMapper {

	public List<MsaBaseMchInfo> getUnreconcilMchInfo(@Param("mch_name") String mch_name, @Param("mch_no") String mch_no, 
			@Param("product_id") Long product_id, @Param("method_type") String method_type, @Param("mch_status") String mch_status, 
			@Param("data_source") String data_source);
}
