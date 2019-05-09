package com.eshequ.msa.codes;																														
/**Auto Created by VBScript Do not modify!*/												 
import java.util.HashMap;                                                                  
import java.util.List;                                                       
import java.util.Map;                                                           
import com.eshequ.msa.codes.model.CodeInfo;                     
/** Auto generated */												 
public class AdLocateType extends CodesItem {                                      
	private AdLocateType(String code){super(CodesItem.adLocateType,code);}	        
	public static String getValue(String code){                                         
		return CodesItem.getValue(CodesItem.adLocateType,code);                         
	}                                                                                   
	public static String getValue(AdLocateType code){	                                
		return CodesItem.getValue(CodesItem.adLocateType,code.toString());              
	}                                                                                   
	public static List<CodeInfo> getCodeList(){	                                                
		return CodesItem.getCodeList(CodesItem.adLocateType);                           
	}                                                                                   
	public static final AdLocateType getObject(String code){                          
		Map map=(Map)mapCodeObject.get(CodesItem.adLocateType);                         
		return map==null?null:(AdLocateType)map.get(code);                                             
	}                                                                                   
	static {		fillObjectToMap();	}                                                   
	private static final void fillObjectToMap(){                                        
		List<CodeInfo> listCode = getCodeFromDB(CodesItem.adLocateType);                                                                 
		Map map = new HashMap(listCode.size());        
		String temp = null;	                          
		Map map2 = new HashMap(listCode.size());                           
		for (int i=0; i<listCode.size();i++){                                            
			CodeInfo codeInfo = listCode.get(i);                                              
			temp = codeInfo.getCiSpCode();                                              
			map.put(temp, codeInfo.getCiSpName());                                     
			map2.put(temp, new AdLocateType(temp));                                        
		}                                                                                 
		mapCategoryCode.put(CodesItem.adLocateType, map);                                
		mapCodeObject.put(CodesItem.adLocateType, map2);		                              
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




	public static final AdLocateType GuangGaoPai                             		= getObject("01");
	public static final AdLocateType DianTiGuangGao                          		= getObject("02");
	public static final AdLocateType XuanChuanLan                            		= getObject("03");
	public static final AdLocateType LuDengZhu                               		= getObject("04");
	public static final AdLocateType DengXiang                               		= getObject("05");
	public static final AdLocateType QiTa                                    		= getObject("99");
}

