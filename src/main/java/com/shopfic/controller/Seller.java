package com.shopfic.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.shopfic.model.Product;
import com.shopfic.service.ProductService;

@Controller
@RequestMapping("seller")
public class Seller {
	@RequestMapping("index")
	public ModelAndView sellerIndex(HttpServletRequest request, HttpServletResponse response){
		HttpSession session= request.getSession();
		if(session.getAttribute("sid")==null)return new ModelAndView("redirect:/index");
		ModelAndView mv = new ModelAndView("seller_index");
		ProductService ps = new ProductService();
		int sid = Integer.parseInt(session.getAttribute("sid").toString());//chance of error
		List<Product> list = ps.productSeller(sid);
		return mv;
	}
	@RequestMapping(value="add", method=RequestMethod.GET)
	public ModelAndView addProduct(HttpServletRequest request, HttpServletResponse response){
		HttpSession session= request.getSession();
		if(session.getAttribute("sid")==null)return new ModelAndView("redirect:/index");
		ModelAndView mv = new ModelAndView("add");
		return mv;
	}
	@RequestMapping(value="add", method=RequestMethod.POST)
	public ModelAndView doAddProduct(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mv = new ModelAndView();
		return new ModelAndView("redirect:/seller/index");
	}
	@RequestMapping(value="delete", method=RequestMethod.GET)
	public ModelAndView deleteProduct(HttpServletRequest request, HttpServletResponse response){
		HttpSession session= request.getSession();
		if(session.getAttribute("sid")==null)return new ModelAndView("redirect:/index");
		ModelAndView mv = new ModelAndView();
		return mv;
	}
	@RequestMapping(value="update", method=RequestMethod.GET)
	public ModelAndView updateProduct(){
		ModelAndView mv = new ModelAndView("update");
		return mv;
	}
	@RequestMapping(value="update", method=RequestMethod.POST)
	public ModelAndView doUpdateProduct(HttpServletRequest request, HttpServletResponse response){
		HttpSession session= request.getSession();
		if(session.getAttribute("sid")==null)return new ModelAndView("redirect:/index");
		return new ModelAndView("redirect:/seller/index");
	}
}
