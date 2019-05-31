package com.eshequ.msa.codes;																														
/**Auto Created by VBScript Do not modify!*/												 
import java.util.HashMap;                                                                  
import java.util.List;                                                       
import java.util.Map;                                                           
import com.eshequ.msa.codes.model.CodeInfo;                     
/** Auto generated */												 
public class MchStatus extends CodesItem {                                      
	private MchStatus(String code){super(CodesItem.mchStatus,code);}	        
	public static String getValue(String code){                                         
		return CodesItem.getValue(CodesItem.mchStatus,code);                         
	}                                                                                   
	public static String getValue(MchStatus code){	                                
		return CodesItem.getValue(CodesItem.mchStatus,code.toString());              
	}                                                                                   
	public static List<CodeInfo> getCodeList(){	                                                
		return CodesItem.getCodeList(CodesItem.mchStatus);                           
	}                                                                                   
	public static final MchStatus getObject(String code){                          
		Map map=(Map)mapCodeObject.get(CodesItem.mchStatus);                         
		return map==null?null:(MchStatus)map.get(code);                                             
	}                                                                                   
	static {		fillObjectToMap();	}                                                   
	private static final void fillObjectToMap(){                                        
		List<CodeInfo> listCode = getCodeFromDB(CodesItem.mchStatus);                                                                 
		Map map = new HashMap(listCode.size());        
		String temp = null;	                          
		Map map2 = new HashMap(listCode.size());                           
		for (int i=0; i<listCode.size();i++){                                            
			CodeInfo codeInfo = listCode.get(i);                                              
			temp = codeInfo.getCiSpCode();                                              
			map.put(temp, codeInfo.getCiSpName());                                     
			map2.put(temp, new MchStatus(temp));                                        
		}                                                                                 
		mapCategoryCode.put(CodesItem.mchStatus, map);                                
		mapCodeObject.put(CodesItem.mchStatus, map2);		                              
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




	public static final MchStatus QiYong                                  		= getObject("0");
	public static final MchStatus ZhuXiao                                 		= getObject("1");
	public static final MchStatus WeiQiYong                               		= getObject("2");
}

