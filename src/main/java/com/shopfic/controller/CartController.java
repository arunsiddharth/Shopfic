package com.shopfic.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.shopfic.model.Cart;

@Controller
public class CartController {
	
	@RequestMapping(value="addcart",method=RequestMethod.GET)
	public ModelAndView addCart(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mv = new ModelAndView();
		Cart c = new Cart();
		
		return mv;
	}

	@RequestMapping(value="viewcart",method=RequestMethod.GET)
	public ModelAndView viewCart(){
		ModelAndView mv = new ModelAndView("viewcart");
		//fetch and items into cart for given uid
		Cart c = new Cart();
		
		return mv;
	}

	@RequestMapping(value="updatecart",method=RequestMethod.GET)
	public ModelAndView updateCart(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mv = new ModelAndView();
		Cart c = new Cart();
		
		return mv;
	}
}
