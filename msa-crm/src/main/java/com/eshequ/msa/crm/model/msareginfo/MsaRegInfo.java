package com.eshequ.msa.crm.model.msareginfo;

import com.eshequ.msa.common.BaseModel;
import java.math.BigDecimal;

import javax.persistence.Id;

public class MsaRegInfo extends BaseModel {
	@Id
    private Long regEnterpriseId;

    private String status;

    private String regDate;

    private String regTime;

    private String randomCode;

    private String loginName;

    private String enterpriseName;

    private String linkMan;

    private String orgTel;

    private String email;

    private String postCode;

    private String orgAddr;

    private String remark;

    private String productVersion;

    private String startDate;

    private String backTeName;

    private String industryType;

    private Long provinceId;

    private String provinceName;

    private Long cityId;

    private String cityName;

    private String environment;

    private BigDecimal sectNum;

    private BigDecimal userNum;

    private BigDecimal invoiceCount;

  

    public Long getRegEnterpriseId() {
		return regEnterpriseId;
	}

	public void setRegEnterpriseId(Long regEnterpriseId) {
		this.regEnterpriseId = regEnterpriseId;
	}

	public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getRegTime() {
        return regTime;
    }

    public void setRegTime(String regTime) {
        this.regTime = regTime;
    }

    public String getRandomCode() {
        return randomCode;
    }

    public void setRandomCode(String randomCode) {
        this.randomCode = randomCode;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getLinkMan() {
        return linkMan;
    }

    public void setLinkMan(String linkMan) {
        this.linkMan = linkMan;
    }

    public String getOrgTel() {
        return orgTel;
    }

    public void setOrgTel(String orgTel) {
        this.orgTel = orgTel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getOrgAddr() {
        return orgAddr;
    }

    public void setOrgAddr(String orgAddr) {
        this.orgAddr = orgAddr;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getProductVersion() {
        return productVersion;
    }

    public void setProductVersion(String productVersion) {
        this.productVersion = productVersion;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getBackTeName() {
        return backTeName;
    }

    public void setBackTeName(String backTeName) {
        this.backTeName = backTeName;
    }

    public String getIndustryType() {
        return industryType;
    }

    public void setIndustryType(String industryType) {
        this.industryType = industryType;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public BigDecimal getSectNum() {
        return sectNum;
    }

    public void setSectNum(BigDecimal sectNum) {
        this.sectNum = sectNum;
    }

    public BigDecimal getUserNum() {
        return userNum;
    }

    public void setUserNum(BigDecimal userNum) {
        this.userNum = userNum;
    }

    public BigDecimal getInvoiceCount() {
        return invoiceCount;
    }

    public void setInvoiceCount(BigDecimal invoiceCount) {
        this.invoiceCount = invoiceCount;
    }
}