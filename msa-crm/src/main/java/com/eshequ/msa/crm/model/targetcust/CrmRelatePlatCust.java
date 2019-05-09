package com.eshequ.msa.crm.model.targetcust;

import com.eshequ.msa.common.BaseModel;

public class CrmRelatePlatCust extends BaseModel {
    private Long custId;

    private String enterpriseId;

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }
}