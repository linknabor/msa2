package com.eshequ.msa.codes;																														
/**Auto Created by VBScript Do not modify!*/												 
import java.util.HashMap;                                                                  
import java.util.List;                                                       
import java.util.Map;                                                           
import com.eshequ.msa.codes.model.CodeInfo;                     
/** Auto generated */												 
public class IsFlag extends CodesItem {                                      
	private IsFlag(String code){super(CodesItem.isFlag,code);}	        
	public static String getValue(String code){                                         
		return CodesItem.getValue(CodesItem.isFlag,code);                         
	}                                                                                   
	public static String getValue(IsFlag code){	                                
		return CodesItem.getValue(CodesItem.isFlag,code.toString());              
	}                                                                                   
	public static List<CodeInfo> getCodeList(){	                                                
		return CodesItem.getCodeList(CodesItem.isFlag);                           
	}                                                                                   
	public static final IsFlag getObject(String code){                          
		Map map=(Map)mapCodeObject.get(CodesItem.isFlag);                         
		return map==null?null:(IsFlag)map.get(code);                                             
	}                                                                                   
	static {		fillObjectToMap();	}                                                   
	private static final void fillObjectToMap(){                                        
		List<CodeInfo> listCode = getCodeFromDB(CodesItem.isFlag);                                                                 
		Map map = new HashMap(listCode.size());        
		String temp = null;	                          
		Map map2 = new HashMap(listCode.size());                           
		for (int i=0; i<listCode.size();i++){                                            
			CodeInfo codeInfo = listCode.get(i);                                              
			temp = codeInfo.getCiSpCode();                                              
			map.put(temp, codeInfo.getCiSpName());                                     
			map2.put(temp, new IsFlag(temp));                                        
		}                                                                                 
		mapCategoryCode.put(CodesItem.isFlag, map);                                
		mapCodeObject.put(CodesItem.isFlag, map2);		                              
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




	public static final IsFlag Fou                                     		= getObject("0");
	public static final IsFlag Shi                                     		= getObject("1");
}

