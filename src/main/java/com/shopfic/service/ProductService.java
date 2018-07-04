package com.shopfic.service;

import java.util.List;
import java.util.Map;

import com.shopfic.dao.ProductDao;
import com.shopfic.model.Product;
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
	public List<Product> productSeller(int sid){
		List<Product> list = null;
		ProductDao pd = new ProductDao();
		pd.connect();
		list = pd.getProductsSid(sid);
		return list;
	}
	public List<Product> productsCategory(String category){
		//gives list of products on basis of category specified
		List<Product> list = null;
		ProductDao pd = new ProductDao();
		pd.connect();
		list = pd.getProductsCategory(category);
		return list;
	}
	public List<Product> productsSubcategory(String category,String subcategory){
		//gives list of products on basis of subcategory+category specified
		List<Product> list = null;
		ProductDao pd = new ProductDao();
		pd.connect();
		list = pd.getProductsSubcategory(category,subcategory);
		return list;
	}
	public List<Product> productsSearch(String pattern){
		//gives list of product on search basis
		List<Product> list = null;
		ProductDao pd = new ProductDao();
		pd.connect();
		list = pd.getProductsSearch(pattern);
		return list;
	}
	public ProductDetailed getProductDetailed(int pid){
		//gives detailed info of one product
		ProductDetailed prod;
		ProductDao pd = new ProductDao();
		pd.connect();
		prod = pd.getProductDetailed(pid);
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
