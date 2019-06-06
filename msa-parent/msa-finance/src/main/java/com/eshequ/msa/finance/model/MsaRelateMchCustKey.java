package com.eshequ.msa.finance.model;

import com.eshequ.msa.common.BaseModel;

public class MsaRelateMchCustKey extends BaseModel {
    private Long sectId;

    private Long mchId;

    public Long getSectId() {
        return sectId;
    }

    public void setSectId(Long sectId) {
        this.sectId = sectId;
    }

    public Long getMchId() {
        return mchId;
    }

    public void setMchId(Long mchId) {
        this.mchId = mchId;
    }
}