package com.eshequ.msa.finance.mapper.custom;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.eshequ.msa.finance.model.MsaBaseReginInfo;

@Mapper
public interface UnreconcilReginInfoMapper {

	public List<MsaBaseReginInfo> getReginInfoList(@Param("regin_type") String regin_type, @Param("super_id") long super_id);
}
