package com.eshequ.msa.crm.mapper.repairmng;

import org.apache.ibatis.annotations.Param;

import com.eshequ.msa.common.CommonMapper;
import com.eshequ.msa.crm.model.repairmng.RepairAssign;

public interface RepairAssignMapper extends CommonMapper<RepairAssign>{

	int getByAssignPepoleId(@Param("assignPepoleId")String assignPepoleId);
	RepairAssign selectByAssignPepoleId(@Param("assignPepoleId")String assignPepoleId);

}
