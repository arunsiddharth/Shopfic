package com.shopfic.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.shopfic.service.ProductService;

@Controller
public class ProductController {
	
	@RequestMapping(value="index",method=RequestMethod.GET)
	public ModelAndView index(){
		ModelAndView mv = new ModelAndView("index");
		ProductService ps = new ProductService();
		Map<String, Map<String,Integer> > list= ps.productList();
		System.out.println(list);
		mv.addObject("productlist",list);
		return mv;
	}
}
