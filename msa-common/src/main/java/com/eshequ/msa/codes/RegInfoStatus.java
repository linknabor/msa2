package com.eshequ.msa.codes;																														
/**Auto Created by VBScript Do not modify!*/												 
import java.util.HashMap;                                                                  
import java.util.List;                                                       
import java.util.Map;                                                           
import com.eshequ.msa.codes.model.CodeInfo;                     
/** Auto generated */												 
public class RegInfoStatus extends CodesItem {                                      
	private RegInfoStatus(String code){super(CodesItem.regInfoStatus,code);}	        
	public static String getValue(String code){                                         
		return CodesItem.getValue(CodesItem.regInfoStatus,code);                         
	}                                                                                   
	public static String getValue(RegInfoStatus code){	                                
		return CodesItem.getValue(CodesItem.regInfoStatus,code.toString());              
	}                                                                                   
	public static List<CodeInfo> getCodeList(){	                                                
		return CodesItem.getCodeList(CodesItem.regInfoStatus);                           
	}                                                                                   
	public static final RegInfoStatus getObject(String code){                          
		Map map=(Map)mapCodeObject.get(CodesItem.regInfoStatus);                         
		return map==null?null:(RegInfoStatus)map.get(code);                                             
	}                                                                                   
	static {		fillObjectToMap();	}                                                   
	private static final void fillObjectToMap(){                                        
		List<CodeInfo> listCode = getCodeFromDB(CodesItem.regInfoStatus);                                                                 
		Map map = new HashMap(listCode.size());        
		String temp = null;	                          
		Map map2 = new HashMap(listCode.size());                           
		for (int i=0; i<listCode.size();i++){                                            
			CodeInfo codeInfo = listCode.get(i);                                              
			temp = codeInfo.getCiSpCode();                                              
			map.put(temp, codeInfo.getCiSpName());                                     
			map2.put(temp, new RegInfoStatus(temp));                                        
		}                                                                                 
		mapCategoryCode.put(CodesItem.regInfoStatus, map);                                
		mapCodeObject.put(CodesItem.regInfoStatus, map2);		                              
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




	public static final RegInfoStatus WeiShenHe                               		= getObject("0");
	public static final RegInfoStatus YiShengHe                               		= getObject("1");
	public static final RegInfoStatus YiFuHE                                  		= getObject("2");
	public static final RegInfoStatus WeiTongGuo                              		= getObject("3");
}

