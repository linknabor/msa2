package com.eshequ.msa.codes.model;

import com.eshequ.msa.common.BaseModel;

public class CodeInfo extends BaseModel{
	
    private String ciSpCode;

    private String ciSpClass;

    private String ciSpClassname;

    private String ciSpName;

    private String ciSpRemark;

    public String getCiSpCode() {
        return ciSpCode;
    }

    public void setCiSpCode(String ciSpCode) {
        this.ciSpCode = ciSpCode == null ? null : ciSpCode.trim();
    }

    public String getCiSpClass() {
        return ciSpClass;
    }

    public void setCiSpClass(String ciSpClass) {
        this.ciSpClass = ciSpClass == null ? null : ciSpClass.trim();
    }

    public String getCiSpClassname() {
        return ciSpClassname;
    }

    public void setCiSpClassname(String ciSpClassname) {
        this.ciSpClassname = ciSpClassname == null ? null : ciSpClassname.trim();
    }

    public String getCiSpName() {
        return ciSpName;
    }

    public void setCiSpName(String ciSpName) {
        this.ciSpName = ciSpName == null ? null : ciSpName.trim();
    }

    public String getCiSpRemark() {
        return ciSpRemark;
    }

    public void setCiSpRemark(String ciSpRemark) {
        this.ciSpRemark = ciSpRemark == null ? null : ciSpRemark.trim();
    }
}