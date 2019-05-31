package com.eshequ.msa.codes;																														
/**Auto Created by VBScript Do not modify!*/												 
import java.util.HashMap;                                                                  
import java.util.List;                                                       
import java.util.Map;                                                           
import com.eshequ.msa.codes.model.CodeInfo;                     
/** Auto generated */												 
public class accountStatus extends CodesItem {                                      
	private accountStatus(String code){super(CodesItem.accountStatus,code);}	        
	public static String getValue(String code){                                         
		return CodesItem.getValue(CodesItem.accountStatus,code);                         
	}                                                                                   
	public static String getValue(accountStatus code){	                                
		return CodesItem.getValue(CodesItem.accountStatus,code.toString());              
	}                                                                                   
	public static List<CodeInfo> getCodeList(){	                                                
		return CodesItem.getCodeList(CodesItem.accountStatus);                           
	}                                                                                   
	public static final accountStatus getObject(String code){                          
		Map map=(Map)mapCodeObject.get(CodesItem.accountStatus);                         
		return map==null?null:(accountStatus)map.get(code);                                             
	}                                                                                   
	static {		fillObjectToMap();	}                                                   
	private static final void fillObjectToMap(){                                        
		List<CodeInfo> listCode = getCodeFromDB(CodesItem.accountStatus);                                                                 
		Map map = new HashMap(listCode.size());        
		String temp = null;	                          
		Map map2 = new HashMap(listCode.size());                           
		for (int i=0; i<listCode.size();i++){                                            
			CodeInfo codeInfo = listCode.get(i);                                              
			temp = codeInfo.getCiSpCode();                                              
			map.put(temp, codeInfo.getCiSpName());                                     
			map2.put(temp, new accountStatus(temp));                                        
		}                                                                                 
		mapCategoryCode.put(CodesItem.accountStatus, map);                                
		mapCodeObject.put(CodesItem.accountStatus, map2);		                              
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




	public static final accountStatus WeiJieSuan                              		= getObject("0");
	public static final accountStatus JieSuanZhong                            		= getObject("1");
	public static final accountStatus YiJieSuan                               		= getObject("2");
}

