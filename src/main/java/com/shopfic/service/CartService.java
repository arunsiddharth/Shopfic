package com.shopfic.service;

import java.util.List;

import com.shopfic.dao.CartDao;
import com.shopfic.model.Cart;

public class CartService {
	public void addCart(Cart c){
		CartDao cd = new CartDao();
		cd.connect();
		//System.out.println("cart in service is "+c.getPid()+", "+c.getUid()+", "+c.getCount());
		cd.addCart(c);
	}
	public List<Cart> viewCartFresh(int uid){
		List<Cart> products;
		CartDao cd = new CartDao();
		cd.connect();
		products = cd.viewCartFresh(uid);
		return products;
	}
	public List<Cart> viewCartOld(int uid){
		List<Cart> products;
		CartDao cd = new CartDao();
		cd.connect();
		products = cd.viewCartOld(uid);
		return products;
	}
	public void deleteCartProduct(int pid,int uid){
		CartDao cd = new CartDao();
		cd.connect();
		cd.deleteCartProduct(pid, uid);
	}
	public void updateCartProduct(int uid,int pid,int count){
		CartDao cd = new CartDao();
		cd.connect();
		cd.updateCartProduct(uid, pid, count);
	}
	public void buyCart(int uid,boolean cod){
		CartDao cd = new CartDao();
		cd.connect();
		cd.buyCart(uid, cod);
	}
}
