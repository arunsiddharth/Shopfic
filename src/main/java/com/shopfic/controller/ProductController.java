package com.shopfic.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.shopfic.model.Product;
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
		List<Product> products = ps.productsLatest();
		mv.addObject("products",products);
		return mv;
	}
	@RequestMapping(value="category",method=RequestMethod.GET)
	public ModelAndView category(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mv = new ModelAndView("index");
		ProductService ps = new ProductService();
		Map<String, Map<String,Integer> > list= ps.productList();
		System.out.println(list);
		mv.addObject("productlist",list);
		List<Product> products = ps.productsCategory(request.getParameter("category"));
		mv.addObject("products",products);
		return mv;
	}
	@RequestMapping(value="subcategory",method=RequestMethod.GET)
	public ModelAndView subCategory(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mv = new ModelAndView("index");
		ProductService ps = new ProductService();
		Map<String, Map<String,Integer> > list= ps.productList();
		System.out.println(list);
		mv.addObject("productlist",list);
		List<Product> products = ps.productsSubcategory(request.getParameter("category"), request.getParameter("subcategory"));;
		mv.addObject("products",products);
		return mv;
	}
	@RequestMapping(value="search",method=RequestMethod.GET)
	public ModelAndView searchProducts(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mv = new ModelAndView("index");
		ProductService ps = new ProductService();
		Map<String, Map<String,Integer> > list= ps.productList();
		System.out.println(list);
		mv.addObject("productlist",list);
		List<Product> products = ps.productsSearch(request.getParameter("pattern"));
		mv.addObject("products",products);
		return mv;
	}
}
