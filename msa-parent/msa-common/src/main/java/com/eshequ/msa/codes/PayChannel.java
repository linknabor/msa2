package com.eshequ.msa.codes;																														
/**Auto Created by VBScript Do not modify!*/												 
import java.util.HashMap;                                                                  
import java.util.List;                                                       
import java.util.Map;                                                           
import com.eshequ.msa.codes.model.CodeInfo;                     
/** Auto generated */												 
public class PayChannel extends CodesItem {                                      
	private PayChannel(String code){super(CodesItem.payChannel,code);}	        
	public static String getValue(String code){                                         
		return CodesItem.getValue(CodesItem.payChannel,code);                         
	}                                                                                   
	public static String getValue(PayChannel code){	                                
		return CodesItem.getValue(CodesItem.payChannel,code.toString());              
	}                                                                                   
	public static List<CodeInfo> getCodeList(){	                                                
		return CodesItem.getCodeList(CodesItem.payChannel);                           
	}                                                                                   
	public static final PayChannel getObject(String code){                          
		Map map=(Map)mapCodeObject.get(CodesItem.payChannel);                         
		return map==null?null:(PayChannel)map.get(code);                                             
	}                                                                                   
	static {		fillObjectToMap();	}                                                   
	private static final void fillObjectToMap(){                                        
		List<CodeInfo> listCode = getCodeFromDB(CodesItem.payChannel);                                                                 
		Map map = new HashMap(listCode.size());        
		String temp = null;	                          
		Map map2 = new HashMap(listCode.size());                           
		for (int i=0; i<listCode.size();i++){                                            
			CodeInfo codeInfo = listCode.get(i);                                              
			temp = codeInfo.getCiSpCode();                                              
			map.put(temp, codeInfo.getCiSpName());                                     
			map2.put(temp, new PayChannel(temp));                                        
		}                                                                                 
		mapCategoryCode.put(CodesItem.payChannel, map);                                
		mapCodeObject.put(CodesItem.payChannel, map2);		                              
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




	public static final PayChannel UnionPay                                		= getObject("01");
}

