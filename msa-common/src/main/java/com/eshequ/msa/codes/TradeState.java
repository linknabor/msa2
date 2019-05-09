package com.eshequ.msa.codes;																														
/**Auto Created by VBScript Do not modify!*/												 
import java.util.HashMap;                                                                  
import java.util.List;                                                       
import java.util.Map;                                                           
import com.eshequ.msa.codes.model.CodeInfo;                     
/** Auto generated */												 
public class TradeState extends CodesItem {                                      
	private TradeState(String code){super(CodesItem.tradeState,code);}	        
	public static String getValue(String code){                                         
		return CodesItem.getValue(CodesItem.tradeState,code);                         
	}                                                                                   
	public static String getValue(TradeState code){	                                
		return CodesItem.getValue(CodesItem.tradeState,code.toString());              
	}                                                                                   
	public static List<CodeInfo> getCodeList(){	                                                
		return CodesItem.getCodeList(CodesItem.tradeState);                           
	}                                                                                   
	public static final TradeState getObject(String code){                          
		Map map=(Map)mapCodeObject.get(CodesItem.tradeState);                         
		return map==null?null:(TradeState)map.get(code);                                             
	}                                                                                   
	static {		fillObjectToMap();	}                                                   
	private static final void fillObjectToMap(){                                        
		List<CodeInfo> listCode = getCodeFromDB(CodesItem.tradeState);                                                                 
		Map map = new HashMap(listCode.size());        
		String temp = null;	                          
		Map map2 = new HashMap(listCode.size());                           
		for (int i=0; i<listCode.size();i++){                                            
			CodeInfo codeInfo = listCode.get(i);                                              
			temp = codeInfo.getCiSpCode();                                              
			map.put(temp, codeInfo.getCiSpName());                                     
			map2.put(temp, new TradeState(temp));                                        
		}                                                                                 
		mapCategoryCode.put(CodesItem.tradeState, map);                                
		mapCodeObject.put(CodesItem.tradeState, map2);		                              
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




	public static final TradeState ZaiTu                                   		= getObject("01");
	public static final TradeState YiZhiFu                                 		= getObject("02");
	public static final TradeState WeiZhiFu                                		= getObject("03");
	public static final TradeState YiQingSuan                              		= getObject("04");
	public static final TradeState YiFanDian                               		= getObject("05");
}

