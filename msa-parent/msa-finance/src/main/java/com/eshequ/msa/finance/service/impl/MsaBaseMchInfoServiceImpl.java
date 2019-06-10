package com.eshequ.msa.finance.service.impl;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import com.eshequ.msa.codes.MchStatus;
import com.eshequ.msa.finance.mapper.custom.UnreconcilMchInfoMapper;
import com.eshequ.msa.finance.mapper.custom.UnreconcilRelateMchCustMapper;
import com.eshequ.msa.finance.mapper.normal.MsaBaseMchInfoMapper;
import com.eshequ.msa.finance.mapper.normal.MsaRelateMchProductMapper;
import com.eshequ.msa.finance.model.MsaBaseMchInfo;
import com.eshequ.msa.finance.model.MsaRelateMchProduct;
import com.eshequ.msa.finance.service.MsaBaseMchInfoService;
import com.eshequ.msa.util.SnowFlake;

@Service
@Transactional
public class MsaBaseMchInfoServiceImpl implements MsaBaseMchInfoService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MsaBaseMchInfoServiceImpl.class);
	
	@Autowired
	MsaBaseMchInfoMapper msaBaseMchInfoMapper;
	@Autowired
	MsaRelateMchProductMapper msaRelateMchProductMapper;
	@Autowired
	UnreconcilMchInfoMapper unreconcilMchInfoMapper;
	@Autowired
	UnreconcilRelateMchCustMapper unreconcilRelateMchCustMapper;
	@Autowired
	SnowFlake snowFlake;
	
	@Override
	public List<MsaBaseMchInfo> getMchInfo(String mch_name, String mch_no, Long product_id, String method_type,
			String mch_status, String data_source) {
		return unreconcilMchInfoMapper.getUnreconcilMchInfo(mch_name, mch_no, product_id, method_type, mch_status, data_source);
	}

	@Override
	public MsaBaseMchInfo queryMchInfoById(Long id) {
		return msaBaseMchInfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public int addMchInfo(MsaBaseMchInfo msaBaseMchInfo, MsaRelateMchProduct msaRelateMchProductKey) {
		
		Long product_id = msaRelateMchProductKey.getProductId();
		if (StringUtils.isEmpty(product_id)) {
			return 0;
		}
		
		msaBaseMchInfo.setId(snowFlake.nextId());
		msaBaseMchInfo.setMchStatus(MchStatus.WeiQiYong.toString());
		msaBaseMchInfoMapper.insertSelective(msaBaseMchInfo);
		
		msaRelateMchProductKey.setMchId(msaBaseMchInfo.getId());
		msaRelateMchProductMapper.insertSelective(msaRelateMchProductKey);
		return 1;
	}

	@Override
	public int delMchInfo(Long id) {
		msaBaseMchInfoMapper.deleteByPrimaryKey(id);
		unreconcilRelateMchCustMapper.delRelateMchCust(id);
		return 1;
	}
}
