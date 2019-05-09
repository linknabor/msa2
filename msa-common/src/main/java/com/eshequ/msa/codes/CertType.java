package com.eshequ.msa.codes;																														
/**Auto Created by VBScript Do not modify!*/												 
import java.util.HashMap;                                                                  
import java.util.List;                                                       
import java.util.Map;                                                           
import com.eshequ.msa.codes.model.CodeInfo;                     
/** Auto generated */												 
public class CertType extends CodesItem {                                      
	private CertType(String code){super(CodesItem.certType,code);}	        
	public static String getValue(String code){                                         
		return CodesItem.getValue(CodesItem.certType,code);                         
	}                                                                                   
	public static String getValue(CertType code){	                                
		return CodesItem.getValue(CodesItem.certType,code.toString());              
	}                                                                                   
	public static List<CodeInfo> getCodeList(){	                                                
		return CodesItem.getCodeList(CodesItem.certType);                           
	}                                                                                   
	public static final CertType getObject(String code){                          
		Map map=(Map)mapCodeObject.get(CodesItem.certType);                         
		return map==null?null:(CertType)map.get(code);                                             
	}                                                                                   
	static {		fillObjectToMap();	}                                                   
	private static final void fillObjectToMap(){                                        
		List<CodeInfo> listCode = getCodeFromDB(CodesItem.certType);                                                                 
		Map map = new HashMap(listCode.size());        
		String temp = null;	                          
		Map map2 = new HashMap(listCode.size());                           
		for (int i=0; i<listCode.size();i++){                                            
			CodeInfo codeInfo = listCode.get(i);                                              
			temp = codeInfo.getCiSpCode();                                              
			map.put(temp, codeInfo.getCiSpName());                                     
			map2.put(temp, new CertType(temp));                                        
		}                                                                                 
		mapCategoryCode.put(CodesItem.certType, map);                                
		mapCodeObject.put(CodesItem.certType, map2);		                              
	}                                                                                   
/////////////////////////////////////////////////////////////////////////////////		
	public int hashCode(){return super.hashCode();}
	public boolean equals(Object obj)
	{
		if( obj instanceof CodesItem)
			return (this == obj);
		else
			return super.equals(obj);
	}
/////////////////////////////////////////////////////////////////////////////////    




	public static final CertType ShenFenZheng                            		= getObject("01");
	public static final CertType GongZuoZheng                            		= getObject("02");
	public static final CertType HuZhao                                  		= getObject("03");
	public static final CertType HuKouPu                                 		= getObject("04");
	public static final CertType QuanShengZheng                          		= getObject("05");
	public static final CertType YingYeZhiZhao                           		= getObject("06");
	public static final CertType ShangYeDengJiZheng                      		= getObject("07");
	public static final CertType HuiXiangZheng                           		= getObject("08");
	public static final CertType LvKa                                    		= getObject("09");
	public static final CertType JunGuanZheng                            		= getObject("10");
	public static final CertType LvXingZheng                             		= getObject("11");
	public static final CertType DaLuWangLaiZheng                        		= getObject("12");
	public static final CertType FaRenDaiMaZheng                         		= getObject("13");
	public static final CertType QiYeDaiMaZheng                          		= getObject("14");
	public static final CertType DengJiZheng                             		= getObject("15");
	public static final CertType SheTuanFaRenZheng                       		= getObject("16");
	public static final CertType BingYiZheng                             		= getObject("17");
	public static final CertType ZuZhiJiGouDaiMaZheng                    		= getObject("18");
	public static final CertType CheLiangXingShiZheng                    		= getObject("19");
	public static final CertType CheLiangFaDongJiHao                     		= getObject("20");
	public static final CertType QiTa                                    		= getObject("99");
}

