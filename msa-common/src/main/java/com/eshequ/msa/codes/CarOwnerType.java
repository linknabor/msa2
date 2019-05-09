package com.eshequ.msa.codes;																														
/**Auto Created by VBScript Do not modify!*/												 
import java.util.HashMap;                                                                  
import java.util.List;                                                       
import java.util.Map;                                                           
import com.eshequ.msa.codes.model.CodeInfo;                     
/** Auto generated */												 
public class CarOwnerType extends CodesItem {                                      
	private CarOwnerType(String code){super(CodesItem.carOwnerType,code);}	        
	public static String getValue(String code){                                         
		return CodesItem.getValue(CodesItem.carOwnerType,code);                         
	}                                                                                   
	public static String getValue(CarOwnerType code){	                                
		return CodesItem.getValue(CodesItem.carOwnerType,code.toString());              
	}                                                                                   
	public static List<CodeInfo> getCodeList(){	                                                
		return CodesItem.getCodeList(CodesItem.carOwnerType);                           
	}                                                                                   
	public static final CarOwnerType getObject(String code){                          
		Map map=(Map)mapCodeObject.get(CodesItem.carOwnerType);                         
		return map==null?null:(CarOwnerType)map.get(code);                                             
	}                                                                                   
	static {		fillObjectToMap();	}                                                   
	private static final void fillObjectToMap(){                                        
		List<CodeInfo> listCode = getCodeFromDB(CodesItem.carOwnerType);                                                                 
		Map map = new HashMap(listCode.size());        
		String temp = null;	                          
		Map map2 = new HashMap(listCode.size());                           
		for (int i=0; i<listCode.size();i++){                                            
			CodeInfo codeInfo = listCode.get(i);                                              
			temp = codeInfo.getCiSpCode();                                              
			map.put(temp, codeInfo.getCiSpName());                                     
			map2.put(temp, new CarOwnerType(temp));                                        
		}                                                                                 
		mapCategoryCode.put(CodesItem.carOwnerType, map);                                
		mapCodeObject.put(CodesItem.carOwnerType, map2);		                              
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




	public static final CarOwnerType SiChe                                   		= getObject("01");
	public static final CarOwnerType GongChe                                 		= getObject("02");
	public static final CarOwnerType QiTa                                    		= getObject("99");
}

