package com.eshequ.msa.codes;																														
/**Auto Created by VBScript Do not modify!*/												 
import java.util.HashMap;                                                                  
import java.util.List;                                                       
import java.util.Map;                                                           
import com.eshequ.msa.codes.model.CodeInfo;                     
/** Auto generated */												 
public class LiftFlag extends CodesItem {                                      
	private LiftFlag(String code){super(CodesItem.liftFlag,code);}	        
	public static String getValue(String code){                                         
		return CodesItem.getValue(CodesItem.liftFlag,code);                         
	}                                                                                   
	public static String getValue(LiftFlag code){	                                
		return CodesItem.getValue(CodesItem.liftFlag,code.toString());              
	}                                                                                   
	public static List<CodeInfo> getCodeList(){	                                                
		return CodesItem.getCodeList(CodesItem.liftFlag);                           
	}                                                                                   
	public static final LiftFlag getObject(String code){                          
		Map map=(Map)mapCodeObject.get(CodesItem.liftFlag);                         
		return map==null?null:(LiftFlag)map.get(code);                                             
	}                                                                                   
	static {		fillObjectToMap();	}                                                   
	private static final void fillObjectToMap(){                                        
		List<CodeInfo> listCode = getCodeFromDB(CodesItem.liftFlag);                                                                 
		Map map = new HashMap(listCode.size());        
		String temp = null;	                          
		Map map2 = new HashMap(listCode.size());                           
		for (int i=0; i<listCode.size();i++){                                            
			CodeInfo codeInfo = listCode.get(i);                                              
			temp = codeInfo.getCiSpCode();                                              
			map.put(temp, codeInfo.getCiSpName());                                     
			map2.put(temp, new LiftFlag(temp));                                        
		}                                                                                 
		mapCategoryCode.put(CodesItem.liftFlag, map);                                
		mapCodeObject.put(CodesItem.liftFlag, map2);		                              
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




	public static final LiftFlag Wu                                      		= getObject("0");
	public static final LiftFlag You                                     		= getObject("1");
}

