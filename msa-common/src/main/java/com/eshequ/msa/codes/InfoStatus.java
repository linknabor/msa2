package com.eshequ.msa.codes;																														
/**Auto Created by VBScript Do not modify!*/												 
import java.util.HashMap;                                                                  
import java.util.List;                                                       
import java.util.Map;                                                           
import com.eshequ.msa.codes.model.CodeInfo;                     
/** Auto generated */												 
public class InfoStatus extends CodesItem {                                      
	private InfoStatus(String code){super(CodesItem.infoStatus,code);}	        
	public static String getValue(String code){                                         
		return CodesItem.getValue(CodesItem.infoStatus,code);                         
	}                                                                                   
	public static String getValue(InfoStatus code){	                                
		return CodesItem.getValue(CodesItem.infoStatus,code.toString());              
	}                                                                                   
	public static List<CodeInfo> getCodeList(){	                                                
		return CodesItem.getCodeList(CodesItem.infoStatus);                           
	}                                                                                   
	public static final InfoStatus getObject(String code){                          
		Map map=(Map)mapCodeObject.get(CodesItem.infoStatus);                         
		return map==null?null:(InfoStatus)map.get(code);                                             
	}                                                                                   
	static {		fillObjectToMap();	}                                                   
	private static final void fillObjectToMap(){                                        
		List<CodeInfo> listCode = getCodeFromDB(CodesItem.infoStatus);                                                                 
		Map map = new HashMap(listCode.size());        
		String temp = null;	                          
		Map map2 = new HashMap(listCode.size());                           
		for (int i=0; i<listCode.size();i++){                                            
			CodeInfo codeInfo = listCode.get(i);                                              
			temp = codeInfo.getCiSpCode();                                              
			map.put(temp, codeInfo.getCiSpName());                                     
			map2.put(temp, new InfoStatus(temp));                                        
		}                                                                                 
		mapCategoryCode.put(CodesItem.infoStatus, map);                                
		mapCodeObject.put(CodesItem.infoStatus, map2);		                              
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




	public static final InfoStatus ZhengChang                              		= getObject("0");
	public static final InfoStatus ZhuXiao                                 		= getObject("1");
}

