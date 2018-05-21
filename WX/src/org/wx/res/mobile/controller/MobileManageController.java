package org.wx.res.mobile.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MobileManageController {

	@RequestMapping(value = "mobile/index.do")
	public String index() {
		return "/mobile/portal";
	}
		
	@RequestMapping(value = "mobile/gotoPage.do")
	public String gotoPage(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String pageId = request.getParameter("pageId");
		model.put("pageId", pageId);
		
		return "/mobile/article";
	}
}
