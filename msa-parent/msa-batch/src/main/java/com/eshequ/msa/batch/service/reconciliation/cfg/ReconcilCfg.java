/**
 * 
 */
package com.eshequ.msa.batch.service.reconciliation.cfg;

import java.util.ArrayList;
import java.util.List;

/**
 * @author david
 *
 */
public class ReconcilCfg {

	
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
