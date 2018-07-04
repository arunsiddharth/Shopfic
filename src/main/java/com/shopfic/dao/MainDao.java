package com.shopfic.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class MainDao {
	private String url = "jdbc:mysql://localhost:3307/shopfic";
	private String user="root";
	private String password="";
	public Connection conn;
	public void connect(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url,user,password);	
		} catch (Exception e) {
			System.out.print("In MainDao : "+e);
		}
	}
}
