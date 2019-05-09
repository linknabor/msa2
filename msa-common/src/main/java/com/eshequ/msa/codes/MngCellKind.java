package com.eshequ.msa.codes;																														
/**Auto Created by VBScript Do not modify!*/												 
import java.util.HashMap;                                                                  
import java.util.List;                                                       
import java.util.Map;                                                           
import com.eshequ.msa.codes.model.CodeInfo;                     
/** Auto generated */												 
public class MngCellKind extends CodesItem {                                      
	private MngCellKind(String code){super(CodesItem.mngCellKind,code);}	        
	public static String getValue(String code){                                         
		return CodesItem.getValue(CodesItem.mngCellKind,code);                         
	}                                                                                   
	public static String getValue(MngCellKind code){	                                
		return CodesItem.getValue(CodesItem.mngCellKind,code.toString());              
	}                                                                                   
	public static List<CodeInfo> getCodeList(){	                                                
		return CodesItem.getCodeList(CodesItem.mngCellKind);                           
	}                                                                                   
	public static final MngCellKind getObject(String code){                          
		Map map=(Map)mapCodeObject.get(CodesItem.mngCellKind);                         
		return map==null?null:(MngCellKind)map.get(code);                                             
	}                                                                                   
	static {		fillObjectToMap();	}                                                   
	private static final void fillObjectToMap(){                                        
		List<CodeInfo> listCode = getCodeFromDB(CodesItem.mngCellKind);                                                                 
		Map map = new HashMap(listCode.size());        
		String temp = null;	                          
		Map map2 = new HashMap(listCode.size());                           
		for (int i=0; i<listCode.size();i++){                                            
			CodeInfo codeInfo = listCode.get(i);                                              
			temp = codeInfo.getCiSpCode();                                              
			map.put(temp, codeInfo.getCiSpName());                                     
			map2.put(temp, new MngCellKind(temp));                                        
		}                                                                                 
		mapCategoryCode.put(CodesItem.mngCellKind, map);                                
		mapCodeObject.put(CodesItem.mngCellKind, map2);		                              
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




	public static final MngCellKind PuTongWuYe                              		= getObject("1");
	public static final MngCellKind GongJianPeiTao                          		= getObject("2");
	public static final MngCellKind WuYeYongFang                            		= getObject("3");
	public static final MngCellKind RenFangGongCheng                        		= getObject("4");
}

