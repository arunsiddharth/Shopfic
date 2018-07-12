package com.shopfic.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.shopfic.model.Notification;
import com.shopfic.model.Product;
import com.shopfic.model.ProductDetailed;
import com.shopfic.service.CartService;
import com.shopfic.service.ProductService;

@Controller
@RequestMapping("seller")
public class Seller {
	private static String UPLOADED_FOLDER = "C:\\Users\\aruna\\EclipseMarsWorkspace\\Eagle\\src\\main\\webapp\\resources\\maintheme\\themes\\images\\products\\";

	
	@RequestMapping("index")
	public ModelAndView sellerIndex(HttpServletRequest request, HttpServletResponse response){
		HttpSession session= request.getSession();
		if(session.getAttribute("sid")==null)return new ModelAndView("redirect:/index");
		ModelAndView mv = new ModelAndView("seller_index");
		ProductService ps = new ProductService();
		int sid = Integer.parseInt(session.getAttribute("sid").toString());//chance of error
		List<Product> list = ps.productSeller(sid);
		mv.addObject("products", list);
		return mv;
	}
	@RequestMapping(value="add", method=RequestMethod.GET)
	public ModelAndView addProduct(HttpServletRequest request, HttpServletResponse response){
		HttpSession session= request.getSession();
		//if(session.getAttribute("sid")==null)return new ModelAndView("redirect:/index");
		ModelAndView mv = new ModelAndView("addproduct");
		ProductService ps = new ProductService();
		Map<String,Map<String,Integer> > hm = ps.productList();
		mv.addObject("list",hm);
		return mv;
	}
	
