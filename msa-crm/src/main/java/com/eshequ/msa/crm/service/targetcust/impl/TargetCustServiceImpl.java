package com.eshequ.msa.crm.service.targetcust.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eshequ.msa.crm.mapper.targetcust.CrmMarketingCustMapper;
import com.eshequ.msa.crm.model.targetcust.CrmMarketingCust;
import com.eshequ.msa.crm.service.targetcust.TargetCustService;
import com.eshequ.msa.crm.vo.targetcust.TargetCustVo;
import com.eshequ.msa.util.SnowFlake;
import com.github.pagehelper.PageHelper;

import tk.mybatis.mapper.entity.Example;

@Service
public class TargetCustServiceImpl implements TargetCustService{

	@Autowired
	private CrmMarketingCustMapper mapper;
	
	@Autowired
	private SnowFlake snowFlake;
	
	//查询目标客户
	@Override
	@Transactional
	public List<CrmMarketingCust> targetcustQuery(TargetCustVo vo) {
		List<CrmMarketingCust> list =  mapper.queryCustByExample(vo);
		return list;
	}

	//新增目标客户
	@Override
	@Transactional
	public void targetcustAdd(TargetCustVo vo) {
		CrmMarketingCust record = new CrmMarketingCust();
		BeanUtils.copyProperties(vo, record);
		record.setCustId(snowFlake.nextId());
		mapper.insertSelective(record);
	}

	//编辑目标客户
	@Override
	@Transactional
	public void targetcustEdit(TargetCustVo vo) {
		CrmMarketingCust record = new CrmMarketingCust();
		BeanUtils.copyProperties(vo, record);
		mapper.updateByPrimaryKey(record);
	}

	//删除目标客户
	@Override
	@Transactional
	public void targetcustDel(TargetCustVo vo) {
		CrmMarketingCust record = new CrmMarketingCust();
		BeanUtils.copyProperties(vo, record);
		mapper.delete(record);
	}

	//根据主键删除目标客户
	@Override
	@Transactional
	public void targetcustDelById(Long id) {
		mapper.deleteByPrimaryKey(id);
	}

	//根据条件查询
	@Override
	public List<CrmMarketingCust> targetcustQueryByExample(TargetCustVo vo) {
		Example example = new Example(CrmMarketingCust.class);
		Example.Criteria criteria = example.createCriteria();
		//添加条件
//		criteria.andLike("custName", "%"+ vo.getCustName() + "%");
//		criteria.andLike("custAddr", "%"+ vo.getCustName() + "%");
		List<CrmMarketingCust> list = mapper.selectByExample(example);
		return list;
	}

	@Override
	public List<CrmMarketingCust> targetcustQueryAll() {
		PageHelper.startPage(0, 1, true);
		List<CrmMarketingCust> list = mapper.selectAll();
		return list;
	}

}
