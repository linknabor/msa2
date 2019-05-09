package com.eshequ.msa.crm.service.para;

import java.util.List;

import com.eshequ.msa.crm.model.sys.MsaCfgSysPara;
import com.eshequ.msa.crm.vo.para.ParaVo;

public interface ParaService {

	List<MsaCfgSysPara> paraQueryAll();
	void paraEdit(ParaVo vo);
	void paraAdd(ParaVo vo);
	void paraDelById(long id);
}
