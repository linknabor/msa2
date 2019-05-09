package com.eshequ.msa.codes;																														
/**Auto Created by VBScript Do not modify!*/												 
import java.util.HashMap;                                                                  
import java.util.List;                                                       
import java.util.Map;                                                           
import com.eshequ.msa.codes.model.CodeInfo;                     
/** Auto generated */												 
public class Activity extends CodesItem {                                      
	private Activity(String code){super(CodesItem.activity,code);}	        
	public static String getValue(String code){                                         
		return CodesItem.getValue(CodesItem.activity,code);                         
	}                                                                                   
	public static String getValue(Activity code){	                                
		return CodesItem.getValue(CodesItem.activity,code.toString());              
	}                                                                                   
	public static List<CodeInfo> getCodeList(){	                                                
		return CodesItem.getCodeList(CodesItem.activity);                           
	}                                                                                   
	public static final Activity getObject(String code){                          
		Map map=(Map)mapCodeObject.get(CodesItem.activity);                         
		return map==null?null:(Activity)map.get(code);                                             
	}                                                                                   
	static {		fillObjectToMap();	}                                                   
	private static final void fillObjectToMap(){                                        
		List<CodeInfo> listCode = getCodeFromDB(CodesItem.activity);                                                                 
		Map map = new HashMap(listCode.size());        
		String temp = null;	                          
		Map map2 = new HashMap(listCode.size());                           
		for (int i=0; i<listCode.size();i++){                                            
			CodeInfo codeInfo = listCode.get(i);                                              
			temp = codeInfo.getCiSpCode();                                              
			map.put(temp, codeInfo.getCiSpName());                                     
			map2.put(temp, new Activity(temp));                                        
		}                                                                                 
		mapCategoryCode.put(CodesItem.activity, map);                                
		mapCodeObject.put(CodesItem.activity, map2);		                              
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




	public static final Activity Xin                                     		= getObject("0");
	public static final Activity HuoYue                                  		= getObject("1");
	public static final Activity QianZaiLiuShi                           		= getObject("2");
}

