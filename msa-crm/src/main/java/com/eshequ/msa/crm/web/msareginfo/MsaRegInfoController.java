package com.eshequ.msa.crm.web.msareginfo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eshequ.msa.codes.model.CodeInfo;
import com.eshequ.msa.common.BaseResult;
import com.eshequ.msa.common.User;
import com.eshequ.msa.constants.Constants;
import com.eshequ.msa.crm.model.msareginfo.MsaRegInfo;
import com.eshequ.msa.crm.service.msareginfo.MsaRegInfoService;
import com.eshequ.msa.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@RestController
public class MsaRegInfoController {
	@Autowired
	private MsaRegInfoService msaRegInfoService;

	@RequestMapping(value = "/addOrUpdateMsaInfo", method = RequestMethod.POST)
	public BaseResult<?> addOrUpdateMsaInfo(@RequestBody MsaRegInfo msaRegInfo, String type,
			@ModelAttribute(Constants.USER) User user) {
		if (msaRegInfo != null) {
			if (msaRegInfo.getRegEnterpriseId() == null) {
				return msaRegInfoService.addMsaInfo(msaRegInfo);
			} else {
				if (!ObjectUtil.isEmpty(type)) {
					return msaRegInfoService.updateMsaInfo(msaRegInfo, type, user);
				}
			}
		}
		return BaseResult.fail(500, "参数错误！");
	}

	@RequestMapping(value = "/getProductVersion", method = RequestMethod.GET)
	public List<CodeInfo> getProductVersion() {
		return msaRegInfoService.getProductVersion("46");
	}

	@RequestMapping(value = "/getMsaInfoById", method = RequestMethod.GET)
	public MsaRegInfo getMsaInfoById(Long regEnterpriseId) {
		return msaRegInfoService.getMsaInfoById(regEnterpriseId);
	}

	@RequestMapping(value = "/getMsaInfoList", method = RequestMethod.GET)
	public PageInfo<MsaRegInfo> getMsaInfoList(@RequestParam(defaultValue = "0", required = false) int pageNum,
			@RequestParam(defaultValue = "10", required = false) int pageSize,@RequestBody MsaRegInfo msaRegInfo) {
		PageHelper.startPage(pageNum, pageSize);
		List<MsaRegInfo> lists = msaRegInfoService.getMsaInfoList(msaRegInfo);
		PageInfo<MsaRegInfo> pageInfo = new PageInfo<>(lists);
		return pageInfo;
	}

	
}
