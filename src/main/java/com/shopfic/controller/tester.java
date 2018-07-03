package com.shopfic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class tester {
	@RequestMapping(value="/")
	public ModelAndView test(){
		ModelAndView mv = new ModelAndView("index.jsp");
		return mv;
	}
}
