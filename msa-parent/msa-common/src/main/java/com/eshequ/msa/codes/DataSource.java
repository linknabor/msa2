package com.eshequ.msa.codes;																														
/**Auto Created by VBScript Do not modify!*/												 
import java.util.HashMap;                                                                  
import java.util.List;                                                       
import java.util.Map;                                                           
import com.eshequ.msa.codes.model.CodeInfo;                     
/** Auto generated */												 
public class DataSource extends CodesItem {                                      
	private DataSource(String code){super(CodesItem.dataSource,code);}	        
	public static String getValue(String code){                                         
		return CodesItem.getValue(CodesItem.dataSource,code);                         
	}                                                                                   
	public static String getValue(DataSource code){	                                
		return CodesItem.getValue(CodesItem.dataSource,code.toString());              
	}                                                                                   
	public static List<CodeInfo> getCodeList(){	                                                
		return CodesItem.getCodeList(CodesItem.dataSource);                           
	}                                                                                   
	public static final DataSource getObject(String code){                          
		Map map=(Map)mapCodeObject.get(CodesItem.dataSource);                         
		return map==null?null:(DataSource)map.get(code);                                             
	}                                                                                   
	static {		fillObjectToMap();	}                                                   
	private static final void fillObjectToMap(){                                        
		List<CodeInfo> listCode = getCodeFromDB(CodesItem.dataSource);                                                                 
		Map map = new HashMap(listCode.size());        
		String temp = null;	                          
		Map map2 = new HashMap(listCode.size());                           
		for (int i=0; i<listCode.size();i++){                                            
			CodeInfo codeInfo = listCode.get(i);                                              
			temp = codeInfo.getCiSpCode();                                              
			map.put(temp, codeInfo.getCiSpName());                                     
			map2.put(temp, new DataSource(temp));                                        
		}                                                                                 
		mapCategoryCode.put(CodesItem.dataSource, map);                                
		mapCodeObject.put(CodesItem.dataSource, map2);		                              
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




	public static final DataSource ShangHaiSaaS                            		= getObject("01");
	public static final DataSource HeBeiSaaS                               		= getObject("02");
	public static final DataSource ZheJiangSaaS                            		= getObject("03");
}

