package com.eshequ.msa.codes;																														
/**Auto Created by VBScript Do not modify!*/												 
import java.util.HashMap;                                                                  
import java.util.List;                                                       
import java.util.Map;                                                           
import com.eshequ.msa.codes.model.CodeInfo;                     
/** Auto generated */												 
public class OrgStatus extends CodesItem {                                      
	private OrgStatus(String code){super(CodesItem.orgStatus,code);}	        
	public static String getValue(String code){                                         
		return CodesItem.getValue(CodesItem.orgStatus,code);                         
	}                                                                                   
	public static String getValue(OrgStatus code){	                                
		return CodesItem.getValue(CodesItem.orgStatus,code.toString());              
	}                                                                                   
	public static List<CodeInfo> getCodeList(){	                                                
		return CodesItem.getCodeList(CodesItem.orgStatus);                           
	}                                                                                   
	public static final OrgStatus getObject(String code){                          
		Map map=(Map)mapCodeObject.get(CodesItem.orgStatus);                         
		return map==null?null:(OrgStatus)map.get(code);                                             
	}                                                                                   
	static {		fillObjectToMap();	}                                                   
	private static final void fillObjectToMap(){                                        
		List<CodeInfo> listCode = getCodeFromDB(CodesItem.orgStatus);                                                                 
		Map map = new HashMap(listCode.size());        
		String temp = null;	                          
		Map map2 = new HashMap(listCode.size());                           
		for (int i=0; i<listCode.size();i++){                                            
			CodeInfo codeInfo = listCode.get(i);                                              
			temp = codeInfo.getCiSpCode();                                              
			map.put(temp, codeInfo.getCiSpName());                                     
			map2.put(temp, new OrgStatus(temp));                                        
		}                                                                                 
		mapCategoryCode.put(CodesItem.orgStatus, map);                                
		mapCodeObject.put(CodesItem.orgStatus, map2);		                              
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




	public static final OrgStatus ZhengChang                              		= getObject("0");
	public static final OrgStatus JinYong                                 		= getObject("1");
	public static final OrgStatus ZhuXiao                                 		= getObject("2");
}