	@RequestMapping(value="add", method=RequestMethod.POST)
	public ModelAndView doAddProduct(@RequestParam("images") MultipartFile[] files, HttpServletRequest request, HttpServletResponse response){
		int sid = Integer.parseInt(request.getSession().getAttribute("sid").toString());
		System.out.println("SID IS"+sid);
		ProductService ps=new ProductService();
		ProductDetailed prod = new ProductDetailed();
		prod.setName(request.getParameter("name"));
		prod.setPrice(Double.parseDouble(request.getParameter("price")));
		prod.setDiscount(Double.parseDouble(request.getParameter("discount")));
		prod.setStock(Integer.parseInt(request.getParameter("stock")));
		prod.setCategory(request.getParameter("category"));
		prod.setSubcategory(request.getParameter("subcategory"));
		prod.setBrand(request.getParameter("brand"));
		prod.setVersion(request.getParameter("version"));
		prod.setShort_description(request.getParameter("short_description"));
		prod.setFeatures(request.getParameter("features"));
		prod.setSid(sid);
		int tpid = ps.addProduct(prod);
		
		//prod.setImage_path(request.getParameter("images"));
		List<String> images = new ArrayList<String>();
		// Get the file and save it somewhere
        int c=1;
        for (MultipartFile file : files) {
            if (file.isEmpty()) {
                continue;
            }
            try {
                byte[] bytes = file.getBytes();
                Path path = Paths.get(UPLOADED_FOLDER + tpid+"-"+c+"."+file.getContentType().substring(6));
                Files.write(path, bytes);
                images.add(tpid+"-"+c+"."+file.getContentType().substring(6));
                //System.out.println(tpid+"-"+c+"."+file.getContentType().substring(6));
                c+=1;
                if(c==5)break;
            } catch (IOException e) {
                System.out.println("Image I/O Exception"+e);
            }
        }
        //if(images.isEmpty())images.add("tmp.png");
		prod.setImages(images);
		ps.addImage(tpid, images);
		return new ModelAndView("redirect:/seller/index");
	}
	@RequestMapping(value="delete", method=RequestMethod.GET)
	public ModelAndView deleteProduct(HttpServletRequest request, HttpServletResponse response){
		HttpSession session= request.getSession();
		if(session.getAttribute("sid")==null)return new ModelAndView("redirect:/index");
		ModelAndView mv = new ModelAndView();
		int pid = Integer.parseInt(request.getParameter("pid"));
		ProductService ps = new ProductService();
		ps.deleteProduct(pid);
		return new ModelAndView("redirect:/seller/index");
	}
	@RequestMapping(value="update", method=RequestMethod.GET)
	public ModelAndView updateProduct(HttpServletRequest request, HttpServletResponse response){
		HttpSession session= request.getSession();
		if(session.getAttribute("sid")==null)return new ModelAndView("redirect:/index");
		int pid = Integer.parseInt(request.getParameter("pid"));
		ModelAndView mv = new ModelAndView("updateproduct");
		ProductDetailed prod = new ProductDetailed();
		ProductService ps = new ProductService();
		prod = ps.getProductDetailed(pid);
		mv.addObject("product",prod);
		Map<String,Map<String,Integer> > hm = ps.productList();
		mv.addObject("list",hm);
		return mv;
	}
	@RequestMapping(value="update", method=RequestMethod.POST)
	public ModelAndView doUpdateProduct(HttpServletRequest request, HttpServletResponse response){
		HttpSession session= request.getSession();
		if(session.getAttribute("sid")==null)return new ModelAndView("redirect:/index");
		//add sid
		int pid = Integer.parseInt(request.getParameter("pid"));
		ProductDetailed product = new ProductDetailed();
		ProductService ps = new ProductService();
		product = ps.getProductDetailed(pid);
		if(!request.getParameter("name").equals(product.getName()))product.setName(request.getParameter("name"));
		if(!request.getParameter("price").equals(product.getPrice()))product.setPrice(Double.parseDouble(request.getParameter("price")));
		if(!request.getParameter("discount").equals(product.getDiscount()))product.setDiscount(Double.parseDouble(request.getParameter("discount")));
		if(!request.getParameter("features").equals(product.getFeatures()))product.setFeatures(request.getParameter("features"));
		if(!request.getParameter("stock").equals(product.getStock()))product.setStock(Integer.parseInt(request.getParameter("stock")));
		if(!request.getParameter("category").equals(product.getCategory()))product.setCategory(request.getParameter("category"));
		if(!request.getParameter("subcategory").equals(product.getSubcategory()))product.setSubcategory(request.getParameter("subcategory"));
		if(!request.getParameter("brand").equals(product.getBrand()))product.setBrand(request.getParameter("brand"));
		if(!request.getParameter("version").equals(product.getVersion()))product.setVersion(request.getParameter("version"));
		if(!request.getParameter("short_description").equals(product.getShort_description()))product.setShort_description(request.getParameter("short_description"));
		product.setSid(Integer.parseInt(session.getAttribute("sid").toString()));
		ps.updateProduct(product);
		return new ModelAndView("redirect:/seller/index");
	}
	
	@RequestMapping(value="notification",method=RequestMethod.GET)
	public ModelAndView notifications(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mv = new ModelAndView("notification");
		CartService cs = new CartService();
		String view_requested = request.getParameter("view");
		List<Notification> n;
		HttpSession session = request.getSession();
		mv.addObject("oos","false");
		int sid = Integer.parseInt(session.getAttribute("sid").toString());
		if(view_requested.equals("prf")){
			mv.addObject("oos","false");
			n=cs.notificationProductRequestFulfilled(sid);
			mv.addObject("marker", "false");
		}
		else if(view_requested.equals("pr")){
			mv.addObject("oos","false");
			n=cs.notificationProductRequest(sid);
			mv.addObject("marker", "true");
		}
		else if(view_requested.equals("prp")){
			mv.addObject("oos","false");
			n=cs.notificationProductRequestPending(sid);
			mv.addObject("marker", "false");
		}
		else {
			mv.addObject("oos","true");
			n=cs.notificationOutOfStock(sid);
			mv.addObject("marker", "false");
		}
		mv.addObject("notification",n);
		return mv;
	}
	@RequestMapping(value="productsmark",method=RequestMethod.GET)
	public ModelAndView mark(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		int sid = Integer.parseInt(session.getAttribute("sid").toString());
		CartService cs = new CartService();
		cs.mark(sid);
		return new ModelAndView("redirect:/seller/notification?view=prp");
	}
}
