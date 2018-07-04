package com.shopfic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("seller")
public class Seller {
	@RequestMapping("index")
	public ModelAndView sellerIndex(){
		ModelAndView mv = new ModelAndView();
		
		return mv;
	}
}
