package com.eshequ.msa.crm.model.targetcust;

import com.eshequ.msa.common.BaseModel;

public class CrmMarketingCustCall extends BaseModel {
    private String callId;

    private String callDate;

    private String callTime;

    private String callContent;

    private String sysOperid;

    private String lastStatus;

    private String currentStatus;

    private String mailFlg;

    private String partReason;

    private String buildType;

    private String sectKind;

    private Long custId;

    private String linkId;

    public String getCallId() {
        return callId;
    }

    public void setCallId(String callId) {
        this.callId = callId;
    }

    public String getCallDate() {
        return callDate;
    }

    public void setCallDate(String callDate) {
        this.callDate = callDate;
    }

    public String getCallTime() {
        return callTime;
    }

    public void setCallTime(String callTime) {
        this.callTime = callTime;
    }

    public String getCallContent() {
        return callContent;
    }

    public void setCallContent(String callContent) {
        this.callContent = callContent;
    }

    public String getSysOperid() {
        return sysOperid;
    }

    public void setSysOperid(String sysOperid) {
        this.sysOperid = sysOperid;
    }

    public String getLastStatus() {
        return lastStatus;
    }

    public void setLastStatus(String lastStatus) {
        this.lastStatus = lastStatus;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }

    public String getMailFlg() {
        return mailFlg;
    }

    public void setMailFlg(String mailFlg) {
        this.mailFlg = mailFlg;
    }

    public String getPartReason() {
        return partReason;
    }

    public void setPartReason(String partReason) {
        this.partReason = partReason;
    }

    public String getBuildType() {
        return buildType;
    }

    public void setBuildType(String buildType) {
        this.buildType = buildType;
    }

    public String getSectKind() {
        return sectKind;
    }

    public void setSectKind(String sectKind) {
        this.sectKind = sectKind;
    }

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public String getLinkId() {
        return linkId;
    }

    public void setLinkId(String linkId) {
        this.linkId = linkId;
    }
}