package com.eshequ.msa.crm.service.msareginfo.impl;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.eshequ.msa.codes.RegInfoStatus;
import com.eshequ.msa.codes.mapper.CodeInfoMapper;
import com.eshequ.msa.codes.model.CodeInfo;
import com.eshequ.msa.common.BaseResult;
import com.eshequ.msa.common.User;
import com.eshequ.msa.crm.mapper.msareginfo.MsaRegInfoMapper;
import com.eshequ.msa.crm.model.msareginfo.MsaRegInfo;
import com.eshequ.msa.crm.service.msareginfo.MsaRegInfoService;
import com.eshequ.msa.util.DateUtil;
import com.eshequ.msa.util.SnowFlake;
import com.eshequ.msa.util.http.HttpUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@Transactional
public class MsaRegInfoImpl implements MsaRegInfoService {
	private static final Logger logger = LoggerFactory.getLogger(MsaRegInfoImpl.class);
	
	
	private final String REVIEWED="0";//判断参数是审核
	private final String RECHECK="1";//判断参数是复核
	@Autowired
	private  MsaRegInfoMapper msaRegInfoMapper;
	
	@Autowired
	private CodeInfoMapper codeInfoMapper;

	@Autowired
	private SnowFlake snowFlake;

	
	@Autowired
	private HttpUtil httpClientProxy;

	@Override
	public BaseResult<?> addMsaInfo(MsaRegInfo MasRegInfo) {
		MasRegInfo.setRegEnterpriseId(snowFlake.nextId());
		MasRegInfo.setRegDate(DateUtil.getSysDate());
		MasRegInfo.setRegTime(DateUtil.getSysTime());
		MasRegInfo.setStatus(RegInfoStatus.WeiShenHe.toString());
		if(msaRegInfoMapper.insertSelective(MasRegInfo)>0){
			return BaseResult.successResult("注册成功！");
		}
		return BaseResult.fail(0, "注册失败！");
	}

	@Override
	public MsaRegInfo getMsaInfoById(Long regEnterpriseId) {
		MsaRegInfo mri=new MsaRegInfo();
		mri.setRegEnterpriseId(regEnterpriseId);
		return msaRegInfoMapper.selectOne(mri);
	}

	@Override
	public BaseResult<?> updateMsaInfo(MsaRegInfo msaRegInfo, String type,User user) {
		String message="";
		if(REVIEWED.equals(type)){
			msaRegInfo.setStatus(RegInfoStatus.YiShengHe.toString());
		}else if(RECHECK.equals(type)){
			msaRegInfo.setStatus(RegInfoStatus.YiFuHE.toString());
		}
		if(user != null){
			msaRegInfo.setBackTeName(user.getUserName());
		}
		int count=msaRegInfoMapper.updateByPrimaryKeySelective(msaRegInfo);
		  if(count>0){
			if(REVIEWED.equals(type)){
				message="审核成功！";
			}else if(RECHECK.equals(type)){
				//TODO 调另一个系统接口返回id httpClientProxy
				 String url="";
				 ObjectMapper mapper = new ObjectMapper(); 
				 try {
					String requestJsonStr = mapper.writeValueAsString(msaRegInfoMapper.selectOne(msaRegInfo));
					httpClientProxy.doPost(url, requestJsonStr, "utf-8");
				 } catch (JsonProcessingException e) {
					logger.error(e.getMessage(),e);
				}
				message="复核成功！";
			}else{
				message="编辑成功！";
			}
				  return BaseResult.successResult(message);
	       }else{
	    	   if(REVIEWED.equals(type)){
					message="审核失败！";
				}else if(RECHECK.equals(type)){
					message="复核失败！";
				}else{
					message="编辑失败！";
				}
	       }
		return BaseResult.fail(message);
	}

	@Override
	public List<MsaRegInfo> getMsaInfoList(MsaRegInfo msaRegInfo) {
		
		return msaRegInfoMapper.getMsaInfoList(msaRegInfo);
	}

	@Override
	public List<CodeInfo> getProductVersion(String ciSpClass) {
		return codeInfoMapper.selectByClass(ciSpClass);
	}


}
