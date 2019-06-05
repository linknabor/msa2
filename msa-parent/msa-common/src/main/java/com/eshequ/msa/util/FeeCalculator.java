/**
 * 
 */
package com.eshequ.msa.util;

import java.math.BigDecimal;

/**
 * @author david
 *
 */
public class FeeCalculator {
	
	/**
	 * 计算在线支付费率金额
	 * 先计算费率金额，保留五位小数。如果费率金额小于1分钱，则收1分钱，如果大于1分钱则按四舍五入计算。
	 * 费率金额=交易金额×支付费率
	 * @param tranAmt
	 * @param consultRate
	 * @return
	 	*/
	public static BigDecimal calculateConsultAmt(BigDecimal tranAmt, BigDecimal consultRate){
		
		BigDecimal consult_amt = tranAmt.multiply(consultRate).divide(new BigDecimal("100"), 5, BigDecimal.ROUND_HALF_UP);
		boolean isNegative = consult_amt.compareTo(BigDecimal.ZERO)<0;
		BigDecimal absAmt = consult_amt.abs();
		
		if (absAmt.compareTo(new BigDecimal("0.01"))<0) {
			absAmt = new BigDecimal("0.01");
		}
		consult_amt = isNegative?absAmt.multiply(new BigDecimal("-1")):absAmt;
		return consult_amt;
	
	}
	
	/**
	 * 计算渠道费率金额
	 * 先计算费率金额，保留五位小数。如果费率金额小于1分钱，则收1分钱，如果大于1分钱则按四舍五入计算。
	 * 渠道费率金额 = 交易金额×渠道费率
	 */
	public static BigDecimal calculateChannelAmt(BigDecimal tranAmt, BigDecimal channelRate){
		
		BigDecimal channel_amt = tranAmt.multiply(channelRate).divide(new BigDecimal("100"), 5, BigDecimal.ROUND_HALF_UP);
		if (channel_amt.compareTo(new BigDecimal("0.01"))<0) {
			channel_amt = new BigDecimal("0.01");
		}else {
			channel_amt = tranAmt.multiply(channelRate).divide(new BigDecimal("100"), 2, BigDecimal.ROUND_HALF_UP);
		}
		return channel_amt;
		
	}
	
	/**
	 * 根据手续费金额和交易金额，计算出手续费率
	 * @param feeAmt
	 * @param tranAmt
	 * @return
	 */
	public static BigDecimal calculateFeeRate(BigDecimal feeAmt, BigDecimal tranAmt) {
		
		BigDecimal feeRate = feeAmt.divide(tranAmt, 2, BigDecimal.ROUND_HALF_UP);
		return feeRate;
		
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
