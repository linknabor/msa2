package com.eshequ.msa.crm.vo.repairmng;

import java.util.List;

import com.eshequ.msa.crm.model.repairmng.FileMng;
import com.eshequ.msa.crm.model.repairmng.RepairAssign;
import com.eshequ.msa.crm.model.repairmng.RepairOrder;

public class RepairAndFileVo {
	private RepairOrder repairOrder;
	
	private RepairAssign repairAssign;
	
	private List<FileMng> list;

	public RepairOrder getRepairOrder() {
		return repairOrder;
	}

	public void setRepairOrder(RepairOrder repairOrder) {
		this.repairOrder = repairOrder;
	}

	public List<FileMng> getList() {
		return list;
	}

	public void setList(List<FileMng> list) {
		this.list = list;
	}

	public RepairAssign getRepairAssign() {
		return repairAssign;
	}

	public void setRepairAssign(RepairAssign repairAssign) {
		this.repairAssign = repairAssign;
	}
   
	
}
