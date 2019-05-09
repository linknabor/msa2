package com.eshequ.msa.codes;																														
/**Auto Created by VBScript Do not modify!*/												 
import java.util.HashMap;                                                                  
import java.util.List;                                                       
import java.util.Map;                                                           
import com.eshequ.msa.codes.model.CodeInfo;                     
/** Auto generated */												 
public class MchType extends CodesItem {                                      
	private MchType(String code){super(CodesItem.mchType,code);}	        
	public static String getValue(String code){                                         
		return CodesItem.getValue(CodesItem.mchType,code);                         
	}                                                                                   
	public static String getValue(MchType code){	                                
		return CodesItem.getValue(CodesItem.mchType,code.toString());              
	}                                                                                   
	public static List<CodeInfo> getCodeList(){	                                                
		return CodesItem.getCodeList(CodesItem.mchType);                           
	}                                                                                   
	public static final MchType getObject(String code){                          
		Map map=(Map)mapCodeObject.get(CodesItem.mchType);                         
		return map==null?null:(MchType)map.get(code);                                             
	}                                                                                   
	static {		fillObjectToMap();	}                                                   
	private static final void fillObjectToMap(){                                        
		List<CodeInfo> listCode = getCodeFromDB(CodesItem.mchType);                                                                 
		Map map = new HashMap(listCode.size());        
		String temp = null;	                          
		Map map2 = new HashMap(listCode.size());                           
		for (int i=0; i<listCode.size();i++){                                            
			CodeInfo codeInfo = listCode.get(i);                                              
			temp = codeInfo.getCiSpCode();                                              
			map.put(temp, codeInfo.getCiSpName());                                     
			map2.put(temp, new MchType(temp));                                        
		}                                                                                 
		mapCategoryCode.put(CodesItem.mchType, map);                                
		mapCodeObject.put(CodesItem.mchType, map2);		                              
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




	public static final MchType Main                                    		= getObject("1");
	public static final MchType Sub                                     		= getObject("2");
}

