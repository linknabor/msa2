package com.eshequ.msa.codes;																														
/**Auto Created by VBScript Do not modify!*/												 
import java.util.HashMap;                                                                  
import java.util.List;                                                       
import java.util.Map;                                                           
import com.eshequ.msa.codes.model.CodeInfo;                     
/** Auto generated */												 
public class ShopsType extends CodesItem {                                      
	private ShopsType(String code){super(CodesItem.shopsType,code);}	        
	public static String getValue(String code){                                         
		return CodesItem.getValue(CodesItem.shopsType,code);                         
	}                                                                                   
	public static String getValue(ShopsType code){	                                
		return CodesItem.getValue(CodesItem.shopsType,code.toString());              
	}                                                                                   
	public static List<CodeInfo> getCodeList(){	                                                
		return CodesItem.getCodeList(CodesItem.shopsType);                           
	}                                                                                   
	public static final ShopsType getObject(String code){                          
		Map map=(Map)mapCodeObject.get(CodesItem.shopsType);                         
		return map==null?null:(ShopsType)map.get(code);                                             
	}                                                                                   
	static {		fillObjectToMap();	}                                                   
	private static final void fillObjectToMap(){                                        
		List<CodeInfo> listCode = getCodeFromDB(CodesItem.shopsType);                                                                 
		Map map = new HashMap(listCode.size());        
		String temp = null;	                          
		Map map2 = new HashMap(listCode.size());                           
		for (int i=0; i<listCode.size();i++){                                            
			CodeInfo codeInfo = listCode.get(i);                                              
			temp = codeInfo.getCiSpCode();                                              
			map.put(temp, codeInfo.getCiSpName());                                     
			map2.put(temp, new ShopsType(temp));                                        
		}                                                                                 
		mapCategoryCode.put(CodesItem.shopsType, map);                                
		mapCodeObject.put(CodesItem.shopsType, map2);		                              
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




	public static final ShopsType DiShang                                 		= getObject("01");
	public static final ShopsType XieZiJian                               		= getObject("02");
	public static final ShopsType ShangChuang                             		= getObject("03");
}

