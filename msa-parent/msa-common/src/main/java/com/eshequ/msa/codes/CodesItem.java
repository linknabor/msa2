package com.eshequ.msa.codes;																																
/**Auto Created by VBScript Do not modify!*/																								 	
import java.util.ArrayList;                                                                 	
import java.util.Collections;                                                         				
import java.util.Comparator;                                                    							
import java.util.HashMap;                                       															
import java.util.Iterator;                                      															
import java.util.List;                                                           						
import java.util.Map;                                                           							
import javax.annotation.PostConstruct;                                                       
import org.springframework.beans.factory.annotation.Autowired;                               
import org.springframework.stereotype.Component;                                             
import com.eshequ.msa.codes.mapper.CodeInfoMapper;                                           
import com.eshequ.msa.codes.model.CodeInfo;                                                  
import com.eshequ.msa.exception.NotFoundException;                                           
@Component                                                                                   
public class CodesItem {                                                      			
	
	protected static final String payMethod=new String("1");
	
	protected static final String platChannel=new String("2");
	
	protected static final String isFlag=new String("3");
	
	protected static final String mergerStatus=new String("5");
	
	protected static final String cardType=new String("6");
	
	protected static final String mchStatus=new String("7");
	
	protected static final String accountStatus=new String("8");
	
	protected static final String payChannel=new String("9");
	
	protected static final String reginType=new String("12");
	
	protected static final String dataSource=new String("13");
	
	protected static final String methodType=new String("14");


	protected static final Map mapCat= new HashMap(100);
	static{
		mapCat.put("PayMethod",payMethod);
		mapCat.put("PlatChannel",platChannel);
		mapCat.put("IsFlag",isFlag);
		mapCat.put("MergerStatus",mergerStatus);
		mapCat.put("CardType",cardType);
		mapCat.put("MchStatus",mchStatus);
		mapCat.put("AccountStatus",accountStatus);
		mapCat.put("PayChannel",payChannel);
		mapCat.put("ReginType",reginType);
		mapCat.put("DataSource",dataSource);
		mapCat.put("MethodType",methodType);
	}


	protected static final  Map mapCategoryCode=new HashMap(100);																			
	protected static final  Map mapCodeObject=new HashMap(100);																				
 private static CodeInfoMapper mapper;
 @Autowired
 private CodeInfoMapper codeInfoMapper;
	public boolean equals(String obj) {                                                               
		return this.code.equals(obj);                                                        						
	}                                                                                                 
	public boolean equals(Object obj) {  
		return this.code.equals(obj);                                                           				
	}                                                                                            			
	public String toString(){ return this.code; }                                                     
	                                                                                                  
	/**convert 2 cn value                                                                 
	 * @param category                                                     
	 * @param code                                                                   
	 * @return                                                                                        
	 */                                                                                               
	protected static String getValue(String category,String code){                                    
		Map map=(Map)mapCategoryCode.get(category);                                                     
		if(map==null) return "";                                    						              				
		String temp=(String)map.get(code);                                    						  						
		return (temp==null)? "" : temp;                                    														
	}

		public static List<CodeInfo> getCodeList(String categoryCode){
		   List<CodeInfo> list = new ArrayList<CodeInfo>();
		   Map mp = (Map)mapCategoryCode.get(categoryCode);
		   if(mp==null) return list;
		   String temp;
		   for (Iterator it = mp.keySet().iterator(); it.hasNext();){
		      temp = (String)it.next();
		      CodeInfo codeInfo = new CodeInfo();
		      codeInfo.setCiSpClass(categoryCode);
		      codeInfo.setCiSpCode(temp);
		      codeInfo.setCiSpName((String)mp.get(temp));
		      list.add(codeInfo);
		      }
		      Collections.sort(list, new Comparator<CodeInfo>() {
		         @Override
		         public int compare(CodeInfo o1, CodeInfo o2) {
		            int i = o1.getCiSpCode().compareTo(o2.getCiSpCode());
		      			if (i==0) {return o1.getCiSpName().compareTo(o2.getCiSpName());}
		            return i;
		         }
		      });
		      return list;
   }

		public static Map getCategoryItems(String categoryName){
		   String categoryCode = (String)mapCat.get(categoryName);
		   if(categoryCode==null)
		      throw new RuntimeException(categoryName + "code category dose not initialize!");
		   
		   Map mp=(Map)mapCategoryCode.get(categoryCode);
		   return mp;
		}

	/**                                                                                               
	 * convert 2 cn value                                                                             
	 * @return                                                                                        
	 */                                                                                               
	public  String getValue(){                                                                        
		 return getValue( this.category,this.code);                                                     
	}                                                                                                 
	public CodesItem() {
	}                                                                                                 
	
	@PostConstruct                                                                                    
	public void init() {                                                                              
	   mapper = this.codeInfoMapper;                                                                  
	}                                                                                                 
	/**                                                                                               
	 * construction                                                                                      
	 * @param category                                                       
	 * @param code                                                                 
	 */                                                                                               
	protected  CodesItem(String category,String code){                                                
		this.category=category;                                                                         
		this.code=code;                                                                                 
	}                                                                                                 
	                                                                                                  
	protected static final List<CodeInfo> getCodeFromDB(String category) {             							
			List<CodeInfo> list = mapper.selectByClass(category);                                  
			if(list.size()<1){	                                                                        
				throw new NotFoundException("current code item can't be found in db! code=" + category);             
			}                                                                                     
		return list;                                                                                      
	}                                                                                                 
	private String code;                                                                              
	protected String category;                                                                        
	public  String getCategoryCode(){                                                
		return category;                                                                                 
	}                                                                                                 
}                                                                                                  



