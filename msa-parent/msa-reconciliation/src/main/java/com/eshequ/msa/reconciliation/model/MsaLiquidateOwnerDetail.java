package com.eshequ.msa.reconciliation.model;

import com.eshequ.msa.common.BaseModel;
import java.math.BigDecimal;

public class MsaLiquidateOwnerDetail extends BaseModel {
    private Long id;

    private Long orderId;

    private BigDecimal tranAmt;

    private String tranDate;

    private String tranTime;

    private String payMethod;

    private String platChanle;

    private BigDecimal consultRate;

    private BigDecimal consultAmt;

    private BigDecimal channelRate;

    private BigDecimal channelAmt;

    private String mchNo;

    private Long sectId;

    private String sectName;

    private Long cspId;

    private String cspName;

    private Long liquidateId;

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

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public String getPlatChanle() {
        return platChanle;
    }

    public void setPlatChanle(String platChanle) {
        this.platChanle = platChanle;
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

    public Long getSectId() {
        return sectId;
    }

    public void setSectId(Long sectId) {
        this.sectId = sectId;
    }

    public String getSectName() {
        return sectName;
    }

    public void setSectName(String sectName) {
        this.sectName = sectName;
    }

    public Long getCspId() {
        return cspId;
    }

    public void setCspId(Long cspId) {
        this.cspId = cspId;
    }

    public String getCspName() {
        return cspName;
    }

    public void setCspName(String cspName) {
        this.cspName = cspName;
    }

    public Long getLiquidateId() {
        return liquidateId;
    }

    public void setLiquidateId(Long liquidateId) {
        this.liquidateId = liquidateId;
    }
}