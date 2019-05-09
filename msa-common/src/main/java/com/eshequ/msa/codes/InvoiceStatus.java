package com.eshequ.msa.codes;																														
/**Auto Created by VBScript Do not modify!*/												 
import java.util.HashMap;                                                                  
import java.util.List;                                                       
import java.util.Map;                                                           
import com.eshequ.msa.codes.model.CodeInfo;                     
/** Auto generated */												 
public class InvoiceStatus extends CodesItem {                                      
	private InvoiceStatus(String code){super(CodesItem.invoiceStatus,code);}	        
	public static String getValue(String code){                                         
		return CodesItem.getValue(CodesItem.invoiceStatus,code);                         
	}                                                                                   
	public static String getValue(InvoiceStatus code){	                                
		return CodesItem.getValue(CodesItem.invoiceStatus,code.toString());              
	}                                                                                   
	public static List<CodeInfo> getCodeList(){	                                                
		return CodesItem.getCodeList(CodesItem.invoiceStatus);                           
	}                                                                                   
	public static final InvoiceStatus getObject(String code){                          
		Map map=(Map)mapCodeObject.get(CodesItem.invoiceStatus);                         
		return map==null?null:(InvoiceStatus)map.get(code);                                             
	}                                                                                   
	static {		fillObjectToMap();	}                                                   
	private static final void fillObjectToMap(){                                        
		List<CodeInfo> listCode = getCodeFromDB(CodesItem.invoiceStatus);                                                                 
		Map map = new HashMap(listCode.size());        
		String temp = null;	                          
		Map map2 = new HashMap(listCode.size());                           
		for (int i=0; i<listCode.size();i++){                                            
			CodeInfo codeInfo = listCode.get(i);                                              
			temp = codeInfo.getCiSpCode();                                              
			map.put(temp, codeInfo.getCiSpName());                                     
			map2.put(temp, new InvoiceStatus(temp));                                        
		}                                                                                 
		mapCategoryCode.put(CodesItem.invoiceStatus, map);                                
		mapCodeObject.put(CodesItem.invoiceStatus, map2);		                              
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




	public static final InvoiceStatus ShenQingZhong                           		= getObject("01");
	public static final InvoiceStatus KaiPiaoZhong                            		= getObject("02");
	public static final InvoiceStatus ChengGong                               		= getObject("03");
	public static final InvoiceStatus ZuoFeiZhong                             		= getObject("97");
	public static final InvoiceStatus ShiBai                                  		= getObject("98");
	public static final InvoiceStatus ZuoFei                                  		= getObject("99");
}

