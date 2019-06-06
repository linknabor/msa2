package com.eshequ.msa.finance.model;

import com.eshequ.msa.common.BaseModel;

public class MsaBaseProductInfo extends BaseModel {
    private Long id;

    private String productName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}