package com.eshequ.msa.codes;																														
/**Auto Created by VBScript Do not modify!*/												 
import java.util.HashMap;                                                                  
import java.util.List;                                                       
import java.util.Map;                                                           
import com.eshequ.msa.codes.model.CodeInfo;                     
/** Auto generated */												 
public class InvoiceType extends CodesItem {                                      
	private InvoiceType(String code){super(CodesItem.invoiceType,code);}	        
	public static String getValue(String code){                                         
		return CodesItem.getValue(CodesItem.invoiceType,code);                         
	}                                                                                   
	public static String getValue(InvoiceType code){	                                
		return CodesItem.getValue(CodesItem.invoiceType,code.toString());              
	}                                                                                   
	public static List<CodeInfo> getCodeList(){	                                                
		return CodesItem.getCodeList(CodesItem.invoiceType);                           
	}                                                                                   
	public static final InvoiceType getObject(String code){                          
		Map map=(Map)mapCodeObject.get(CodesItem.invoiceType);                         
		return map==null?null:(InvoiceType)map.get(code);                                             
	}                                                                                   
	static {		fillObjectToMap();	}                                                   
	private static final void fillObjectToMap(){                                        
		List<CodeInfo> listCode = getCodeFromDB(CodesItem.invoiceType);                                                                 
		Map map = new HashMap(listCode.size());        
		String temp = null;	                          
		Map map2 = new HashMap(listCode.size());                           
		for (int i=0; i<listCode.size();i++){                                            
			CodeInfo codeInfo = listCode.get(i);                                              
			temp = codeInfo.getCiSpCode();                                              
			map.put(temp, codeInfo.getCiSpName());                                     
			map2.put(temp, new InvoiceType(temp));                                        
		}                                                                                 
		mapCategoryCode.put(CodesItem.invoiceType, map);                                
		mapCodeObject.put(CodesItem.invoiceType, map2);		                              
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




	public static final InvoiceType ZhiZhiFaPiao                            		= getObject("0");
	public static final InvoiceType DianZiFaPiao                            		= getObject("1");
}

