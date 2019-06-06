package com.eshequ.msa.finance.model;

public class MsaRelateMchCust extends MsaRelateMchCustKey {
    private Long cspId;

    private String custName;

    private String custAddr;

    public Long getCspId() {
        return cspId;
    }

    public void setCspId(Long cspId) {
        this.cspId = cspId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustAddr() {
        return custAddr;
    }

    public void setCustAddr(String custAddr) {
        this.custAddr = custAddr;
    }
}