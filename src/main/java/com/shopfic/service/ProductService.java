package com.shopfic.service;

import java.util.Map;

import com.shopfic.dao.ProductDao;
import com.shopfic.model.ProductDetailed;

public class ProductService {
	
	public Map<String,Map<String,Integer> > productList(){
		//give list of categories and subcategories
		Map<String,Map<String,Integer> > hm=null;
		ProductDao pd= new ProductDao();
		pd.connect();
		hm = pd.getList();
		return hm;
	}
	public void products_category(){
		//gives list of products on basis of category specified
	}
	public void products_subcategory(){
		//gives list of products on basis of subcategory+category specified
	}
	public void products_search(){
		//gives list of product on search basis
	}
	public ProductDetailed getproduct(int pid){
		//gives detailed info of one product
		ProductDetailed prod;
		ProductDao pd = new ProductDao();
		pd.connect();
		prod = pd.getProduct(pid);
		return prod;
	}
	
	public void addProduct(ProductDetailed p){
		ProductDao pd = new ProductDao();
		pd.connect();
		if(pd.addProduct(p))System.out.println("Added successfully");//return true on success addition
		else System.out.println("Product addition failure");
	}
	public void updateProduct(ProductDetailed prod){
		//TODO 
		ProductDao pd = new ProductDao();
		pd.connect();
		pd.updateProduct(prod);
	}
	public void deleteProduct(int pid){
		ProductDao pd = new ProductDao();
		pd.connect();
		pd.deleteProduct(pid);
	}
}
