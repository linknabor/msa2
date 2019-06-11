package com.eshequ.msa.reconciliation.model;

import com.eshequ.msa.common.BaseModel;
import java.math.BigDecimal;

public class MsaLiquidateOwnerSum extends BaseModel {
    private Long id;

    private String bankName;

    private String acctName;

    private String acctNo;

    private String linkMan;

    private String province;

    private String city;

    private String phone;

    private String entityName;

    private String liquidateDate;

    private String liquidateTime;

    private BigDecimal liquidateAmt;

    private BigDecimal tranAmt;

    private String liquidateStatus;

    private Integer liquidateCount;

    private String operName;

    private String liquidateContent;

    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAcctName() {
        return acctName;
    }

    public void setAcctName(String acctName) {
        this.acctName = acctName;
    }

    public String getAcctNo() {
        return acctNo;
    }

    public void setAcctNo(String acctNo) {
        this.acctNo = acctNo;
    }

    public String getLinkMan() {
        return linkMan;
    }

    public void setLinkMan(String linkMan) {
        this.linkMan = linkMan;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getLiquidateDate() {
        return liquidateDate;
    }

    public void setLiquidateDate(String liquidateDate) {
        this.liquidateDate = liquidateDate;
    }

    public String getLiquidateTime() {
        return liquidateTime;
    }

    public void setLiquidateTime(String liquidateTime) {
        this.liquidateTime = liquidateTime;
    }

    public BigDecimal getLiquidateAmt() {
        return liquidateAmt;
    }

    public void setLiquidateAmt(BigDecimal liquidateAmt) {
        this.liquidateAmt = liquidateAmt;
    }

    public BigDecimal getTranAmt() {
        return tranAmt;
    }

    public void setTranAmt(BigDecimal tranAmt) {
        this.tranAmt = tranAmt;
    }

    public String getLiquidateStatus() {
        return liquidateStatus;
    }

    public void setLiquidateStatus(String liquidateStatus) {
        this.liquidateStatus = liquidateStatus;
    }

    public Integer getLiquidateCount() {
        return liquidateCount;
    }

    public void setLiquidateCount(Integer liquidateCount) {
        this.liquidateCount = liquidateCount;
    }

    public String getOperName() {
        return operName;
    }

    public void setOperName(String operName) {
        this.operName = operName;
    }

    public String getLiquidateContent() {
        return liquidateContent;
    }

    public void setLiquidateContent(String liquidateContent) {
        this.liquidateContent = liquidateContent;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}