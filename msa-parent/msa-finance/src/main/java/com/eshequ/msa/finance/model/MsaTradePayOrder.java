package com.eshequ.msa.finance.model;

import com.eshequ.msa.common.BaseModel;
import java.math.BigDecimal;

public class MsaTradePayOrder extends BaseModel {
    private Long id;

    private String tranStatus;

    private BigDecimal consultRate;

    private BigDecimal consultAmt;

    private BigDecimal tranAmt;

    private String payMethod;

    private String tranDate;

    private String tranTime;

    private String acctDate;

    private String acctTime;

    private String needInvoice;

    private Long cspId;

    private String cspName;

    private Long sectId;

    private String sectName;

    private String platChannel;

    private String cardType;

    private String staffName;

    private BigDecimal ownerConsultAmt;

    private String outsideOrderId;

    private String fromSys;

    private String orderAttach;

    private String mchNo;

    private String mchName;

    private String mchAbbre;

    private String secret;

    private String appid;

    private String payChannel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTranStatus() {
        return tranStatus;
    }

    public void setTranStatus(String tranStatus) {
        this.tranStatus = tranStatus;
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

    public String getAcctDate() {
        return acctDate;
    }

    public void setAcctDate(String acctDate) {
        this.acctDate = acctDate;
    }

    public String getAcctTime() {
        return acctTime;
    }

    public void setAcctTime(String acctTime) {
        this.acctTime = acctTime;
    }

    public String getNeedInvoice() {
        return needInvoice;
    }

    public void setNeedInvoice(String needInvoice) {
        this.needInvoice = needInvoice;
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

    public String getPlatChannel() {
        return platChannel;
    }

    public void setPlatChannel(String platChannel) {
        this.platChannel = platChannel;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public BigDecimal getOwnerConsultAmt() {
        return ownerConsultAmt;
    }

    public void setOwnerConsultAmt(BigDecimal ownerConsultAmt) {
        this.ownerConsultAmt = ownerConsultAmt;
    }

    public String getOutsideOrderId() {
        return outsideOrderId;
    }

    public void setOutsideOrderId(String outsideOrderId) {
        this.outsideOrderId = outsideOrderId;
    }

    public String getFromSys() {
        return fromSys;
    }

    public void setFromSys(String fromSys) {
        this.fromSys = fromSys;
    }

    public String getOrderAttach() {
        return orderAttach;
    }

    public void setOrderAttach(String orderAttach) {
        this.orderAttach = orderAttach;
    }

    public String getMchNo() {
        return mchNo;
    }

    public void setMchNo(String mchNo) {
        this.mchNo = mchNo;
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

    public String getPayChannel() {
        return payChannel;
    }

    public void setPayChannel(String payChannel) {
        this.payChannel = payChannel;
    }
}