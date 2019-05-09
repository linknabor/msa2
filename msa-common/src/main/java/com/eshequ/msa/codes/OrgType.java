package com.eshequ.msa.codes;																														
/**Auto Created by VBScript Do not modify!*/												 
import java.util.HashMap;                                                                  
import java.util.List;                                                       
import java.util.Map;                                                           
import com.eshequ.msa.codes.model.CodeInfo;                     
/** Auto generated */												 
public class OrgType extends CodesItem {                                      
	private OrgType(String code){super(CodesItem.orgType,code);}	        
	public static String getValue(String code){                                         
		return CodesItem.getValue(CodesItem.orgType,code);                         
	}                                                                                   
	public static String getValue(OrgType code){	                                
		return CodesItem.getValue(CodesItem.orgType,code.toString());              
	}                                                                                   
	public static List<CodeInfo> getCodeList(){	                                                
		return CodesItem.getCodeList(CodesItem.orgType);                           
	}                                                                                   
	public static final OrgType getObject(String code){                          
		Map map=(Map)mapCodeObject.get(CodesItem.orgType);                         
		return map==null?null:(OrgType)map.get(code);                                             
	}                                                                                   
	static {		fillObjectToMap();	}                                                   
	private static final void fillObjectToMap(){                                        
		List<CodeInfo> listCode = getCodeFromDB(CodesItem.orgType);                                                                 
		Map map = new HashMap(listCode.size());        
		String temp = null;	                          
		Map map2 = new HashMap(listCode.size());                           
		for (int i=0; i<listCode.size();i++){                                            
			CodeInfo codeInfo = listCode.get(i);                                              
			temp = codeInfo.getCiSpCode();                                              
			map.put(temp, codeInfo.getCiSpName());                                     
			map2.put(temp, new OrgType(temp));                                        
		}                                                                                 
		mapCategoryCode.put(CodesItem.orgType, map);                                
		mapCodeObject.put(CodesItem.orgType, map2);		                              
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




	public static final OrgType YunyinShang                             		= getObject("01");
	public static final OrgType HeZuoHuoBan                             		= getObject("02");
}

