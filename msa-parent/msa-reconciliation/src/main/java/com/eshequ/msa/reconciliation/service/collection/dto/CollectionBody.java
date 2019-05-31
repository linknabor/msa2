/**
 * 
 */
package com.eshequ.msa.reconciliation.service.collection.dto;

/**
 * 所有对账的文件明细都应该继承这个类，抽取共有的部分作为父类，特有的或者需要特殊处理的属性在子类在重写，现在只有银联的，所以暂时都用这个类。有部分方法
 * @author david
 *
 */
public class CollectionBody {

	private String tranDate;
	private String parentMch;
	private String mchId;
	private String payMethod;	//支付产品
	private String tranType;	//交易类型：消费、退货
	private String tradeWaterId;	//商品订单号
	private String tranDateTime;	//yyyy-MM-dd hh:mm:ss,如果是退款，则为退款发起时间
	private String tranAmt;
	private String feeAmt;	//手续费
	private String liquidateAmt;	//清算金额
	private String oriTradeWaterId;	//原商品订单号(可为空，在退款交易出现)
	private String oriTranTime;	//原交易时间(可为空，在退款交易出现)
	private String remark;
	
	/**
	 * 
	 */
	public CollectionBody() {
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

	public String getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

	public String getTranType() {
		return tranType;
	}

	public void setTranType(String tranType) {
		this.tranType = tranType;
	}

	public String getTradeWaterId() {
		return tradeWaterId;
	}

	public void setTradeWaterId(String tradeWaterId) {
		this.tradeWaterId = tradeWaterId;
	}

	public String getTranDateTime() {
		return tranDateTime;
	}

	public void setTranDateTime(String tranDateTime) {
		this.tranDateTime = tranDateTime;
	}

	public String getOriTradeWaterId() {
		return oriTradeWaterId;
	}

	public void setOriTradeWaterId(String oriTradeWaterId) {
		this.oriTradeWaterId = oriTradeWaterId;
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
