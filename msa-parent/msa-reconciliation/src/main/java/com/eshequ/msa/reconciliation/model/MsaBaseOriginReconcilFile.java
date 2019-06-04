package com.eshequ.msa.reconciliation.model;

import com.eshequ.msa.common.BaseModel;
import java.math.BigDecimal;

public class MsaBaseOriginReconcilFile extends BaseModel {
    private Long id;

    private String fileCreateDate;

    private String tranDate;

    private String tranTime;

    private String sysMchNo;

    private String mchName;

    private BigDecimal tranAmt;

    private BigDecimal feeAmt;

    private String payProduct;

    private String tranType;

    private Long orderId;

    private Long originOrderId;

    private String originTranDate;

    private String checkFlag;

    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileCreateDate() {
        return fileCreateDate;
    }

    public void setFileCreateDate(String fileCreateDate) {
        this.fileCreateDate = fileCreateDate;
    }

    public String getTranDate() {
        return tranDate;
    }

    public void setTranDate(String tranDate) {
        this.tranDate = tranDate;
    }

    public String getTranTime() {
        return tranTime;
    }

    public void setTranTime(String tranTime) {
        this.tranTime = tranTime;
    }

    public String getSysMchNo() {
        return sysMchNo;
    }

    public void setSysMchNo(String sysMchNo) {
        this.sysMchNo = sysMchNo;
    }

    public String getMchName() {
        return mchName;
    }

    public void setMchName(String mchName) {
        this.mchName = mchName;
    }

    public BigDecimal getTranAmt() {
        return tranAmt;
    }

    public void setTranAmt(BigDecimal tranAmt) {
        this.tranAmt = tranAmt;
    }

    public BigDecimal getFeeAmt() {
        return feeAmt;
    }

    public void setFeeAmt(BigDecimal feeAmt) {
        this.feeAmt = feeAmt;
    }

    public String getPayProduct() {
        return payProduct;
    }

    public void setPayProduct(String payProduct) {
        this.payProduct = payProduct;
    }

    public String getTranType() {
        return tranType;
    }

    public void setTranType(String tranType) {
        this.tranType = tranType;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOriginOrderId() {
        return originOrderId;
    }

    public void setOriginOrderId(Long originOrderId) {
        this.originOrderId = originOrderId;
    }

    public String getOriginTranDate() {
        return originTranDate;
    }

    public void setOriginTranDate(String originTranDate) {
        this.originTranDate = originTranDate;
    }

    public String getCheckFlag() {
        return checkFlag;
    }

    public void setCheckFlag(String checkFlag) {
        this.checkFlag = checkFlag;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}