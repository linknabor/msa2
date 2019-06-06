package com.eshequ.msa.finance.model;

import javax.persistence.Id;

import com.eshequ.msa.common.BaseModel;

public class MsaBaseAcctInfo extends BaseModel {
	@Id
    private Long id;

    private Integer liquidationCycle;

    private String entityName;

    private String bankName;

    private String accountName;

    private String accountNo;

    private String province;

    private String phone;

    private String city;

    private String status;

    private String dataSource;

    private Long cspId;

    private String cspName;

    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLiquidationCycle() {
        return liquidationCycle;
    }

    public void setLiquidationCycle(Integer liquidationCycle) {
        this.liquidationCycle = liquidationCycle;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public Long getCspId() {
        return cspId;
    }

    public void setCspId(Long cspId) {
        this.cspId = cspId;
    }

    public String getCspName() {
        return cspName;
    }

    public void setCspName(String cspName) {
        this.cspName = cspName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}