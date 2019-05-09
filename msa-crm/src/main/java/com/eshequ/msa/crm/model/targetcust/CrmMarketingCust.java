package com.eshequ.msa.crm.model.targetcust;

import java.math.BigDecimal;

import javax.persistence.Id;

import com.eshequ.msa.common.BaseModel;

public class CrmMarketingCust extends BaseModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    private Long custId;

    private BigDecimal houCount;

    private BigDecimal yearAmt;

    private String qualificationLevel;

    private String saleStatus;

    private String groupCom;

    private Long sysOperid;

    private String custName;

    private String custAddr;

    private String remark;

    private Long regionId;

    private Long cityId;

    private Long provinceId;

    private Long industryId;

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public BigDecimal getHouCount() {
        return houCount;
    }

    public void setHouCount(BigDecimal houCount) {
        this.houCount = houCount;
    }

    public BigDecimal getYearAmt() {
        return yearAmt;
    }

    public void setYearAmt(BigDecimal yearAmt) {
        this.yearAmt = yearAmt;
    }

    public String getQualificationLevel() {
        return qualificationLevel;
    }

    public void setQualificationLevel(String qualificationLevel) {
        this.qualificationLevel = qualificationLevel;
    }

    public String getSaleStatus() {
        return saleStatus;
    }

    public void setSaleStatus(String saleStatus) {
        this.saleStatus = saleStatus;
    }

    public String getGroupCom() {
        return groupCom;
    }

    public void setGroupCom(String groupCom) {
        this.groupCom = groupCom;
    }

    public Long getSysOperid() {
        return sysOperid;
    }

    public void setSysOperid(Long sysOperid) {
        this.sysOperid = sysOperid;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustAddr() {
        return custAddr;
    }

    public void setCustAddr(String custAddr) {
        this.custAddr = custAddr;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public Long getIndustryId() {
        return industryId;
    }

    public void setIndustryId(Long industryId) {
        this.industryId = industryId;
    }
}