package com.shopfic.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shopfic.model.Address;
import com.shopfic.model.Cart;
import com.shopfic.model.Notification;
import com.shopfic.model.Product;
import com.shopfic.model.User;

public class CartDao extends MainDao {
	public boolean addCart(Cart c){
		String query_select = "SELECT * FROM cart WHERE uid=? AND pid=? AND request=0";
		String query_update = "UPDATE cart SET count=count+? WHERE uid=? AND pid=? AND request=0";
		String query_cart="INSERT INTO cart(uid,pid,count) VALUES(?,?,?)";
		PreparedStatement pst;
		ResultSet rs;
		try {
			//System.out.println(c);
			pst = conn.prepareStatement(query_select);
			pst.setInt(1, c.getUid());
			pst.setInt(2, c.getPid());
			rs = pst.executeQuery();
			if(rs.next()){
				pst = conn.prepareStatement(query_update);
				pst.setInt(2, c.getUid());
				pst.setInt(3, c.getPid());
				pst.setInt(1, c.getCount());
				pst.executeUpdate();
			}
			else{
				pst = conn.prepareStatement(query_cart);
				pst.setInt(1, c.getUid());
				pst.setInt(2, c.getPid());
				pst.setInt(3, c.getCount());
				pst.executeUpdate();
			}
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("In CartDao: addCart "+e);
		}
		return false;
	}
	public List<Cart> viewCartFresh(int uid){
		List<Cart> products = new ArrayList<Cart>();
		String query_cart = "SELECT * FROM cart WHERE uid=? AND request=0";
		Product p;
		Cart c;
		ProductDao pd = new ProductDao();
		pd.connect();
		try {
			PreparedStatement pst = conn.prepareStatement(query_cart);
			pst.setInt(1, uid);
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				c = new Cart();
				c.setPid(rs.getInt("pid"));
				c.setUid(rs.getInt("uid"));
				c.setCount(rs.getInt("count"));
				p = pd.getProduct(c.getPid());
				c.setName(p.getName());
				c.setPrice(p.getPrice());
				c.setDiscount(p.getDiscount());
				c.setCost();
				c.setRequest(rs.getInt("request"));
				c.setBrought(rs.getInt("brought"));
				c.setShort_description(p.getShort_description());
				c.setImage_path(p.getImage_path());
				products.add(c);
			}
			return products;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("In CartDao: viewCartFresh "+e);
		}
		return products;
	}
	public List<Cart> viewCartOld(int uid){
		List<Cart> products = new ArrayList<Cart>();
		String query_cart = "SELECT * FROM cart WHERE uid=? AND request=1";
		Product p;
		Cart c;
		ProductDao pd = new ProductDao();
		pd.connect();
		try {
			PreparedStatement pst = conn.prepareStatement(query_cart);
			pst.setInt(1, uid);
			ResultSet rs = pst.executeQuery();

			while(rs.next()){
				c = new Cart();
				c.setPid(rs.getInt("pid"));
				c.setUid(rs.getInt("uid"));
				c.setCount(rs.getInt("count"));
				c.setRequest(rs.getInt("request"));
				c.setBrought(rs.getInt("brought"));
				p = pd.getProduct(c.getPid());
				c.setName(p.getName());
				c.setPrice(p.getPrice());
				c.setDiscount(p.getDiscount());
				c.setCost();
				c.setShort_description(p.getShort_description());
				c.setImage_path(p.getImage_path());
				products.add(c);
			}
			return products;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("In CartDao: viewCartOld "+e);
		}
		return products;
	}
	public boolean deleteCartProduct(int pid,int uid){
		String query_cart="DELETE FROM cart WHERE pid=? AND uid=? AND request=0";
		try {
			PreparedStatement pst = conn.prepareStatement(query_cart);
			pst.setInt(1, pid);
			pst.setInt(2, uid);
			pst.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("In CartDao: deleteCartProduct "+e);
		}
		return false;
	}
	public boolean updateCartProduct(int uid,int pid,int count){
		String query_cart="UPDATE cart SET count=? WHERE pid=? AND uid=? AND request=0";
		try {
			PreparedStatement pst = conn.prepareStatement(query_cart);
			pst.setInt(1, count);
			pst.setInt(2, pid);
			pst.setInt(3, uid);
			pst.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("In CartDao: updateCartProduct "+e);
		}
		return false;
	}
	public boolean buyCartProduct(int uid,int pid,boolean cod){
		//update cart and also reduce stock and put date and transaction_od
		int count=0;
		String query_cart_stock = "SELECT count FROM cart WHERE pid=? AND uid=? AND request=0";
		String query_stock_get = "SELECT stock FROM product WHERE pid=?"; 
		String query_cart="UPDATE cart SET request=1, date_request=NOW(), cod=? WHERE pid=? AND uid=? AND request=0";
		String query_cart_tid="UPDATE cart SET request=1, date_request=NOW(), transaction_id=? WHERE pid=? AND uid=? AND request=0";
		String query_stock = "UPDATE product SET stock=stock-? WHERE pid=?";
		PreparedStatement pst;
		int stock;
		try {
			pst = conn.prepareStatement(query_cart_stock);
			pst.setInt(1, pid);
			pst.setInt(2, uid);
			ResultSet rs = pst.executeQuery();
			if(rs.next())count = rs.getInt("count");
			else return false;
			pst = conn.prepareStatement(query_stock_get);
			pst.setInt(1, pid);
			rs = pst.executeQuery();
			if(rs.next())stock=rs.getInt("stock");
			else return false;
			if(stock<count)return false;
			pst = conn.prepareStatement(query_stock);
			pst.setInt(1, count);
			pst.setInt(2, pid);
			pst.executeUpdate();
			if(cod){
				pst = conn.prepareStatement(query_cart);
				pst.setInt(1, 1);
			}
			else {
				pst = conn.prepareStatement(query_cart_tid);
				pst.setString(1,"RANDOM");
			}
			pst.setInt(2, pid);
			pst.setInt(3, uid);
			pst.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("In CartDao: updateCartProduct "+e);
		}
		return false;
	}
	public boolean buyCart(int uid,boolean cod){
		String query_cart="SELECT pid FROM cart WHERE request=0 AND uid=?";
		try {
			PreparedStatement pst = conn.prepareStatement(query_cart);
			pst.setInt(1, uid);
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				buyCartProduct(uid,rs.getInt("pid"),cod);
			}
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("In CartDao: updateCartProduct "+e);
		}
		return false;
	}
	//NOTIFICATION
	public List<Notification> getProductRequestFulfilled(int sid){
		//left join on cart and product
		List<Notification> list = new ArrayList<Notification>();
		String query = "SELECT * FROM ((SELECT cart.*,product.name,product.price,product.discount,product.stock,product.brand,product.version FROM cart INNER JOIN product ON cart.pid=product.pid WHERE sid=? AND request=1 AND mark=1 AND brought=1)AS cartproduct INNER JOIN user ON cartproduct.uid=user.uid)";
		try {
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setInt(1, sid);
			ResultSet rs = pst.executeQuery();
			Notification n;
			while(rs.next()){
				n = new Notification();
				n.setPid(rs.getInt("pid"));
				n.setName(rs.getString("name"));
				n.setDiscount(rs.getDouble("discount"));
				n.setPrice(rs.getDouble("price"));
				n.setStock(rs.getInt("stock"));
				n.setCost();
				n.setBrand(rs.getString("brand"));
				n.setVersion(rs.getString("version"));
				n.setUid(rs.getInt("uid"));
				n.setCod(rs.getInt("cod"));
				n.setTransaction_id(rs.getString("transaction_id"));
				n.setDate_request(rs.getString("date_request"));
				n.setCount(rs.getInt("count"));
				User user = new User();
				user.setFirstname(rs.getString("firstname"));
				user.setLastname(rs.getString("lastname"));
				user.setHomephone(rs.getString("homephone"));
				Address address = new Address();
				address.setAddress1(rs.getString("address1"));
				address.setAddress2(rs.getString("address2"));
				address.setCity(rs.getString("city"));
				address.setCountry(rs.getString("country"));
				address.setState(rs.getString("state"));
				address.setZip(rs.getString("zip"));
				user.setAddress(address);
				n.setUser(user);
				list.add(n);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("In CartDao: getProductRequestFulfilled "+e);
		}
		return list;
	}
	public List<Notification> getProductRequest(int sid){
		//left join on cart and product
				List<Notification> list = new ArrayList<Notification>();
				String query = "SELECT * FROM ((SELECT cart.*,product.name,product.price,product.discount,product.stock,product.brand,product.version FROM cart INNER JOIN product ON cart.pid=product.pid WHERE sid=? AND request=1 AND mark=0 AND brought=0)AS cartproduct INNER JOIN user ON cartproduct.uid=user.uid)";
				try {
					PreparedStatement pst = conn.prepareStatement(query);
					pst.setInt(1, sid);
					ResultSet rs = pst.executeQuery();
					Notification n;
					while(rs.next()){
						n = new Notification();
						n.setPid(rs.getInt("pid"));
						n.setName(rs.getString("name"));
						n.setDiscount(rs.getDouble("discount"));
						n.setPrice(rs.getDouble("price"));
						n.setCost();
						n.setStock(rs.getInt("stock"));
						n.setBrand(rs.getString("brand"));
						n.setVersion(rs.getString("version"));
						n.setUid(rs.getInt("uid"));
						n.setCod(rs.getInt("cod"));
						n.setTransaction_id(rs.getString("transaction_id"));
						n.setDate_request(rs.getString("date_request"));
						n.setCount(rs.getInt("count"));
						User user = new User();
						user.setFirstname(rs.getString("firstname"));
						user.setLastname(rs.getString("lastname"));
						user.setHomephone(rs.getString("homephone"));
						Address address = new Address();
						address.setAddress1(rs.getString("address1"));
						address.setAddress2(rs.getString("address2"));
						address.setCity(rs.getString("city"));
						address.setCountry(rs.getString("country"));
						address.setState(rs.getString("state"));
						address.setZip(rs.getString("zip"));
						user.setAddress(address);
						n.setUser(user);
						list.add(n);
					}
				} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("In CartDao: getProductRequest "+e);
		}
		
		return list;
	}
	public List<Notification> getProductRequestPending(int sid){
		//left join on cart and product
				List<Notification> list = new ArrayList<Notification>();
				String query = "SELECT * FROM ((SELECT cart.*,product.name,product.price,product.discount,product.stock,product.brand,product.version FROM cart INNER JOIN product ON cart.pid=product.pid WHERE sid=? AND request=1 AND mark=1 AND brought=0)AS cartproduct INNER JOIN user ON cartproduct.uid=user.uid)";
				try {
					PreparedStatement pst = conn.prepareStatement(query);
					pst.setInt(1, sid);
					ResultSet rs = pst.executeQuery();
					Notification n;
					while(rs.next()){
						n = new Notification();
						n.setPid(rs.getInt("pid"));
						n.setName(rs.getString("name"));
						n.setDiscount(rs.getDouble("discount"));
						n.setPrice(rs.getDouble("price"));
						n.setCost();
						n.setStock(rs.getInt("stock"));
						n.setBrand(rs.getString("brand"));
						n.setVersion(rs.getString("version"));
						n.setUid(rs.getInt("uid"));
						n.setCod(rs.getInt("cod"));
						n.setTransaction_id(rs.getString("transaction_id"));
						n.setDate_request(rs.getString("date_request"));
						n.setCount(rs.getInt("count"));
						User user = new User();
						user.setFirstname(rs.getString("firstname"));
						user.setLastname(rs.getString("lastname"));
						user.setHomephone(rs.getString("homephone"));
						Address address = new Address();
						address.setAddress1(rs.getString("address1"));
						address.setAddress2(rs.getString("address2"));
						address.setCity(rs.getString("city"));
						address.setCountry(rs.getString("country"));
						address.setState(rs.getString("state"));
						address.setZip(rs.getString("zip"));
						user.setAddress(address);
						n.setUser(user);
						list.add(n);
					}
				}catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("In CartDao: getProductRequestPending "+e);
		}
		
		return list;
	}
	public List<Notification> getOutOfStock(int sid){
		//left join on cart and product
				List<Notification> list = new ArrayList<Notification>();
				String query = "SELECT * FROM product WHERE sid=? AND stock=0";
				try {
					PreparedStatement pst = conn.prepareStatement(query);
					pst.setInt(1, sid);
					ResultSet rs = pst.executeQuery();
					Notification n;
					while(rs.next()){
						n = new Notification();
						n.setPid(rs.getInt("pid"));
						n.setName(rs.getString("name"));
						n.setDiscount(rs.getDouble("discount"));
						n.setPrice(rs.getDouble("price"));
						n.setCost();
						n.setStock(rs.getInt("stock"));
						n.setBrand(rs.getString("brand"));
						n.setVersion(rs.getString("version"));
						list.add(n);
					}
				} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("In CartDao: getOutOfStock "+e);
		}	
		return list;
	}
	public boolean mark(int sid){
		String query = "UPDATE cart SET mark=1 WHERE pid IN (SELECT pid FROM product WHERE sid=?) AND request=1 AND mark=0 AND brought=0";
		try {
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setInt(1, sid);
			pst.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("In CartDao: mark "+e);
		}
		return false;
	}
}
