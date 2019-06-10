package com.eshequ.msa.reconciliation.service.dto;

import java.util.List;
import java.util.Map;

import com.eshequ.msa.reconciliation.model.MsaBaseCheckDetail;

public class ReconcilDTO {
	
	private Map<String, List<MsaBaseCheckDetail>> checkMap; //key为sect_id+"_"+"_"+mch_id+"_"+entity_id，value为对应的对账明细集合
	private Map<Long, String> sectMap;	//key->sect_id, value->sect_name
	private Map<Long, String> cspMap;		//key->csp_id, value->csp_name
	private Map<Long, Long> cspSectMap;	//key->sect_id, value->csp_id
	
	public Map<String, List<MsaBaseCheckDetail>> getCheckMap() {
		return checkMap;
	}
	public void setCheckMap(Map<String, List<MsaBaseCheckDetail>> checkMap) {
		this.checkMap = checkMap;
	}
	public Map<Long, String> getSectMap() {
		return sectMap;
	}
	public void setSectMap(Map<Long, String> sectMap) {
		this.sectMap = sectMap;
	}
	public Map<Long, String> getCspMap() {
		return cspMap;
	}
	public void setCspMap(Map<Long, String> cspMap) {
		this.cspMap = cspMap;
	}
	public Map<Long, Long> getCspSectMap() {
		return cspSectMap;
	}
	public void setCspSectMap(Map<Long, Long> cspSectMap) {
		this.cspSectMap = cspSectMap;
	}
	
	
	

}
