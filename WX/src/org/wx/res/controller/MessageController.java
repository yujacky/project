/**
 * 
 */
package org.wx.res.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/Message.do")
public class MessageController {
	
	private static final Logger logger = Logger.getLogger(MessageController.class);
	
	@RequestMapping(value="/get")
	public String get(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		
		return "true";
	}

}
