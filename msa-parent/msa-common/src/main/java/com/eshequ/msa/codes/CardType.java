package com.eshequ.msa.codes;																														
/**Auto Created by VBScript Do not modify!*/												 
import java.util.HashMap;                                                                  
import java.util.List;                                                       
import java.util.Map;                                                           
import com.eshequ.msa.codes.model.CodeInfo;                     
/** Auto generated */												 
public class CardType extends CodesItem {                                      
	private CardType(String code){super(CodesItem.cardType,code);}	        
	public static String getValue(String code){                                         
		return CodesItem.getValue(CodesItem.cardType,code);                         
	}                                                                                   
	public static String getValue(CardType code){	                                
		return CodesItem.getValue(CodesItem.cardType,code.toString());              
	}                                                                                   
	public static List<CodeInfo> getCodeList(){	                                                
		return CodesItem.getCodeList(CodesItem.cardType);                           
	}                                                                                   
	public static final CardType getObject(String code){                          
		Map map=(Map)mapCodeObject.get(CodesItem.cardType);                         
		return map==null?null:(CardType)map.get(code);                                             
	}                                                                                   
	static {		fillObjectToMap();	}                                                   
	private static final void fillObjectToMap(){                                        
		List<CodeInfo> listCode = getCodeFromDB(CodesItem.cardType);                                                                 
		Map map = new HashMap(listCode.size());        
		String temp = null;	                          
		Map map2 = new HashMap(listCode.size());                           
		for (int i=0; i<listCode.size();i++){                                            
			CodeInfo codeInfo = listCode.get(i);                                              
			temp = codeInfo.getCiSpCode();                                              
			map.put(temp, codeInfo.getCiSpName());                                     
			map2.put(temp, new CardType(temp));                                        
		}                                                                                 
		mapCategoryCode.put(CodesItem.cardType, map);                                
		mapCodeObject.put(CodesItem.cardType, map2);		                              
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




	public static final CardType DebitCard                               		= getObject("1");
	public static final CardType CreditCard                              		= getObject("2");
}

