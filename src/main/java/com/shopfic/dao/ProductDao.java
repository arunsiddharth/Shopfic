package com.shopfic.dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.shopfic.model.Comment;
import com.shopfic.model.Product;
import com.shopfic.model.ProductDetailed;
import com.shopfic.model.Rating;

public class ProductDao extends MainDao{
	@Deprecated
	public int pidMaker(){
		String query = "SELECT pid FROM product order by pid DESC LIMIT 1";
		Statement st;
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			if(rs.next())return rs.getInt("pid");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("In Product Dao : pidMaker"+e);
		}
		return 0;
	}
	
	public Map<String,Map<String,Integer> > getList(){
		//Returns list of categories and subcategories
		String query_category = "SELECT * FROM category";
		String query_subcategory = "SELECT * FROM subcategory WHERE cid=(?)";
		Map<String, Map<String,Integer> > result = new HashMap<String, Map<String,Integer> >();
		Statement st;
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(query_category);
			while(rs.next()){
				Map<String,Integer> sub_result = new HashMap<String,Integer>();//map for subcategory
				PreparedStatement pst = conn.prepareStatement(query_subcategory);
				sub_result.put("cid", rs.getInt("cid"));
				sub_result.put("count", 0);
				pst.setInt(1, rs.getInt("cid"));
				ResultSet rs2 = pst.executeQuery();
				while(rs2.next()){
					sub_result.put(rs2.getString("name"), rs2.getInt("count"));
					sub_result.replace("count", sub_result.get("count")+rs2.getInt("count"));
				}
				result.put(rs.getString("name"), sub_result);
			}
			//st.close();
			//conn.close();
			return result;
		} catch (SQLException e) {
			System.out.println("In Product Dao : getList : "+e);
		}
		return null;
	}
	public boolean updateProduct(ProductDetailed prod){
		//MUST GIVE ORIGINAL + MODIFICATION;
		int pid = prod.getPid();
		int newpid =addProduct(prod);
		updateImage(prod.getImages(),newpid);
		updateCarts(pid,newpid);
		deleteProduct(pid);
		return true;
	}
	public void updateCarts(int oldPid,int newPid){
		String query = "UPDATE cart SET pid=? WHERE pid=?";
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(query);
			pst.setInt(1, newPid);
			pst.setInt(2, oldPid);
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("In UpdateCart : "+e);
		}	
	}
	public ProductDetailed getProductDetailed(int pid){
		ProductDetailed pd = new ProductDetailed();
		String query = "SELECT * FROM product WHERE pid=?";
		String query_category = "SELECT * FROM category WHERE cid=?";
		String query_subcategory = "SELECT * FROM subcategory WHERE csid=?";
		String query_images = "SELECT * FROM images WHERE pid=?";
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(query);
			pst.setInt(1, pid);
			ResultSet rs = pst.executeQuery();
			rs.next();
			pd.setPid(pid);
			pd.setName(rs.getString("name"));
			pd.setPrice(rs.getDouble("price"));
			pd.setCsid(rs.getInt("csid"));
			pd.setStock(rs.getInt("Stock"));
			pd.setSid(rs.getInt("sid"));
			pd.setShort_description(rs.getString("short_description"));
			pd.setVersion(rs.getString("version"));
			pd.setBrand(rs.getString("brand"));
			pd.setDiscount(rs.getDouble("discount"));
			pd.setFeatures(rs.getString("features"));
			pd.setCost();
			//pst.close();
			pst = conn.prepareStatement(query_subcategory);
			pst.setInt(1, pd.getCsid());
			rs = pst.executeQuery();
			rs.next();
			pd.setSubcategory(rs.getString("name"));
			//pst.close();
			pst = conn.prepareStatement(query_category);
			pst.setInt(1, rs.getInt("cid"));
			rs = pst.executeQuery();
			rs.next();
			pd.setCategory(rs.getString("name"));
			//pst.close();
			pst = conn.prepareStatement(query_images);
			pst.setInt(1, pid);
			rs = pst.executeQuery();
			List<String> list = new ArrayList<String>();
			while(rs.next()){
				list.add(rs.getString("path"));
				pd.setImage_path(rs.getString("path"));
			}
			pd.setImages(list);
			return pd;
		} catch (SQLException e) {
			System.out.println("In Product Dao : getProductDetailed : "+e);
		}
		return null;
	}
	public boolean updateImage(List<String> images,int pid){
		String query_images = "INSERT INTO images(pid,path) VALUES(?,?)";
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(query_images);
			pst.setInt(1, pid);
			for(String img:images){
				pst.setString(2, img);
				pst.executeUpdate();
			}
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("In Product Dao:updateImages"+e);
		}
		return false;
	}
	public int addProduct(ProductDetailed p){
		//insert into product and images + update subcategory
		//get csid
		int cid,csid,pid;
		String query_cid = "SELECT cid FROM category WHERE name=?";
		String query_csid = "SELECT csid FROM subcategory WHERE cid=? AND name=?";
		String query_product = "INSERT INTO product(name,price,discount,features,stock,csid,brand,version,sid,short_description) VALUES(?,?,?,?,?,?,?,?,?,?)";
		String query_pid = "SELECT pid FROM product ORDER BY pid DESC LIMIT 1";
		//String query_images = "INSERT INTO images(pid,path) VALUES(?,?)";
		String query_subcategory= "UPDATE subcategory SET count=count+1 WHERE name=?";
		try{

			Statement st = conn.createStatement();
			PreparedStatement pst = conn.prepareStatement(query_cid);
			pst.setString(1, p.getCategory());
			ResultSet rs = pst.executeQuery();
			rs.next();
			cid = rs.getInt("cid");
			pst.close();
			
			pst = conn.prepareStatement(query_csid);
			pst.setInt(1, cid);
			pst.setString(2, p.getSubcategory());
			System.out.println(cid+" "+p.getSubcategory());
			rs = pst.executeQuery();
			rs.next();
			csid = rs.getInt("csid");
			pst.close();
			
			pst = conn.prepareStatement(query_product);
			pst.setString(1, p.getName());
			pst.setDouble(2, p.getPrice());
			pst.setDouble(3, p.getDiscount());
			pst.setString(4, p.getFeatures());
			pst.setInt(5, p.getStock());
			pst.setInt(6, csid);
			pst.setString(7, p.getBrand());
			pst.setString(8, p.getVersion());
			pst.setInt(9, p.getSid());
			pst.setString(10, p.getShort_description());
			int count = pst.executeUpdate();
			//pst.close();

			rs = st.executeQuery(query_pid);
			rs.next();
			pid = rs.getInt(1);
			/*pst = conn.prepareStatement(query_images);
			pst.setInt(1, pid);
			for(String s:p.getImages()){
				pst.setString(2, s);
				pst.executeUpdate();
			}
			pst.close();*/
			pst = conn.prepareStatement(query_subcategory);
			pst.setString(1, p.getSubcategory());
			pst.executeUpdate();
			//pst.close();
			//conn.close();
			return pid;
		}catch (SQLException e) {
			System.out.println("In Product Dao : addProduct : "+e);
		}
		return 0;
	}
	
	public boolean deleteProduct(int pid){
		int csid;
		String query = "SELECT csid FROM product WHERE pid=?";
		String query_del = "DELETE FROM product WHERE pid=?";
		String query_subcategory = "UPDATE subcategory SET COUNT=COUNT-1 WHERE csid=?";
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(query);
			pst.setInt(1, pid);
			ResultSet rs = pst.executeQuery();
			System.out.println("1");
			rs.next();
			csid = rs.getInt("csid");
			pst = conn.prepareStatement(query_del);
			pst.setInt(1, pid);
			pst.executeUpdate();
			System.out.println("2");
			pst = conn.prepareStatement(query_subcategory);
			pst.setInt(1, csid);
			pst.executeUpdate(query_subcategory);
			System.out.println("3");
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("In Product Dao : deleteProduct : "+e);
		}
		return false;
	}
	public Product getProduct(int pid){
		Product prod = new Product();
		String query_product = "SELECT * FROM product WHERE pid=?";
		String query_image = "SELECT * FROM images WHERE pid=?";
		try {
			PreparedStatement pst = conn.prepareStatement(query_product);
			pst.setInt(1, pid);
			ResultSet rs = pst.executeQuery();
			rs.next();
			prod.setName(rs.getString("name"));
			prod.setPid(rs.getInt("pid"));
			prod.setPrice(rs.getDouble("price"));
			prod.setDiscount(rs.getDouble("discount"));
			prod.setStock(rs.getInt("stock"));
			prod.setCost();
			prod.setShort_description(rs.getString("short_description"));
			//pst.close();
			pst = conn.prepareStatement(query_image);
			pst.setInt(1, pid);
			rs = pst.executeQuery();
			List<String> images = new ArrayList<String>();
			
			while(rs.next()){
				images.add(rs.getString("path"));
				prod.setImage_path(rs.getString("path"));
			}
			
			//st.executeUpdate(query_subcategory);
			//st.close();
			return prod;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("In Product Dao : getProduct : "+e);
		}
		return null;
	}
	public List<Product> getProductsSid(int sid){
		List<Product> list = new ArrayList<Product>();
		String query = "SELECT pid FROM product WHERE sid=?";
		try {
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setInt(1, sid);
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				list.add(getProduct(rs.getInt("pid")));
			}
			//pst.close();
			//conn.close();
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("In Product Dao : getProductsSid : "+e);
		}
		return list;
	}
	public List<Product> getProductsLatest(){
		List<Product> list = new ArrayList<Product>();
		String query_pid = "SELECT pid FROM product ORDER BY pid desc LIMIT 10";
		try {
			PreparedStatement pst = conn.prepareStatement(query_pid);
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				list.add(getProduct(rs.getInt("pid")));
			}
			//pst.close();
			//conn.close();
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("In Product Dao : getProductsLatest : "+e);
		}
		return null;
	}
	public List<Product> getProductsCategory(String category){
		List<Product> list = new ArrayList<Product>();
		int cid,csid;
		String query_cid = "SELECT * FROM category WHERE name=?";
		String query_csid = "SELECT * FROM subcategory WHERE cid=?";
		String query_pid = "SELECT pid FROM product WHERE csid=?";
		try {
			PreparedStatement pst = conn.prepareStatement(query_cid);
			PreparedStatement pst2 = conn.prepareStatement(query_pid);
			pst.setString(1, category);
			ResultSet rs = pst.executeQuery();
			
			ResultSet rs2;
			rs.next();
			cid = rs.getInt("cid");
			System.out.println(cid);
			pst.close();
			pst = conn.prepareStatement(query_csid);
			pst.setInt(1, cid);
			rs = pst.executeQuery();
			while(rs.next()){
				csid = rs.getInt("csid");
				System.out.println("csid "+csid);
				pst2 = conn.prepareStatement(query_pid);
				pst2.setInt(1, csid);
				rs2 = pst2.executeQuery();
				while(rs2.next()){
					System.out.println("pid "+rs2.getInt("pid"));
					list.add(getProduct(rs2.getInt("pid")));
				}
			}
			//pst.close();
			//conn.close();
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("In Product Dao : getProductsCategory : "+e);
		}
		return list;
	}
	public List<Product> getProductsSubcategory(String category,String subcategory){
		List<Product> list = new ArrayList<Product>();
		int cid,csid;
		String query_cid = "SELECT cid FROM category WHERE name=?";
		String query_csid = "SELECT csid FROM subcategory WHERE cid=? AND name=?";
		String query_pid = "SELECT pid FROM product WHERE csid=?";
		try {
			PreparedStatement pst = conn.prepareStatement(query_cid);
			pst.setString(1, category);
			ResultSet rs = pst.executeQuery();
			rs.next();
			cid = rs.getInt("cid");
			pst.close();
			pst = conn.prepareStatement(query_csid);
			pst.setInt(1, cid);
			pst.setString(2, subcategory);
			rs = pst.executeQuery();
			rs.next();
			csid = rs.getInt("csid");
			pst.close();
			pst = conn.prepareStatement(query_pid);
			pst.setInt(1, csid);
			rs = pst.executeQuery();
			while(rs.next()){
				list.add(getProduct(rs.getInt("pid")));
			}
			//pst.close();
			//conn.close();
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("In Product Dao : getProductSubcategory : "+e);
		}
		return null;

	}
	public List<Product> getProductsSearch(String pattern){
		List<Product> list = new ArrayList<Product>();
		String query = "SELECT pid FROM product WHERE name LIKE ?";
		try {
			PreparedStatement pst = conn.prepareStatement(query);
			pattern+="%";
			pst.setString(1, pattern);
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				list.add(getProduct(rs.getInt("pid")));
			}
			//pst.close();
			//conn.close();
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("In Product Dao : getProductsSearch : "+e);
		}
		return null;
	}
	public Rating getProductRating(int pid){
		Rating ratings = new Rating();
		List<Comment> comments = new ArrayList<Comment>();
		double rating = 0;
		int count=0;
		ratings.setPid(pid);
		String query_rating = "SELECT * FROM rating WHERE pid=?";
		String query_name = "SELECT firstname,lastname FROM user WHERE uid=?";
		try {
			PreparedStatement pst = conn.prepareStatement(query_rating);
			PreparedStatement pst2 = conn.prepareStatement(query_name);
			pst.setInt(1, pid);
			ResultSet rs = pst.executeQuery();
			ResultSet rs2;
			while(rs.next()){
				Comment c = new Comment();
				c.setDate(rs.getString("date"));
				c.setComment(rs.getString("comment"));
				c.setUid(rs.getInt("uid"));
				pst2.setInt(1, c.getUid());
				rs2 = pst2.executeQuery();
				if(rs2.next())c.setName(rs2.getString("firstname")+" "+rs2.getString("lastname"));
				c.setRating(rs.getInt("rating"));
				rating+=c.getRating();
				count++;
				comments.add(c);
			}
			if(count>0)rating = rating/count;
			ratings.setRating(rating);
			ratings.setComments(comments);
			return ratings;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("In Product Dao : getProductsRating : "+e);
		}
		return ratings;
	}
	public boolean addRating(Comment c){
		String query_rating = "INSERT INTO rating(pid,uid,rating,comment) VALUES(?,?,?,?)";
		try {
			PreparedStatement pst = conn.prepareStatement(query_rating);
			pst.setInt(1, c.getPid());
			pst.setInt(2, c.getUid());
			pst.setDouble(3, c.getRating());
			pst.setString(4, c.getComment());
			pst.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("In Product Dao : addRating : "+e);
		}
		return false;
	}
}
