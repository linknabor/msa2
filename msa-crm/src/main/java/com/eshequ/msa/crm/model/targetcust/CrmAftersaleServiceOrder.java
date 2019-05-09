package com.eshequ.msa.crm.model.targetcust;

import com.eshequ.msa.common.BaseModel;

public class CrmAftersaleServiceOrder extends BaseModel {
    private String serviceOrderId;

    private String orderServiceDate;

    private String dateFlag;

    private String timeQuantum;

    private String orderServiceObject;

    private String orderServiceType;

    private String orderServiceContent;

    private String remark;

    private String spSectId;

    private String linkId;

    private String afterSaleId;

    private Long custId;

    public String getServiceOrderId() {
        return serviceOrderId;
    }

    public void setServiceOrderId(String serviceOrderId) {
        this.serviceOrderId = serviceOrderId;
    }

    public String getOrderServiceDate() {
        return orderServiceDate;
    }

    public void setOrderServiceDate(String orderServiceDate) {
        this.orderServiceDate = orderServiceDate;
    }

    public String getDateFlag() {
        return dateFlag;
    }

    public void setDateFlag(String dateFlag) {
        this.dateFlag = dateFlag;
    }

    public String getTimeQuantum() {
        return timeQuantum;
    }

    public void setTimeQuantum(String timeQuantum) {
        this.timeQuantum = timeQuantum;
    }

    public String getOrderServiceObject() {
        return orderServiceObject;
    }

    public void setOrderServiceObject(String orderServiceObject) {
        this.orderServiceObject = orderServiceObject;
    }

    public String getOrderServiceType() {
        return orderServiceType;
    }

    public void setOrderServiceType(String orderServiceType) {
        this.orderServiceType = orderServiceType;
    }

    public String getOrderServiceContent() {
        return orderServiceContent;
    }

    public void setOrderServiceContent(String orderServiceContent) {
        this.orderServiceContent = orderServiceContent;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSpSectId() {
        return spSectId;
    }

    public void setSpSectId(String spSectId) {
        this.spSectId = spSectId;
    }

    public String getLinkId() {
        return linkId;
    }

    public void setLinkId(String linkId) {
        this.linkId = linkId;
    }

    public String getAfterSaleId() {
        return afterSaleId;
    }

    public void setAfterSaleId(String afterSaleId) {
        this.afterSaleId = afterSaleId;
    }

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }
}