/**
 * 
 */
package com.eshequ.msa.reconciliation.service.collection.cfg;

import java.util.ArrayList;
import java.util.List;

/**
 * @author david
 *
 */
public class CollectionCfg {

	
	public static final String WEIXIN_JSPAY_CN = "微信公众号";
	public static final String WEIXIN_APPPAY_CN = "微信APP支付";
	public static final String WEIXIN_MICROPAY_CN = "微信被扫";
	public static final String ALIPAY_MICROPAY_CN = "支付宝被扫";
	public static final String ALIPAY_JSPAY_CN = "支付宝H5支付";
	public static final String UNION_PAY_CN = "云闪付";
	
	public static final String HuiFu = "huiFu";	//包含汇付扫码和汇付刷卡
	public static final String XingYe = "xingYe";	//兴业收付直通车产品
	public static final String XingYePos = "xingYePos";	//兴业POS刷卡
	public static final String Lakala = "lakala";	//lakala刷卡
	public static final String Sand = "sand";	//杉德扫码、刷卡
	public static final String UnionPay = "unionPay";
	
	public static List<String> collectionQueue;
	
	
	static{
		
		collectionQueue = new ArrayList<String>();
//		collectionQueue.add(HuiFu);
//		collectionQueue.add(XingYe);
//		collectionQueue.add(XingYePos);
//		collectionQueue.add(Sand);
//		collectionQueue.add(Lakala);
		collectionQueue.add(UnionPay);
		
	}
}
