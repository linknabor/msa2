package com.eshequ.msa.codes;																														
/**Auto Created by VBScript Do not modify!*/												 
import java.util.HashMap;                                                                  
import java.util.List;                                                       
import java.util.Map;                                                           
import com.eshequ.msa.codes.model.CodeInfo;                     
/** Auto generated */												 
public class PlatChannel extends CodesItem {                                      
	private PlatChannel(String code){super(CodesItem.platChannel,code);}	        
	public static String getValue(String code){                                         
		return CodesItem.getValue(CodesItem.platChannel,code);                         
	}                                                                                   
	public static String getValue(PlatChannel code){	                                
		return CodesItem.getValue(CodesItem.platChannel,code.toString());              
	}                                                                                   
	public static List<CodeInfo> getCodeList(){	                                                
		return CodesItem.getCodeList(CodesItem.platChannel);                           
	}                                                                                   
	public static final PlatChannel getObject(String code){                          
		Map map=(Map)mapCodeObject.get(CodesItem.platChannel);                         
		return map==null?null:(PlatChannel)map.get(code);                                             
	}                                                                                   
	static {		fillObjectToMap();	}                                                   
	private static final void fillObjectToMap(){                                        
		List<CodeInfo> listCode = getCodeFromDB(CodesItem.platChannel);                                                                 
		Map map = new HashMap(listCode.size());        
		String temp = null;	                          
		Map map2 = new HashMap(listCode.size());                           
		for (int i=0; i<listCode.size();i++){                                            
			CodeInfo codeInfo = listCode.get(i);                                              
			temp = codeInfo.getCiSpCode();                                              
			map.put(temp, codeInfo.getCiSpName());                                     
			map2.put(temp, new PlatChannel(temp));                                        
		}                                                                                 
		mapCategoryCode.put(CodesItem.platChannel, map);                                
		mapCodeObject.put(CodesItem.platChannel, map2);		                              
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




	public static final PlatChannel Weixin                                  		= getObject("0");
	public static final PlatChannel Swiftpass                               		= getObject("1");
	public static final PlatChannel Sand                                    		= getObject("2");
	public static final PlatChannel Allinpay                                		= getObject("3");
	public static final PlatChannel Lakala                                  		= getObject("4");
	public static final PlatChannel XingyePos                               		= getObject("5");
	public static final PlatChannel JianHangPos                             		= getObject("6");
	public static final PlatChannel JiaxingBKPos                            		= getObject("7");
	public static final PlatChannel LakalaPos                               		= getObject("8");
	public static final PlatChannel CbcOnline                               		= getObject("9");
	public static final PlatChannel HuiDouQuan                              		= getObject("A");
	public static final PlatChannel Tenpay                                  		= getObject("Y");
	public static final PlatChannel Cash                                    		= getObject("Z");
}

