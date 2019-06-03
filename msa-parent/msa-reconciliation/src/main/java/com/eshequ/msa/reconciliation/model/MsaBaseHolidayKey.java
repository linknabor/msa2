package com.eshequ.msa.reconciliation.model;

import com.eshequ.msa.common.BaseModel;

public class MsaBaseHolidayKey extends BaseModel {
    private Long id;

    private String holiday;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHoliday() {
        return holiday;
    }

    public void setHoliday(String holiday) {
        this.holiday = holiday;
    }
}