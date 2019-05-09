package com.eshequ.msa.crm.model.repairmng;

import com.eshequ.msa.common.BaseModel;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

import javax.persistence.Id;

public class RepairAssign extends BaseModel {
    @Id
    private String repairAssignId;

    private String assignPepoleId;

    private String assignPepoleName;

    private String assignPepoleImg;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date assignDate;

    private String assignPepolePosition;
    
    private String assignPepolePhone;

    public String getRepairAssignId() {
        return repairAssignId;
    }

    public void setRepairAssignId(String repairAssignId) {
        this.repairAssignId = repairAssignId;
    }

    public String getAssignPepoleId() {
        return assignPepoleId;
    }

    public void setAssignPepoleId(String assignPepoleId) {
        this.assignPepoleId = assignPepoleId;
    }

    public String getAssignPepoleName() {
        return assignPepoleName;
    }

    public void setAssignPepoleName(String assignPepoleName) {
        this.assignPepoleName = assignPepoleName;
    }

    public String getAssignPepoleImg() {
        return assignPepoleImg;
    }

    public void setAssignPepoleImg(String assignPepoleImg) {
        this.assignPepoleImg = assignPepoleImg;
    }

    public Date getAssignDate() {
        return assignDate;
    }

    public void setAssignDate(Date assignDate) {
        this.assignDate = assignDate;
    }

    public String getAssignPepolePosition() {
        return assignPepolePosition;
    }

    public void setAssignPepolePosition(String assignPepolePosition) {
        this.assignPepolePosition = assignPepolePosition;
    }

	public String getAssignPepolePhone() {
		return assignPepolePhone;
	}

	public void setAssignPepolePhone(String assignPepolePhone) {
		this.assignPepolePhone = assignPepolePhone;
	}
    
}