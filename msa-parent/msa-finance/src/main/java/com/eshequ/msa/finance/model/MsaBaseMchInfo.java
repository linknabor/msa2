package com.eshequ.msa.finance.model;

import com.eshequ.msa.common.BaseModel;
import java.math.BigDecimal;

public class MsaBaseMchInfo extends BaseModel {
    private Long id;

    private String mchNo;

    private String mchStatus;

    private String mchName;

    private String mchAbbre;

    private String secret;

    private String appid;

    private String methodType;

    private BigDecimal consultRate;

    private BigDecimal consultLimit;

    private String payProduct;

    private String payChannel;

    private String linkMan;

    private String certNo;

    private String tel;

    private String email;

    private String customerTel;

    private String mchAddr;

    private String industryType;

    private String remark;

    private String dataSource;

    private Long entityId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMchNo() {
        return mchNo;
    }

    public void setMchNo(String mchNo) {
        this.mchNo = mchNo;
    }

    public String getMchStatus() {
        return mchStatus;
    }

    public void setMchStatus(String mchStatus) {
        this.mchStatus = mchStatus;
    }

    public String getMchName() {
        return mchName;
    }

    public void setMchName(String mchName) {
        this.mchName = mchName;
    }

    public String getMchAbbre() {
        return mchAbbre;
    }

    public void setMchAbbre(String mchAbbre) {
        this.mchAbbre = mchAbbre;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMethodType() {
        return methodType;
    }

    public void setMethodType(String methodType) {
        this.methodType = methodType;
    }

    public BigDecimal getConsultRate() {
        return consultRate;
    }

    public void setConsultRate(BigDecimal consultRate) {
        this.consultRate = consultRate;
    }

    public BigDecimal getConsultLimit() {
        return consultLimit;
    }

    public void setConsultLimit(BigDecimal consultLimit) {
        this.consultLimit = consultLimit;
    }

    public String getPayProduct() {
        return payProduct;
    }

    public void setPayProduct(String payProduct) {
        this.payProduct = payProduct;
    }

    public String getPayChannel() {
        return payChannel;
    }

    public void setPayChannel(String payChannel) {
        this.payChannel = payChannel;
    }

    public String getLinkMan() {
        return linkMan;
    }

    public void setLinkMan(String linkMan) {
        this.linkMan = linkMan;
    }

    public String getCertNo() {
        return certNo;
    }

    public void setCertNo(String certNo) {
        this.certNo = certNo;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCustomerTel() {
        return customerTel;
    }

    public void setCustomerTel(String customerTel) {
        this.customerTel = customerTel;
    }

    public String getMchAddr() {
        return mchAddr;
    }

    public void setMchAddr(String mchAddr) {
        this.mchAddr = mchAddr;
    }

    public String getIndustryType() {
        return industryType;
    }

    public void setIndustryType(String industryType) {
        this.industryType = industryType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }
}