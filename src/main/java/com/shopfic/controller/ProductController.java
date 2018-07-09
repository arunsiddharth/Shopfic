package com.shopfic.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.shopfic.model.Comment;
import com.shopfic.model.Product;
import com.shopfic.model.ProductDetailed;
import com.shopfic.model.Rating;
import com.shopfic.service.ProductService;

@Controller
public class ProductController {
	
	@RequestMapping(value={"/","index"},method=RequestMethod.GET)
	public ModelAndView index(){
		ModelAndView mv = new ModelAndView("index");
		ProductService ps = new ProductService();
		Map<String, Map<String,Integer> > list= ps.productList();
		//System.out.println(list);
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
		//System.out.println(list);
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
		//System.out.println(list);
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
	@RequestMapping(value="viewproduct",method=RequestMethod.GET)
	public ModelAndView product(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mv = new ModelAndView("product");
		ProductService ps = new ProductService();
		Map<String, Map<String,Integer> > list= ps.productList();
		System.out.println(list);
		int pid = Integer.parseInt(request.getParameter("pid"));
		mv.addObject("productlist",list);
		ProductDetailed product = new ProductDetailed();
		product = ps.getProductDetailed(pid);
		mv.addObject("product",product);
		//Add product rating
		Rating rating = ps.productRating(pid);
		mv.addObject("ratings", rating);
		return mv;
	}
	
	@RequestMapping(value="addrating",method=RequestMethod.POST)
	public ModelAndView addrating(HttpServletRequest request,HttpServletResponse response){
		Comment c = new Comment();
		if(request.getSession().getAttribute("uid")==null)return new ModelAndView("redirect:/login");
		int uid = Integer.parseInt(request.getSession().getAttribute("uid").toString());
		c.setComment(request.getParameter("comment"));
		c.setRating(Integer.parseInt(request.getParameter("rating")));
		c.setUid(uid);
		c.setPid(Integer.parseInt(request.getParameter("pid")));
		ProductService ps = new ProductService();
		ps.setProductRating(c);
		return new ModelAndView("redirect:/viewproduct?pid="+c.getPid());
	}
}
