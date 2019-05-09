package com.eshequ.msa.crm.vo.targetcust;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Id;

public class TargetCustVo implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	private long custId;
	private String custName;
	private String custAddr;
	private String remark;
	private long regionId;
	private long cityId;
	private long provinceId;
	private long industryId;
	private BigDecimal houCount;
    private BigDecimal yearAmt;
    private String qualificationLevel;
    private String saleStatus;
    private String groupCom;
    private long sysOperid;
    private BigDecimal minHouCount;
    private BigDecimal maxHouCount;
	public long getCustId() {
		return custId;
	}
	public void setCustId(long custId) {
		this.custId = custId;
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
	public long getRegionId() {
		return regionId;
	}
	public void setRegionId(long regionId) {
		this.regionId = regionId;
	}
	public long getCityId() {
		return cityId;
	}
	public void setCityId(long cityId) {
		this.cityId = cityId;
	}
	public long getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(long provinceId) {
		this.provinceId = provinceId;
	}
	public long getIndustryId() {
		return industryId;
	}
	public void setIndustryId(long industryId) {
		this.industryId = industryId;
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
	public long getSysOperid() {
		return sysOperid;
	}
	public void setSysOperid(long sysOperid) {
		this.sysOperid = sysOperid;
	}
	public BigDecimal getMinHouCount() {
		return minHouCount;
	}
	public void setMinHouCount(BigDecimal minHouCount) {
		this.minHouCount = minHouCount;
	}
	public BigDecimal getMaxHouCount() {
		return maxHouCount;
	}
	public void setMaxHouCount(BigDecimal maxHouCount) {
		this.maxHouCount = maxHouCount;
	}
	
	
	
}
