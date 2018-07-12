package com.shopfic.dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.shopfic.model.User;


public class UserDao extends MainDao{
	public Map<String,String> hm = new HashMap<String,String>();
	
	public int login(String email,String password){
		String query = "SELECT uid,firstname,password,role FROM user WHERE email=?";
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(query);
			pst.setString(1, email);
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				if(rs.getString("password").equals(password)){
					Integer role = rs.getInt(4);
					hm.put("uid", rs.getString(1));
					hm.put("firstname", rs.getString(2));
					hm.put("role", role.toString());
					return 0;
				}
				else{
					hm.put("message", "Wrong Password");
					return 2;
				}
			}
			else{
				hm.put("message", "Wrong E mail");
				return 1;
			}
		} catch (SQLException e) {
			System.out.println("In UserDao: "+e);
			hm.put("message", "Database Failure");
			return 3;
		}	
	}
	public void setSellerImage(int sid,String image_path){
		String query_seller = "UPDATE seller SET image_path=? WHERE uid=?";
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(query_seller);
			pst.setString(1, image_path);
			pst.setInt(2, sid);
			pst.executeUpdate();
		} catch (SQLException e) {
			System.out.println("In UserDao: setSellerImage"+e);
		}
	}
	
	public int register(User user){
		String query = "INSERT INTO user(title,firstname,lastname,email,password,dob,address1,address2,city,state,country,zip,homephone,mobilephone,additional_info,role) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		String query_id = "SELECT uid,firstname FROM user WHERE email=?";
		String query_seller = "INSERT INTO seller(uid,company,id_proof) VALUES(?,?,?)";
		
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(query);
			pst.setString(1, user.getTitle());
			pst.setString(2, user.getFirstname());
			pst.setString(3, user.getLastname());
			pst.setString(4, user.getEmail());
			pst.setString(5, user.getPassword());
			pst.setString(6, user.getDob());
			pst.setString(7, user.getAddress().getAddress1());
			pst.setString(8, user.getAddress().getAddress2());
			pst.setString(9, user.getAddress().getCity());
			pst.setString(10, user.getAddress().getState());
			pst.setString(11, user.getAddress().getCountry());
			pst.setString(12, user.getAddress().getZip());
			pst.setString(13, user.getHomephone());
			pst.setString(14, user.getMobilephone());
			pst.setString(15, user.getAdditional_info());
			if(user.getRole().equals("true"))pst.setInt(16, 1);
			else pst.setInt(16, 0);
			PreparedStatement pst2 = conn.prepareStatement(query_id);
			pst2.setString(1,user.getEmail());
			ResultSet rs = pst2.executeQuery();
			if(rs.next()==false){
				//int count = 
				pst.executeUpdate();
				pst2 = conn.prepareStatement(query_id);
				pst2.setString(1,user.getEmail());
				rs = pst2.executeQuery();
				rs.next();
				hm.put("uid", rs.getString("uid"));
				hm.put("firstname", rs.getString("firstname"));
				if(user.getRole().equals("true")){
					//update seller table
					hm.put("sid", rs.getString("uid"));
					System.out.println("trying to make him seller");
					PreparedStatement pst3 = conn.prepareStatement(query_seller);
					pst3.setInt(1, Integer.parseInt(rs.getString(1)));
					pst3.setString(2, user.getSeller().getCompany());
					pst3.setString(3, user.getSeller().getId_proof());
					//pst3.setString(4, user.getSeller().getImage_path());
					int count2 = pst3.executeUpdate();
					if(count2==1){
						return 0;
					}
					else{
						hm.put("message", "vendor registration failed");
						//TODO: delete from user ,clear session 
						return 1;
					}
				}
				return 0;
			}
			else{
				hm.put("message", "Email/mobilephone already exist");
				return 1;
			}
		} catch (SQLException e) {
			System.out.println("In userDao: "+e);
			hm.put("message", "Database Failure");
			return 3;
		}	
	}
}
