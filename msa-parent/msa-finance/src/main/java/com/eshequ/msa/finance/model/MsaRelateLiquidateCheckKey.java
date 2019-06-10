package com.eshequ.msa.finance.model;

import com.eshequ.msa.common.BaseModel;

public class MsaRelateLiquidateCheckKey extends BaseModel {
    private Long liquidateId;

    private Long checkId;

    public Long getLiquidateId() {
        return liquidateId;
    }

    public void setLiquidateId(Long liquidateId) {
        this.liquidateId = liquidateId;
    }

    public Long getCheckId() {
        return checkId;
    }

    public void setCheckId(Long checkId) {
        this.checkId = checkId;
    }
}