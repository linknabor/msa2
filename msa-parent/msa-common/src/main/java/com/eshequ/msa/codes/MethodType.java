package com.eshequ.msa.codes;																														
/**Auto Created by VBScript Do not modify!*/												 
import java.util.HashMap;                                                                  
import java.util.List;                                                       
import java.util.Map;                                                           
import com.eshequ.msa.codes.model.CodeInfo;                     
/** Auto generated */												 
public class MethodType extends CodesItem {                                      
	private MethodType(String code){super(CodesItem.methodType,code);}	        
	public static String getValue(String code){                                         
		return CodesItem.getValue(CodesItem.methodType,code);                         
	}                                                                                   
	public static String getValue(MethodType code){	                                
		return CodesItem.getValue(CodesItem.methodType,code.toString());              
	}                                                                                   
	public static List<CodeInfo> getCodeList(){	                                                
		return CodesItem.getCodeList(CodesItem.methodType);                           
	}                                                                                   
	public static final MethodType getObject(String code){                          
		Map map=(Map)mapCodeObject.get(CodesItem.methodType);                         
		return map==null?null:(MethodType)map.get(code);                                             
	}                                                                                   
	static {		fillObjectToMap();	}                                                   
	private static final void fillObjectToMap(){                                        
		List<CodeInfo> listCode = getCodeFromDB(CodesItem.methodType);                                                                 
		Map map = new HashMap(listCode.size());        
		String temp = null;	                          
		Map map2 = new HashMap(listCode.size());                           
		for (int i=0; i<listCode.size();i++){                                            
			CodeInfo codeInfo = listCode.get(i);                                              
			temp = codeInfo.getCiSpCode();                                              
			map.put(temp, codeInfo.getCiSpName());                                     
			map2.put(temp, new MethodType(temp));                                        
		}                                                                                 
		mapCategoryCode.put(CodesItem.methodType, map);                                
		mapCodeObject.put(CodesItem.methodType, map2);		                              
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




	public static final MethodType WEIXIN                                  		= getObject("01");
	public static final MethodType ALIPAY                                  		= getObject("02");
}

