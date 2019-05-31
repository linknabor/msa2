package com.eshequ.msa.codes;																														
/**Auto Created by VBScript Do not modify!*/												 
import java.util.HashMap;                                                                  
import java.util.List;                                                       
import java.util.Map;                                                           
import com.eshequ.msa.codes.model.CodeInfo;                     
/** Auto generated */												 
public class MergerStatus extends CodesItem {                                      
	private MergerStatus(String code){super(CodesItem.mergerStatus,code);}	        
	public static String getValue(String code){                                         
		return CodesItem.getValue(CodesItem.mergerStatus,code);                         
	}                                                                                   
	public static String getValue(MergerStatus code){	                                
		return CodesItem.getValue(CodesItem.mergerStatus,code.toString());              
	}                                                                                   
	public static List<CodeInfo> getCodeList(){	                                                
		return CodesItem.getCodeList(CodesItem.mergerStatus);                           
	}                                                                                   
	public static final MergerStatus getObject(String code){                          
		Map map=(Map)mapCodeObject.get(CodesItem.mergerStatus);                         
		return map==null?null:(MergerStatus)map.get(code);                                             
	}                                                                                   
	static {		fillObjectToMap();	}                                                   
	private static final void fillObjectToMap(){                                        
		List<CodeInfo> listCode = getCodeFromDB(CodesItem.mergerStatus);                                                                 
		Map map = new HashMap(listCode.size());        
		String temp = null;	                          
		Map map2 = new HashMap(listCode.size());                           
		for (int i=0; i<listCode.size();i++){                                            
			CodeInfo codeInfo = listCode.get(i);                                              
			temp = codeInfo.getCiSpCode();                                              
			map.put(temp, codeInfo.getCiSpName());                                     
			map2.put(temp, new MergerStatus(temp));                                        
		}                                                                                 
		mapCategoryCode.put(CodesItem.mergerStatus, map);                                
		mapCodeObject.put(CodesItem.mergerStatus, map2);		                              
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




	public static final MergerStatus ZaiTu                                   		= getObject("01");
	public static final MergerStatus YiZhiFu                                 		= getObject("02");
	public static final MergerStatus WeiZhiFu                                		= getObject("03");
	public static final MergerStatus YiQingSuan                              		= getObject("04");
	public static final MergerStatus YiFanDian                               		= getObject("05");
}

