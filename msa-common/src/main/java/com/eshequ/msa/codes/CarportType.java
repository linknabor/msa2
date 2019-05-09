package com.eshequ.msa.codes;																														
/**Auto Created by VBScript Do not modify!*/												 
import java.util.HashMap;                                                                  
import java.util.List;                                                       
import java.util.Map;                                                           
import com.eshequ.msa.codes.model.CodeInfo;                     
/** Auto generated */												 
public class CarportType extends CodesItem {                                      
	private CarportType(String code){super(CodesItem.carportType,code);}	        
	public static String getValue(String code){                                         
		return CodesItem.getValue(CodesItem.carportType,code);                         
	}                                                                                   
	public static String getValue(CarportType code){	                                
		return CodesItem.getValue(CodesItem.carportType,code.toString());              
	}                                                                                   
	public static List<CodeInfo> getCodeList(){	                                                
		return CodesItem.getCodeList(CodesItem.carportType);                           
	}                                                                                   
	public static final CarportType getObject(String code){                          
		Map map=(Map)mapCodeObject.get(CodesItem.carportType);                         
		return map==null?null:(CarportType)map.get(code);                                             
	}                                                                                   
	static {		fillObjectToMap();	}                                                   
	private static final void fillObjectToMap(){                                        
		List<CodeInfo> listCode = getCodeFromDB(CodesItem.carportType);                                                                 
		Map map = new HashMap(listCode.size());        
		String temp = null;	                          
		Map map2 = new HashMap(listCode.size());                           
		for (int i=0; i<listCode.size();i++){                                            
			CodeInfo codeInfo = listCode.get(i);                                              
			temp = codeInfo.getCiSpCode();                                              
			map.put(temp, codeInfo.getCiSpName());                                     
			map2.put(temp, new CarportType(temp));                                        
		}                                                                                 
		mapCategoryCode.put(CodesItem.carportType, map);                                
		mapCodeObject.put(CodesItem.carportType, map2);		                              
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




	public static final CarportType DiXiaCheWei                             		= getObject("01");
	public static final CarportType DiShangCheWei                           		= getObject("02");
}

