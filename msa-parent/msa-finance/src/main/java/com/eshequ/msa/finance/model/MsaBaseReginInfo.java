package com.eshequ.msa.finance.model;

import com.eshequ.msa.common.BaseModel;

public class MsaBaseReginInfo extends BaseModel {
    private Long id;

    private String reginName;

    private String reginType;

    private Long superId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReginName() {
        return reginName;
    }

    public void setReginName(String reginName) {
        this.reginName = reginName;
    }

    public String getReginType() {
        return reginType;
    }

    public void setReginType(String reginType) {
        this.reginType = reginType;
    }

    public Long getSuperId() {
        return superId;
    }

    public void setSuperId(Long superId) {
        this.superId = superId;
    }
}