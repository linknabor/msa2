package com.eshequ.msa.codes;																														
/**Auto Created by VBScript Do not modify!*/												 
import java.util.HashMap;                                                                  
import java.util.List;                                                       
import java.util.Map;                                                           
import com.eshequ.msa.codes.model.CodeInfo;                     
/** Auto generated */												 
public class PayMethod extends CodesItem {                                      
	private PayMethod(String code){super(CodesItem.payMethod,code);}	        
	public static String getValue(String code){                                         
		return CodesItem.getValue(CodesItem.payMethod,code);                         
	}                                                                                   
	public static String getValue(PayMethod code){	                                
		return CodesItem.getValue(CodesItem.payMethod,code.toString());              
	}                                                                                   
	public static List<CodeInfo> getCodeList(){	                                                
		return CodesItem.getCodeList(CodesItem.payMethod);                           
	}                                                                                   
	public static final PayMethod getObject(String code){                          
		Map map=(Map)mapCodeObject.get(CodesItem.payMethod);                         
		return map==null?null:(PayMethod)map.get(code);                                             
	}                                                                                   
	static {		fillObjectToMap();	}                                                   
	private static final void fillObjectToMap(){                                        
		List<CodeInfo> listCode = getCodeFromDB(CodesItem.payMethod);                                                                 
		Map map = new HashMap(listCode.size());        
		String temp = null;	                          
		Map map2 = new HashMap(listCode.size());                           
		for (int i=0; i<listCode.size();i++){                                            
			CodeInfo codeInfo = listCode.get(i);                                              
			temp = codeInfo.getCiSpCode();                                              
			map.put(temp, codeInfo.getCiSpName());                                     
			map2.put(temp, new PayMethod(temp));                                        
		}                                                                                 
		mapCategoryCode.put(CodesItem.payMethod, map);                                
		mapCodeObject.put(CodesItem.payMethod, map2);		                              
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




	public static final PayMethod XianJin                                 		= getObject("01");
	public static final PayMethod ZhuanZhang                              		= getObject("02");
	public static final PayMethod Pos                                     		= getObject("03");
	public static final PayMethod AliPayWeb                               		= getObject("04");
	public static final PayMethod TenPayWeb                               		= getObject("05");
	public static final PayMethod WeChat                                  		= getObject("06");
	public static final PayMethod WechatApp                               		= getObject("07");
	public static final PayMethod WechatMicropay                          		= getObject("08");
	public static final PayMethod AliPayMicropay                          		= getObject("09");
	public static final PayMethod MobilePos                               		= getObject("10");
	public static final PayMethod WyFeeMp                                 		= getObject("99");
}

