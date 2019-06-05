/**
 * 
 */
package com.eshequ.msa.reconciliation.service.dto;

import java.beans.Transient;

import com.eshequ.msa.util.DateUtil;
import com.eshequ.msa.util.ObjectUtil;

/**
 * 所有对账的文件明细都应该继承这个类，抽取共有的部分作为父类，特有的或者需要特殊处理的属性在子类在重写，现在只有银联的，所以暂时都用这个类。有部分方法
 * @author david
 *
 */
public class ReconcilFileBody {

	private String acctDate;	//记账日期	yyyyMMdd
	private String parentMch;	//父商户号
	private String mchId;		//商户号
	private String payPro;		//支付产品
	private String tranType;	//交易类型：消费、退货
	private String orderId;		//商品订单号
	private String tranDateTime;	//交易时间yyyy-MM-dd hh:mm:ss,如果是退款，则为退款发起时间
	private String tranAmt;			//交易金额,单位分
	private String feeAmt;			//手续费，单位分
	private String liquidateAmt;	//清算金额，单位分
	private String oriOrderId;	//原商品订单号(可为空，在退款交易出现)
	private String oriTranDateTime;	//原交易时间(可为空，在退款交易出现)
	private String remark;
	
	/**
	 * 将yyyy-MM-dd hh:mm:ss 中的yyyy-MM-dd部分取出，并转换成yyyyMMdd
	 * @return
	 */
	@Transient
	public String getTranDate() {
		
		if (ObjectUtil.isEmpty(tranDateTime)) {
			return "";
		}
		String tranDate = tranDateTime.substring(0, tranDateTime.indexOf(" "));
		tranDate = DateUtil.format2DB(tranDate);
		return tranDate;
	}
	
	/**
	 * 将yyyy-MM-dd hh:mm:ss 中的hh:mm:ss部分取出，并转换成hhmmss
	 * @return
	 */
	@Transient
	public String getTranTime() {
		
		if (ObjectUtil.isEmpty(tranDateTime)) {
			return "";
		}
		String tranTime = tranDateTime.substring(tranDateTime.indexOf(" ")+1);
		tranTime = DateUtil.formatTime2DB(tranTime);
		return tranTime;
	}
	
	@Transient
	public String getOriTranDate() {
		
		if (ObjectUtil.isEmpty(oriTranDateTime)) {
			return "";
		}
		String oriTranDate = oriTranDateTime.substring(0, oriTranDateTime.indexOf(" "));
		oriTranDate = DateUtil.format2DB(oriTranDate);
		return oriTranDate;
	}
	
	@Transient
	public String getOriTranTime() {
		
		if (ObjectUtil.isEmpty(oriTranDateTime)) {
			return "";
		}
		String oriTranTime = oriTranDateTime.substring(oriTranDateTime.indexOf(" ")+1);
		oriTranTime = DateUtil.formatTime2DB(oriTranTime);
		return oriTranTime;
	}
	
	/**
	 * 
	 */
	public ReconcilFileBody() {
		// TODO Auto-generated constructor stub
	}
	
	public String getAcctDate() {
		return acctDate;
	}
	public void setAcctDate(String acctDate) {
		this.acctDate = acctDate;
	}
	public String getParentMch() {
		return parentMch;
	}

	public void setParentMch(String parentMch) {
		this.parentMch = parentMch;
	}

	public String getMchId() {
		return mchId;
	}

	public void setMchId(String mchId) {
		this.mchId = mchId;
	}
	
	public String getPayPro() {
		return payPro;
	}

	public void setPayPro(String payPro) {
		this.payPro = payPro;
	}

	public String getTranType() {
		return tranType;
	}

	public void setTranType(String tranType) {
		this.tranType = tranType;
	}
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getTranDateTime() {
		return tranDateTime;
	}

	public void setTranDateTime(String tranDateTime) {
		this.tranDateTime = tranDateTime;
	}
	
	public String getOriOrderId() {
		return oriOrderId;
	}

	public void setOriOrderId(String oriOrderId) {
		this.oriOrderId = oriOrderId;
	}

	public String getTranAmt() {
		return tranAmt;
	}

	public void setTranAmt(String tranAmt) {
		this.tranAmt = tranAmt;
	}

	public String getFeeAmt() {
		return feeAmt;
	}

	public void setFeeAmt(String feeAmt) {
		this.feeAmt = feeAmt;
	}

	public String getLiquidateAmt() {
		return liquidateAmt;
	}

	public void setLiquidateAmt(String liquidateAmt) {
		this.liquidateAmt = liquidateAmt;
	}

	public String getOriTranDateTime() {
		return oriTranDateTime;
	}

	public void setOriTranDateTime(String oriTranDateTime) {
		this.oriTranDateTime = oriTranDateTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	
}
