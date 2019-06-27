package com.eshequ.msa.codes;																														
/**Auto Created by VBScript Do not modify!*/												 
import java.util.HashMap;                                                                  
import java.util.List;                                                       
import java.util.Map;                                                           
import com.eshequ.msa.codes.model.CodeInfo;                     
/** Auto generated */												 
public class RefundStatus extends CodesItem {                                      
	private RefundStatus(String code){super(CodesItem.refundStatus,code);}	        
	public static String getValue(String code){                                         
		return CodesItem.getValue(CodesItem.refundStatus,code);                         
	}                                                                                   
	public static String getValue(RefundStatus code){	                                
		return CodesItem.getValue(CodesItem.refundStatus,code.toString());              
	}                                                                                   
	public static List<CodeInfo> getCodeList(){	                                                
		return CodesItem.getCodeList(CodesItem.refundStatus);                           
	}                                                                                   
	public static final RefundStatus getObject(String code){                          
		Map map=(Map)mapCodeObject.get(CodesItem.refundStatus);                         
		return map==null?null:(RefundStatus)map.get(code);                                             
	}                                                                                   
	static {		fillObjectToMap();	}                                                   
	private static final void fillObjectToMap(){                                        
		List<CodeInfo> listCode = getCodeFromDB(CodesItem.refundStatus);                                                                 
		Map map = new HashMap(listCode.size());        
		String temp = null;	                          
		Map map2 = new HashMap(listCode.size());                           
		for (int i=0; i<listCode.size();i++){                                            
			CodeInfo codeInfo = listCode.get(i);                                              
			temp = codeInfo.getCiSpCode();                                              
			map.put(temp, codeInfo.getCiSpName());                                     
			map2.put(temp, new RefundStatus(temp));                                        
		}                                                                                 
		mapCategoryCode.put(CodesItem.refundStatus, map);                                
		mapCodeObject.put(CodesItem.refundStatus, map2);		                              
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




	public static final RefundStatus JiaoYiWanCheng                          		= getObject("01");
	public static final RefundStatus DaiShenHe                               		= getObject("03");
}

