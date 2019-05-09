package com.eshequ.msa.codes;																														
/**Auto Created by VBScript Do not modify!*/												 
import java.util.HashMap;                                                                  
import java.util.List;                                                       
import java.util.Map;                                                           
import com.eshequ.msa.codes.model.CodeInfo;                     
/** Auto generated */												 
public class Ward extends CodesItem {                                      
	private Ward(String code){super(CodesItem.ward,code);}	        
	public static String getValue(String code){                                         
		return CodesItem.getValue(CodesItem.ward,code);                         
	}                                                                                   
	public static String getValue(Ward code){	                                
		return CodesItem.getValue(CodesItem.ward,code.toString());              
	}                                                                                   
	public static List<CodeInfo> getCodeList(){	                                                
		return CodesItem.getCodeList(CodesItem.ward);                           
	}                                                                                   
	public static final Ward getObject(String code){                          
		Map map=(Map)mapCodeObject.get(CodesItem.ward);                         
		return map==null?null:(Ward)map.get(code);                                             
	}                                                                                   
	static {		fillObjectToMap();	}                                                   
	private static final void fillObjectToMap(){                                        
		List<CodeInfo> listCode = getCodeFromDB(CodesItem.ward);                                                                 
		Map map = new HashMap(listCode.size());        
		String temp = null;	                          
		Map map2 = new HashMap(listCode.size());                           
		for (int i=0; i<listCode.size();i++){                                            
			CodeInfo codeInfo = listCode.get(i);                                              
			temp = codeInfo.getCiSpCode();                                              
			map.put(temp, codeInfo.getCiSpName());                                     
			map2.put(temp, new Ward(temp));                                        
		}                                                                                 
		mapCategoryCode.put(CodesItem.ward, map);                                
		mapCodeObject.put(CodesItem.ward, map2);		                              
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




	public static final Ward Nan                                     		= getObject("01");
	public static final Ward Bei                                     		= getObject("02");
	public static final Ward Dong                                    		= getObject("03");
	public static final Ward Xi                                      		= getObject("04");
	public static final Ward DongNan                                 		= getObject("05");
	public static final Ward XiNan                                   		= getObject("06");
	public static final Ward DongBei                                 		= getObject("07");
	public static final Ward XiBei                                   		= getObject("08");
}

