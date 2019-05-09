package com.eshequ.msa.crm.mapper.repairmng;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.eshequ.msa.common.CommonMapper;
import com.eshequ.msa.crm.model.repairmng.FileMng;

public interface FileMngMapper extends CommonMapper<FileMng>{

	int  seleteByRepairId(@Param("repairId")String repairId);

	List<FileMng> getRepairOrderById(@Param("repairId")String repairId);

}
