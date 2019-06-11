package com.eshequ.msa.finance.service;

import java.util.List;

import com.eshequ.msa.finance.model.MsaBaseReginInfo;

public interface MsaBaseReginInfoService {

	List<MsaBaseReginInfo> getReginInfoList(String regin_type, Long super_id);
}
