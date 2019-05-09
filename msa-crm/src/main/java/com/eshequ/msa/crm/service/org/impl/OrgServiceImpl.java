package com.eshequ.msa.crm.service.org.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eshequ.msa.crm.mapper.org.CrmSysOrgInfoMapper;
import com.eshequ.msa.crm.model.org.CrmSysOrgInfo;
import com.eshequ.msa.crm.service.org.OrgService;
import com.eshequ.msa.crm.vo.org.OrgVo;
import com.eshequ.msa.util.SnowFlake;

@Service
public class OrgServiceImpl implements OrgService{

	@Autowired
	private CrmSysOrgInfoMapper mapper;
	
	@Autowired
	private SnowFlake snowFlake;
	
	@Override
	@Transactional
	public void orgAdd(OrgVo vo) {

		CrmSysOrgInfo record = new CrmSysOrgInfo();
		BeanUtils.copyProperties(vo, record);
		record.setOrgId(snowFlake.nextId());
		mapper.insert(record);
	}

	@Override
	@Transactional
	public void orgEdit(OrgVo vo) {
		CrmSysOrgInfo record = new CrmSysOrgInfo();
		BeanUtils.copyProperties(vo, record);
		mapper.updateByPrimaryKey(record);
	}

	@Override
	@Transactional
	public void orgDel(OrgVo vo) {
		CrmSysOrgInfo record = new CrmSysOrgInfo();
		BeanUtils.copyProperties(vo, record);
		mapper.delete(record);
	}

	@Override
	@Transactional
	public void orgDelById(Long id) {
		mapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<CrmSysOrgInfo> orgQuery(OrgVo vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CrmSysOrgInfo> orgQueryByExample(OrgVo vo) {
		List<CrmSysOrgInfo> list = mapper.queryOrgByExample(vo);
		return list;
	}

	@Override
	public List<CrmSysOrgInfo> orgQueryAll() {
		List<CrmSysOrgInfo> list = mapper.selectAll();
		return list;
	}

}
