package com.eshequ.msa.crm.model.targetcust;

import com.eshequ.msa.common.BaseModel;

public class CrmIndustryInfo extends BaseModel {
    private Long industryId;

    private String industryName;

    public Long getIndustryId() {
        return industryId;
    }

    public void setIndustryId(Long industryId) {
        this.industryId = industryId;
    }

    public String getIndustryName() {
        return industryName;
    }

    public void setIndustryName(String industryName) {
        this.industryName = industryName;
    }
}