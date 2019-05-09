package com.eshequ.msa.codes;																														
/**Auto Created by VBScript Do not modify!*/												 
import java.util.HashMap;                                                                  
import java.util.List;                                                       
import java.util.Map;                                                           
import com.eshequ.msa.codes.model.CodeInfo;                     
/** Auto generated */												 
public class HousePropertyKind extends CodesItem {                                      
	private HousePropertyKind(String code){super(CodesItem.housePropertyKind,code);}	        
	public static String getValue(String code){                                         
		return CodesItem.getValue(CodesItem.housePropertyKind,code);                         
	}                                                                                   
	public static String getValue(HousePropertyKind code){	                                
		return CodesItem.getValue(CodesItem.housePropertyKind,code.toString());              
	}                                                                                   
	public static List<CodeInfo> getCodeList(){	                                                
		return CodesItem.getCodeList(CodesItem.housePropertyKind);                           
	}                                                                                   
	public static final HousePropertyKind getObject(String code){                          
		Map map=(Map)mapCodeObject.get(CodesItem.housePropertyKind);                         
		return map==null?null:(HousePropertyKind)map.get(code);                                             
	}                                                                                   
	static {		fillObjectToMap();	}                                                   
	private static final void fillObjectToMap(){                                        
		List<CodeInfo> listCode = getCodeFromDB(CodesItem.housePropertyKind);                                                                 
		Map map = new HashMap(listCode.size());        
		String temp = null;	                          
		Map map2 = new HashMap(listCode.size());                           
		for (int i=0; i<listCode.size();i++){                                            
			CodeInfo codeInfo = listCode.get(i);                                              
			temp = codeInfo.getCiSpCode();                                              
			map.put(temp, codeInfo.getCiSpName());                                     
			map2.put(temp, new HousePropertyKind(temp));                                        
		}                                                                                 
		mapCategoryCode.put(CodesItem.housePropertyKind, map);                                
		mapCodeObject.put(CodesItem.housePropertyKind, map2);		                              
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




	public static final HousePropertyKind ShangPinFang                            		= getObject("1");
	public static final HousePropertyKind ShouHouGongFang                         		= getObject("2");
	public static final HousePropertyKind YouXianChanQuan                         		= getObject("3");
	public static final HousePropertyKind XiTongGongFang                          		= getObject("4");
	public static final HousePropertyKind ZhiGuanGongFang                         		= getObject("5");
	public static final HousePropertyKind JingShiFang                             		= getObject("6");
	public static final HousePropertyKind LianZuFang                              		= getObject("7");
	public static final HousePropertyKind DongQianFang                            		= getObject("8");
	public static final HousePropertyKind ZiGuanGongFang                          		= getObject("A");
	public static final HousePropertyKind DaiGuanGongFang                         		= getObject("B");
	public static final HousePropertyKind ZuLinFang                               		= getObject("C");
	public static final HousePropertyKind ZuLinKongZhiFang                        		= getObject("D");
	public static final HousePropertyKind ChanQuanKongZhiFang                     		= getObject("E");
	public static final HousePropertyKind FeiJuZuLin                              		= getObject("F");
	public static final HousePropertyKind FeiJuChanQuan                           		= getObject("G");
	public static final HousePropertyKind JuGaiFei                                		= getObject("H");
	public static final HousePropertyKind ZuLinShangPu                            		= getObject("I");
	public static final HousePropertyKind ChanQuanShangPu                         		= getObject("J");
	public static final HousePropertyKind KongZhiShangPu                          		= getObject("K");
}

