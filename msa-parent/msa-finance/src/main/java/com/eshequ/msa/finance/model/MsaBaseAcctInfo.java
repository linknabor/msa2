package com.eshequ.msa.finance.model;

import com.eshequ.msa.common.BaseModel;
import java.math.BigDecimal;

import javax.persistence.Id;

public class MsaBaseAcctInfo extends BaseModel {
	@Id
    private String id;

    private BigDecimal liquidationCycle;

    private String entityName;

    private String bankName;

    private String accountName;

    private String accountNo;

    private String custName;

    private String province;

    private String phone;

    private String city;

    private String status;

    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getLiquidationCycle() {
        return liquidationCycle;
    }

    public void setLiquidationCycle(BigDecimal liquidationCycle) {
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

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

	@Override
	public String toString() {
		return "MsaBaseAcctInfo [id=" + id + ", liquidationCycle=" + liquidationCycle + ", entityName=" + entityName
				+ ", bankName=" + bankName + ", accountName=" + accountName + ", accountNo=" + accountNo + ", custName="
				+ custName + ", province=" + province + ", phone=" + phone + ", city=" + city + ", status=" + status
				+ ", remark=" + remark + "]";
	}
}