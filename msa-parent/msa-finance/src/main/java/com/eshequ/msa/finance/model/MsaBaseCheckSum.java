package com.eshequ.msa.finance.model;

import com.eshequ.msa.common.BaseModel;
import java.math.BigDecimal;

public class MsaBaseCheckSum extends BaseModel {
    private Long id;

    private BigDecimal shouldPayAmt;

    private String shouldDate;

    private BigDecimal accountAmt;

    private String accountDate;

    private BigDecimal payNum;

    private String accountStatus;

    private String entityId;

    private String entityName;

    private String accountNo;

    private String remark;

    private Long mchId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getShouldPayAmt() {
        return shouldPayAmt;
    }

    public void setShouldPayAmt(BigDecimal shouldPayAmt) {
        this.shouldPayAmt = shouldPayAmt;
    }

    public String getShouldDate() {
        return shouldDate;
    }

    public void setShouldDate(String shouldDate) {
        this.shouldDate = shouldDate;
    }

    public BigDecimal getAccountAmt() {
        return accountAmt;
    }

    public void setAccountAmt(BigDecimal accountAmt) {
        this.accountAmt = accountAmt;
    }

    public String getAccountDate() {
        return accountDate;
    }

    public void setAccountDate(String accountDate) {
        this.accountDate = accountDate;
    }

    public BigDecimal getPayNum() {
        return payNum;
    }

    public void setPayNum(BigDecimal payNum) {
        this.payNum = payNum;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getMchId() {
        return mchId;
    }

    public void setMchId(Long mchId) {
        this.mchId = mchId;
    }
}