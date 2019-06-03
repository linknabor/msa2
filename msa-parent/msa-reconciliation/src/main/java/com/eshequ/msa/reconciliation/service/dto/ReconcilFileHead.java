/**
 * 
 */
package com.eshequ.msa.reconciliation.service.dto;

/**
 *所有对账的文件头都应该继承这个类，抽取共有的部分作为父类，特有的或者需要特殊处理的属性在子类在重写
 * @author david
 *
 */
public class ReconcilFileHead {
	
	private String tranDate;
	private String totalCount;
	private String totalTranAmt;
	private String totalFeeAmt;
	private String totalLiquiateAmt;
	
	/**
	 * 
	 */
	public ReconcilFileHead() {
		// TODO Auto-generated constructor stub
	}

	public String getTranDate() {
		return tranDate;
	}

	public void setTranDate(String tranDate) {
		this.tranDate = tranDate;
	}

	public String getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}

	public String getTotalTranAmt() {
		return totalTranAmt;
	}

	public void setTotalTranAmt(String totalTranAmt) {
		this.totalTranAmt = totalTranAmt;
	}

	public String getTotalFeeAmt() {
		return totalFeeAmt;
	}

	public void setTotalFeeAmt(String totalFeeAmt) {
		this.totalFeeAmt = totalFeeAmt;
	}

	public String getTotalLiquiateAmt() {
		return totalLiquiateAmt;
	}

	public void setTotalLiquiateAmt(String totalLiquiateAmt) {
		this.totalLiquiateAmt = totalLiquiateAmt;
	}
	
	

}
