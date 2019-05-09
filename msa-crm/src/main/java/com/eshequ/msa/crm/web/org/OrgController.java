package com.eshequ.msa.crm.web.org;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.eshequ.msa.crm.model.org.CrmSysOrgInfo;
import com.eshequ.msa.crm.service.org.OrgService;
import com.eshequ.msa.crm.vo.org.OrgVo;
import com.eshequ.msa.crm.web.BaseController;
@RestController
public class OrgController extends BaseController{

	@Autowired
	private OrgService orgService;
	
	@RequestMapping(value = "/orgQuery", method = RequestMethod.POST)
	public List<CrmSysOrgInfo> orgQuery(@RequestBody OrgVo vo){
		return orgService.orgQuery(vo);
	}
	
	@RequestMapping(value = "/orgQueryByExample", method = RequestMethod.POST)
	public List<CrmSysOrgInfo> orgQueryByExample(@RequestBody OrgVo vo){
		return orgService.orgQueryByExample(vo);
	}
	
	@RequestMapping(value = "/orgQueryAll", method = RequestMethod.POST)
	public List<CrmSysOrgInfo> orgQueryAll(){
		return orgService.orgQueryAll();
	}
	
	@RequestMapping(value = "/orgAdd", method = RequestMethod.POST)
	public void orgAdd(@RequestBody OrgVo vo){
		orgService.orgAdd(vo);
	}
	
	@RequestMapping(value = "/orgEdit", method = RequestMethod.POST)
	public void orgEdit(@RequestBody OrgVo vo){
		orgService.orgEdit(vo);
	}
	
	@RequestMapping(value = "/orgDel", method = RequestMethod.POST)
	public void orgDel(@RequestBody OrgVo vo){
		orgService.orgDel(vo);
	}
	
	@RequestMapping(value = "/orgDelById/{id}", method = RequestMethod.POST)
	public void orgDelById(@PathVariable long id){
		orgService.orgDelById(id);
	}
}
