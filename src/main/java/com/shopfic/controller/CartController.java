package com.shopfic.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.shopfic.model.Cart;
import com.shopfic.service.CartService;

@Controller
public class CartController {
	
	@RequestMapping(value="addcart",method=RequestMethod.POST)
	public ModelAndView addCart(HttpServletRequest request, HttpServletResponse response){
		//fetch count,uid,pid
		Cart c = new Cart();
		HttpSession session = request.getSession();
		int uid=Integer.parseInt(session.getAttribute("uid").toString());
		c.setPid(Integer.parseInt(request.getParameter("pid")));
		c.setUid(uid);
		c.setCount(Integer.parseInt(request.getParameter("count")));
		//System.out.println(c);
		CartService cs = new CartService();
		cs.addCart(c);
		return new ModelAndView("redirect:/viewcart?view=fresh");
	}

	@RequestMapping(value="viewcart",method=RequestMethod.GET)
	public ModelAndView viewCart(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mv = new ModelAndView("viewcart");
		//fetch and items into cart for given uid
		List<Cart> cart = new ArrayList<Cart>();
		HttpSession session = request.getSession();
		int uid=Integer.parseInt(session.getAttribute("uid").toString());
		CartService cs = new CartService();
		if(request.getParameter("view").equals("old")){cart = cs.viewCartOld(uid);}
		else cart = cs.viewCartFresh(uid);
		mv.addObject("cart",cart);
		return mv;
	}

	@RequestMapping(value="updatecart",method=RequestMethod.POST)
	public ModelAndView updateCart(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		int uid=Integer.parseInt(session.getAttribute("uid").toString());
		CartService cs = new CartService();
		int pid = Integer.parseInt(request.getParameter("pid"));
		int count = Integer.parseInt(request.getParameter("count"));
		cs.updateCartProduct(uid, pid, count);
		return new ModelAndView("redirect:/viewcart?view=fresh");
	}
	@RequestMapping(value="deletecartproduct",method=RequestMethod.GET)
	public ModelAndView deleteCart(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		int uid=Integer.parseInt(session.getAttribute("uid").toString());
		CartService cs = new CartService();
		int pid = Integer.parseInt(request.getParameter("pid"));
		cs.deleteCartProduct(pid, uid);
		return new ModelAndView("redirect:/viewcart?view=fresh");
	}
	@RequestMapping(value="buycart",method=RequestMethod.POST)
	public ModelAndView buyCart(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		int uid=Integer.parseInt(session.getAttribute("uid").toString());
		CartService cs = new CartService();
		Boolean cod = Boolean.parseBoolean(request.getParameter("cod"));
		cs.buyCart(uid, cod);
		return new ModelAndView("redirect:/viewcart?view=old");
	}
}
