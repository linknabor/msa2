/**
 * 
 */
package com.eshequ.msa.finance.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eshequ.msa.finance.service.TestService;

/**
 * @author david
 *
 */
@RestController
public class TestController extends com.eshequ.msa.common.BaseController {

	@Autowired
	private TestService testService;
	
	@GetMapping("/hello")
    public String createCode(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {
    	
		String str = testService.sayHello();
		return "hello world!  \r\n" + str;
    }
	
}
