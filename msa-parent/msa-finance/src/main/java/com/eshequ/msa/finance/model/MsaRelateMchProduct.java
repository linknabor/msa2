package com.eshequ.msa.finance.model;

import com.eshequ.msa.common.BaseModel;

public class MsaRelateMchProduct extends BaseModel {
    private Long mchId;

    private Long productId;

    public Long getMchId() {
        return mchId;
    }

    public void setMchId(Long mchId) {
        this.mchId = mchId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

	@Override
	public String toString() {
		return "MsaRelateMchProduct [mchId=" + mchId + ", productId=" + productId + "]";
	}
}