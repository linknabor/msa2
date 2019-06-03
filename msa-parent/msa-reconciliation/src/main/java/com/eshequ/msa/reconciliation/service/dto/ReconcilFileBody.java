/**
 * 
 */
package com.eshequ.msa.reconciliation.service.dto;

/**
 * 所有对账的文件明细都应该继承这个类，抽取共有的部分作为父类，特有的或者需要特殊处理的属性在子类在重写，现在只有银联的，所以暂时都用这个类。有部分方法
 * @author david
 *
 */
public class ReconcilFileBody {

	private String tranDate;	//记账日期	yyyyMMdd
	private String parentMch;	//父商户号
	private String mchId;		//商户号
	private String payPro;		//支付产品
	private String tranType;	//交易类型：消费、退货
	private String orderId;		//商品订单号
	private String tranDateTime;	//yyyy-MM-dd hh:mm:ss,如果是退款，则为退款发起时间
	private String tranAmt;			//交易金额,单位分
	private String feeAmt;			//手续费，单位分
	private String liquidateAmt;	//清算金额，单位分
	private String oriOrderId;	//原商品订单号(可为空，在退款交易出现)
	private String oriTranTime;	//原交易时间(可为空，在退款交易出现)
	private String remark;
	
	/**
	 * 
	 */
	public ReconcilFileBody() {
		// TODO Auto-generated constructor stub
	}

	public String getTranDate() {
		return tranDate;
	}

	public void setTranDate(String tranDate) {
		this.tranDate = tranDate;
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

	public String getOriTranTime() {
		return oriTranTime;
	}

	public void setOriTranTime(String oriTranTime) {
		this.oriTranTime = oriTranTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	
}
