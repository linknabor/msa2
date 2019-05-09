package com.eshequ.msa.codes;																														
/**Auto Created by VBScript Do not modify!*/												 
import java.util.HashMap;                                                                  
import java.util.List;                                                       
import java.util.Map;                                                           
import com.eshequ.msa.codes.model.CodeInfo;                     
/** Auto generated */												 
public class HouseModel extends CodesItem {                                      
	private HouseModel(String code){super(CodesItem.houseModel,code);}	        
	public static String getValue(String code){                                         
		return CodesItem.getValue(CodesItem.houseModel,code);                         
	}                                                                                   
	public static String getValue(HouseModel code){	                                
		return CodesItem.getValue(CodesItem.houseModel,code.toString());              
	}                                                                                   
	public static List<CodeInfo> getCodeList(){	                                                
		return CodesItem.getCodeList(CodesItem.houseModel);                           
	}                                                                                   
	public static final HouseModel getObject(String code){                          
		Map map=(Map)mapCodeObject.get(CodesItem.houseModel);                         
		return map==null?null:(HouseModel)map.get(code);                                             
	}                                                                                   
	static {		fillObjectToMap();	}                                                   
	private static final void fillObjectToMap(){                                        
		List<CodeInfo> listCode = getCodeFromDB(CodesItem.houseModel);                                                                 
		Map map = new HashMap(listCode.size());        
		String temp = null;	                          
		Map map2 = new HashMap(listCode.size());                           
		for (int i=0; i<listCode.size();i++){                                            
			CodeInfo codeInfo = listCode.get(i);                                              
			temp = codeInfo.getCiSpCode();                                              
			map.put(temp, codeInfo.getCiSpName());                                     
			map2.put(temp, new HouseModel(temp));                                        
		}                                                                                 
		mapCategoryCode.put(CodesItem.houseModel, map);                                
		mapCodeObject.put(CodesItem.houseModel, map2);		                              
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




	public static final HouseModel YiShi                                   		= getObject("01");
	public static final HouseModel ErShi                                   		= getObject("02");
	public static final HouseModel SanShi                                  		= getObject("03");
	public static final HouseModel SiShi                                   		= getObject("04");
	public static final HouseModel WuShi                                   		= getObject("05");
	public static final HouseModel WuShiYiShang                            		= getObject("06");
	public static final HouseModel QiTa                                    		= getObject("99");
}

