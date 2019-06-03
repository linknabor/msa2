package com.eshequ.msa.codes;																														
/**Auto Created by VBScript Do not modify!*/												 
import java.util.HashMap;                                                                  
import java.util.List;                                                       
import java.util.Map;                                                           
import com.eshequ.msa.codes.model.CodeInfo;                     
/** Auto generated */												 
public class ProcessStatus extends CodesItem {                                      
	private ProcessStatus(String code){super(CodesItem.processStatus,code);}	        
	public static String getValue(String code){                                         
		return CodesItem.getValue(CodesItem.processStatus,code);                         
	}                                                                                   
	public static String getValue(ProcessStatus code){	                                
		return CodesItem.getValue(CodesItem.processStatus,code.toString());              
	}                                                                                   
	public static List<CodeInfo> getCodeList(){	                                                
		return CodesItem.getCodeList(CodesItem.processStatus);                           
	}                                                                                   
	public static final ProcessStatus getObject(String code){                          
		Map map=(Map)mapCodeObject.get(CodesItem.processStatus);                         
		return map==null?null:(ProcessStatus)map.get(code);                                             
	}                                                                                   
	static {		fillObjectToMap();	}                                                   
	private static final void fillObjectToMap(){                                        
		List<CodeInfo> listCode = getCodeFromDB(CodesItem.processStatus);                                                                 
		Map map = new HashMap(listCode.size());        
		String temp = null;	                          
		Map map2 = new HashMap(listCode.size());                           
		for (int i=0; i<listCode.size();i++){                                            
			CodeInfo codeInfo = listCode.get(i);                                              
			temp = codeInfo.getCiSpCode();                                              
			map.put(temp, codeInfo.getCiSpName());                                     
			map2.put(temp, new ProcessStatus(temp));                                        
		}                                                                                 
		mapCategoryCode.put(CodesItem.processStatus, map);                                
		mapCodeObject.put(CodesItem.processStatus, map2);		                              
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




	public static final ProcessStatus DaiShenHe                               		= getObject("0");
	public static final ProcessStatus ShenHeTongGuo                           		= getObject("1");
	public static final ProcessStatus ShenHeBoHui                             		= getObject("2");
}

