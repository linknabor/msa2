package com.eshequ.msa.codes;																														
/**Auto Created by VBScript Do not modify!*/												 
import java.util.HashMap;                                                                  
import java.util.List;                                                       
import java.util.Map;                                                           
import com.eshequ.msa.codes.model.CodeInfo;                     
/** Auto generated */												 
public class MngCellType extends CodesItem {                                      
	private MngCellType(String code){super(CodesItem.mngCellType,code);}	        
	public static String getValue(String code){                                         
		return CodesItem.getValue(CodesItem.mngCellType,code);                         
	}                                                                                   
	public static String getValue(MngCellType code){	                                
		return CodesItem.getValue(CodesItem.mngCellType,code.toString());              
	}                                                                                   
	public static List<CodeInfo> getCodeList(){	                                                
		return CodesItem.getCodeList(CodesItem.mngCellType);                           
	}                                                                                   
	public static final MngCellType getObject(String code){                          
		Map map=(Map)mapCodeObject.get(CodesItem.mngCellType);                         
		return map==null?null:(MngCellType)map.get(code);                                             
	}                                                                                   
	static {		fillObjectToMap();	}                                                   
	private static final void fillObjectToMap(){                                        
		List<CodeInfo> listCode = getCodeFromDB(CodesItem.mngCellType);                                                                 
		Map map = new HashMap(listCode.size());        
		String temp = null;	                          
		Map map2 = new HashMap(listCode.size());                           
		for (int i=0; i<listCode.size();i++){                                            
			CodeInfo codeInfo = listCode.get(i);                                              
			temp = codeInfo.getCiSpCode();                                              
			map.put(temp, codeInfo.getCiSpName());                                     
			map2.put(temp, new MngCellType(temp));                                        
		}                                                                                 
		mapCategoryCode.put(CodesItem.mngCellType, map);                                
		mapCodeObject.put(CodesItem.mngCellType, map2);		                              
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




	public static final MngCellType FangJian                                		= getObject("01");
	public static final MngCellType ShangPu                                 		= getObject("02");
	public static final MngCellType CheWei                                  		= getObject("03");
	public static final MngCellType GuangGaoWei                             		= getObject("04");
	public static final MngCellType DiXiaShi                                		= getObject("05");
}

