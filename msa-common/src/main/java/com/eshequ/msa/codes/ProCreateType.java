package com.eshequ.msa.codes;																														
/**Auto Created by VBScript Do not modify!*/												 
import java.util.HashMap;                                                                  
import java.util.List;                                                       
import java.util.Map;                                                           
import com.eshequ.msa.codes.model.CodeInfo;                     
/** Auto generated */												 
public class ProCreateType extends CodesItem {                                      
	private ProCreateType(String code){super(CodesItem.proCreateType,code);}	        
	public static String getValue(String code){                                         
		return CodesItem.getValue(CodesItem.proCreateType,code);                         
	}                                                                                   
	public static String getValue(ProCreateType code){	                                
		return CodesItem.getValue(CodesItem.proCreateType,code.toString());              
	}                                                                                   
	public static List<CodeInfo> getCodeList(){	                                                
		return CodesItem.getCodeList(CodesItem.proCreateType);                           
	}                                                                                   
	public static final ProCreateType getObject(String code){                          
		Map map=(Map)mapCodeObject.get(CodesItem.proCreateType);                         
		return map==null?null:(ProCreateType)map.get(code);                                             
	}                                                                                   
	static {		fillObjectToMap();	}                                                   
	private static final void fillObjectToMap(){                                        
		List<CodeInfo> listCode = getCodeFromDB(CodesItem.proCreateType);                                                                 
		Map map = new HashMap(listCode.size());        
		String temp = null;	                          
		Map map2 = new HashMap(listCode.size());                           
		for (int i=0; i<listCode.size();i++){                                            
			CodeInfo codeInfo = listCode.get(i);                                              
			temp = codeInfo.getCiSpCode();                                              
			map.put(temp, codeInfo.getCiSpName());                                     
			map2.put(temp, new ProCreateType(temp));                                        
		}                                                                                 
		mapCategoryCode.put(CodesItem.proCreateType, map);                                
		mapCodeObject.put(CodesItem.proCreateType, map2);		                              
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




	public static final ProCreateType ShangPinFang                            		= getObject("1");
	public static final ProCreateType DongQianFang                            		= getObject("2");
	public static final ProCreateType JingShiFang                             		= getObject("3");
	public static final ProCreateType ShouHouFang                             		= getObject("4");
	public static final ProCreateType ZhiGuanGongFang                         		= getObject("5");
	public static final ProCreateType HunHe                                   		= getObject("6");
	public static final ProCreateType XiTongGongFang                          		= getObject("7");
}

