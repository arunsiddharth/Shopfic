package com.shopfic.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.shopfic.model.Address;
import com.shopfic.model.Error;
import com.shopfic.model.Seller;
import com.shopfic.model.User;
import com.shopfic.service.UserService;

@Controller
public class LoginAndRegisterController {
	@RequestMapping(value={"login","seller/login"},method=RequestMethod.GET)
	public ModelAndView showLogin(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mv = new ModelAndView("login");
		return mv;
	}
	
	@RequestMapping(value={"register","seller/register"},method=RequestMethod.GET)
	public ModelAndView showRegister(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mv = new ModelAndView("register");
		return mv;
	}
	
	@RequestMapping(value={"login","seller/login"},method=RequestMethod.POST)
	public ModelAndView doLogin(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mv = new ModelAndView();
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		UserService us = new UserService();
		HttpSession session = request.getSession();
		if(us.loginUser(email, password, session)==0){
			if(session.getAttribute("sid")==null)return new ModelAndView("redirect:/index");
			return new ModelAndView("redirect:/seller/index");
		}
		else{
			Error e = new Error(us.message);
			mv.addObject("e",e);
			System.out.println(e);
			mv.setViewName("login");
		}
		return mv;
	}
	
	@RequestMapping(value={"register","seller/register"},method=RequestMethod.POST)
	public ModelAndView doRegister(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mv = new ModelAndView();
		User user = new User();
		Address address = new Address();
		Seller seller = new Seller();
		
		user.setTitle(request.getParameter("title"));
		user.setEmail(request.getParameter("email"));
		user.setFirstname(request.getParameter("firstname"));
		user.setLastname(request.getParameter("lastname"));
		user.setPassword(request.getParameter("password"));
		user.setDob(request.getParameter("dob"));
		address.setAddress1(request.getParameter("address.address1"));
		address.setAddress2(request.getParameter("address.address2"));
		address.setCity(request.getParameter("address.city"));
		address.setState(request.getParameter("address.state"));
		address.setCountry(request.getParameter("address.country"));
		address.setZip(request.getParameter("address.zip"));
		user.setAddress(address);
		user.setHomephone(request.getParameter("homephone"));
		user.setMobilephone(request.getParameter("mobilephone"));
		user.setAdditional_info(request.getParameter("additional_info"));
		user.setRole(request.getParameter("role"));
		seller.setCompany(request.getParameter("seller.company"));
		seller.setId_proof(request.getParameter("seller.id_proof"));
		seller.setImage_path(request.getParameter("seller.image_path"));
		user.setSeller(seller);
		UserService us = new UserService();
		HttpSession session = request.getSession();
		if(us.registerUser(user, session)==0){
			if(user.getRole().equals("false"))return new ModelAndView("redirect:/index");
			else mv.setViewName("seller_index");
		}
		else{
			Error e = new Error(us.message);
			mv.addObject("e",e);
			mv.setViewName("register");
		}
		return mv;
	}
	
	@RequestMapping(value={"logout","seller/logout"})
	public ModelAndView logout(HttpServletRequest request,HttpServletResponse response){
		UserService us = new UserService();
		HttpSession session = request.getSession();
		us.logout(session);
		return new ModelAndView("redirect:/login");
	}
}
