package com.eshequ.msa.codes;																														
/**Auto Created by VBScript Do not modify!*/												 
import java.util.HashMap;                                                                  
import java.util.List;                                                       
import java.util.Map;                                                           
import com.eshequ.msa.codes.model.CodeInfo;                     
/** Auto generated */												 
public class InvoiceApplyType extends CodesItem {                                      
	private InvoiceApplyType(String code){super(CodesItem.invoiceApplyType,code);}	        
	public static String getValue(String code){                                         
		return CodesItem.getValue(CodesItem.invoiceApplyType,code);                         
	}                                                                                   
	public static String getValue(InvoiceApplyType code){	                                
		return CodesItem.getValue(CodesItem.invoiceApplyType,code.toString());              
	}                                                                                   
	public static List<CodeInfo> getCodeList(){	                                                
		return CodesItem.getCodeList(CodesItem.invoiceApplyType);                           
	}                                                                                   
	public static final InvoiceApplyType getObject(String code){                          
		Map map=(Map)mapCodeObject.get(CodesItem.invoiceApplyType);                         
		return map==null?null:(InvoiceApplyType)map.get(code);                                             
	}                                                                                   
	static {		fillObjectToMap();	}                                                   
	private static final void fillObjectToMap(){                                        
		List<CodeInfo> listCode = getCodeFromDB(CodesItem.invoiceApplyType);                                                                 
		Map map = new HashMap(listCode.size());        
		String temp = null;	                          
		Map map2 = new HashMap(listCode.size());                           
		for (int i=0; i<listCode.size();i++){                                            
			CodeInfo codeInfo = listCode.get(i);                                              
			temp = codeInfo.getCiSpCode();                                              
			map.put(temp, codeInfo.getCiSpName());                                     
			map2.put(temp, new InvoiceApplyType(temp));                                        
		}                                                                                 
		mapCategoryCode.put(CodesItem.invoiceApplyType, map);                                
		mapCodeObject.put(CodesItem.invoiceApplyType, map2);		                              
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




	public static final InvoiceApplyType KaiJuFaPiao                             		= getObject("0");
	public static final InvoiceApplyType HongChongFaPiao                         		= getObject("1");
}

