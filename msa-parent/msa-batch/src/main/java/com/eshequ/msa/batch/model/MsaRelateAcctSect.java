package com.eshequ.msa.batch.model;

import com.eshequ.msa.common.BaseModel;

public class MsaRelateAcctSect extends BaseModel {
    private Long sectId;

    private Long acctId;

    public Long getSectId() {
        return sectId;
    }

    public void setSectId(Long sectId) {
        this.sectId = sectId;
    }

    public Long getAcctId() {
        return acctId;
    }

    public void setAcctId(Long acctId) {
        this.acctId = acctId;
    }
}