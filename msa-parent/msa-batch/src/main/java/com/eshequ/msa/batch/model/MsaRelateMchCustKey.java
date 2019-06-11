package com.eshequ.msa.batch.model;

import com.eshequ.msa.common.BaseModel;

public class MsaRelateMchCustKey extends BaseModel {
    private Long mchId;

    private Long custId;

    public Long getMchId() {
        return mchId;
    }

    public void setMchId(Long mchId) {
        this.mchId = mchId;
    }

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }
}