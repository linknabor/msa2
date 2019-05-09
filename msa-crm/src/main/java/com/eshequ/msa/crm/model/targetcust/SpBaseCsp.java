package com.eshequ.msa.crm.model.targetcust;

import java.math.BigDecimal;

import javax.persistence.Id;

public class SpBaseCsp {
	@Id
    private Long cspId;

    private String cspStatus;

    private String loginName;

    private String cspName;

    private String email;

    private String linkMan;

    private String orgTel;

    private String orgAddr;

    private String postCode;

    private String remark;

    private String productVersion;

    private BigDecimal userNum;

    private BigDecimal sectNum;

    private String startDate;

    private String backTeName;

    private Long regCspId;

    public Long getCspId() {
        return cspId;
    }

    public void setCspId(Long cspId) {
        this.cspId = cspId;
    }

    public String getCspStatus() {
        return cspStatus;
    }

    public void setCspStatus(String cspStatus) {
        this.cspStatus = cspStatus;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getCspName() {
        return cspName;
    }

    public void setCspName(String cspName) {
        this.cspName = cspName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getOrgAddr() {
        return orgAddr;
    }

    public void setOrgAddr(String orgAddr) {
        this.orgAddr = orgAddr;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
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

    public BigDecimal getUserNum() {
        return userNum;
    }

    public void setUserNum(BigDecimal userNum) {
        this.userNum = userNum;
    }

    public BigDecimal getSectNum() {
        return sectNum;
    }

    public void setSectNum(BigDecimal sectNum) {
        this.sectNum = sectNum;
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

    public Long getRegCspId() {
        return regCspId;
    }

    public void setRegCspId(Long regCspId) {
        this.regCspId = regCspId;
    }
}