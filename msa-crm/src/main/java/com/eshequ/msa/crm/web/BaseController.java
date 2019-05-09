package com.eshequ.msa.crm.web;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

public class BaseController {

	 @InitBinder
     protected void initBinder(WebDataBinder binder) {
         binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
         binder.registerCustomEditor(int.class, new CustomNumberEditor(Integer.class,true));
         binder.registerCustomEditor(long.class,new CustomNumberEditor(Long.class,true));
         binder.registerCustomEditor(double.class,new CustomNumberEditor(Double.class,true));
         binder.registerCustomEditor(float.class, new CustomNumberEditor(Float.class,true));
     }
	
	
}
