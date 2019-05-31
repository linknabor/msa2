package com.eshequ.msa.finance.model;

import com.eshequ.msa.common.BaseModel;

public class MsaRelateMchCust extends BaseModel {
    private Long custId;

    private Long mchId;

    private String custName;

    private String custAddr;

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public Long getMchId() {
        return mchId;
    }

    public void setMchId(Long mchId) {
        this.mchId = mchId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustAddr() {
        return custAddr;
    }

    public void setCustAddr(String custAddr) {
        this.custAddr = custAddr;
    }
}