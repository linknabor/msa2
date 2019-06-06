package com.eshequ.msa.codes;																														
/**Auto Created by VBScript Do not modify!*/												 
import java.util.HashMap;                                                                  
import java.util.List;                                                       
import java.util.Map;                                                           
import com.eshequ.msa.codes.model.CodeInfo;                     
/** Auto generated */												 
public class ReginType extends CodesItem {                                      
	private ReginType(String code){super(CodesItem.reginType,code);}	        
	public static String getValue(String code){                                         
		return CodesItem.getValue(CodesItem.reginType,code);                         
	}                                                                                   
	public static String getValue(ReginType code){	                                
		return CodesItem.getValue(CodesItem.reginType,code.toString());              
	}                                                                                   
	public static List<CodeInfo> getCodeList(){	                                                
		return CodesItem.getCodeList(CodesItem.reginType);                           
	}                                                                                   
	public static final ReginType getObject(String code){                          
		Map map=(Map)mapCodeObject.get(CodesItem.reginType);                         
		return map==null?null:(ReginType)map.get(code);                                             
	}                                                                                   
	static {		fillObjectToMap();	}                                                   
	private static final void fillObjectToMap(){                                        
		List<CodeInfo> listCode = getCodeFromDB(CodesItem.reginType);                                                                 
		Map map = new HashMap(listCode.size());        
		String temp = null;	                          
		Map map2 = new HashMap(listCode.size());                           
		for (int i=0; i<listCode.size();i++){                                            
			CodeInfo codeInfo = listCode.get(i);                                              
			temp = codeInfo.getCiSpCode();                                              
			map.put(temp, codeInfo.getCiSpName());                                     
			map2.put(temp, new ReginType(temp));                                        
		}                                                                                 
		mapCategoryCode.put(CodesItem.reginType, map);                                
		mapCodeObject.put(CodesItem.reginType, map2);		                              
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




	public static final ReginType QuanGuo                                 		= getObject("0");
	public static final ReginType Sheng                                   		= getObject("1");
	public static final ReginType Shi                                     		= getObject("2");
	public static final ReginType Qu                                      		= getObject("3");
}

