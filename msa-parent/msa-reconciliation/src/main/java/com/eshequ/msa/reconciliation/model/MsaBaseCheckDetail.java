package com.eshequ.msa.reconciliation.model;

import com.eshequ.msa.common.BaseModel;
import java.math.BigDecimal;

public class MsaBaseCheckDetail extends BaseModel {
    private Long id;

    private Long orderId;

    private BigDecimal tranAmt;

    private String payMethod;

    private String payProduct;

    private String tranDate;

    private String tranTime;

    private BigDecimal consultRate;

    private BigDecimal consultAmt;

    private BigDecimal channelRate;

    private BigDecimal channelAmt;

    private String mchNo;

    private Long originOrderId;

    private String originTranDate;

    private String sectName;

    private Long sectId;

    private String cspName;

    private Long cspId;

    private Long checkId;

    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getTranAmt() {
        return tranAmt;
    }

    public void setTranAmt(BigDecimal tranAmt) {
        this.tranAmt = tranAmt;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public String getPayProduct() {
        return payProduct;
    }

    public void setPayProduct(String payProduct) {
        this.payProduct = payProduct;
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

    public BigDecimal getConsultRate() {
        return consultRate;
    }

    public void setConsultRate(BigDecimal consultRate) {
        this.consultRate = consultRate;
    }

    public BigDecimal getConsultAmt() {
        return consultAmt;
    }

    public void setConsultAmt(BigDecimal consultAmt) {
        this.consultAmt = consultAmt;
    }

    public BigDecimal getChannelRate() {
        return channelRate;
    }

    public void setChannelRate(BigDecimal channelRate) {
        this.channelRate = channelRate;
    }

    public BigDecimal getChannelAmt() {
        return channelAmt;
    }

    public void setChannelAmt(BigDecimal channelAmt) {
        this.channelAmt = channelAmt;
    }

    public String getMchNo() {
        return mchNo;
    }

    public void setMchNo(String mchNo) {
        this.mchNo = mchNo;
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

    public String getSectName() {
        return sectName;
    }

    public void setSectName(String sectName) {
        this.sectName = sectName;
    }

    public Long getSectId() {
        return sectId;
    }

    public void setSectId(Long sectId) {
        this.sectId = sectId;
    }

    public String getCspName() {
        return cspName;
    }

    public void setCspName(String cspName) {
        this.cspName = cspName;
    }

    public Long getCspId() {
        return cspId;
    }

    public void setCspId(Long cspId) {
        this.cspId = cspId;
    }

    public Long getCheckId() {
        return checkId;
    }

    public void setCheckId(Long checkId) {
        this.checkId = checkId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}