package com.eshequ.msa.codes;																														
/**Auto Created by VBScript Do not modify!*/												 
import java.util.HashMap;                                                                  
import java.util.List;                                                       
import java.util.Map;                                                           
import com.eshequ.msa.codes.model.CodeInfo;                     
/** Auto generated */												 
public class CustType extends CodesItem {                                      
	private CustType(String code){super(CodesItem.custType,code);}	        
	public static String getValue(String code){                                         
		return CodesItem.getValue(CodesItem.custType,code);                         
	}                                                                                   
	public static String getValue(CustType code){	                                
		return CodesItem.getValue(CodesItem.custType,code.toString());              
	}                                                                                   
	public static List<CodeInfo> getCodeList(){	                                                
		return CodesItem.getCodeList(CodesItem.custType);                           
	}                                                                                   
	public static final CustType getObject(String code){                          
		Map map=(Map)mapCodeObject.get(CodesItem.custType);                         
		return map==null?null:(CustType)map.get(code);                                             
	}                                                                                   
	static {		fillObjectToMap();	}                                                   
	private static final void fillObjectToMap(){                                        
		List<CodeInfo> listCode = getCodeFromDB(CodesItem.custType);                                                                 
		Map map = new HashMap(listCode.size());        
		String temp = null;	                          
		Map map2 = new HashMap(listCode.size());                           
		for (int i=0; i<listCode.size();i++){                                            
			CodeInfo codeInfo = listCode.get(i);                                              
			temp = codeInfo.getCiSpCode();                                              
			map.put(temp, codeInfo.getCiSpName());                                     
			map2.put(temp, new CustType(temp));                                        
		}                                                                                 
		mapCategoryCode.put(CodesItem.custType, map);                                
		mapCodeObject.put(CodesItem.custType, map2);		                              
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




	public static final CustType GeRen                                   		= getObject("01");
	public static final CustType GongSi                                  		= getObject("02");
	public static final CustType YeWeiHui                                		= getObject("03");
	public static final CustType KaiFaShang                              		= getObject("04");
}

