package com.shopfic.service;

import javax.servlet.http.HttpSession;

import com.shopfic.dao.UserDao;
import com.shopfic.model.User;

public class UserService {
	public String message;
	UserDao ud = new UserDao();
	
	public int loginUser(String email,String password,HttpSession session){
		//try to login user
		//if done then set session and return true
		//else return false
		//return 0->success 1->wrong email 2->wrong password
			ud.connect();
			int result = ud.login(email, password);
			if(result==0){
				//put in session
				if(Integer.parseInt(ud.hm.get("role"))==0)session.setAttribute("uid", ud.hm.get("uid"));
				else session.setAttribute("sid", ud.hm.get("uid"));
				session.setAttribute("firstname", ud.hm.get("firstname"));
				return 0;
			}
			else if(result==1){
				message = ud.hm.get("message");
				return 1;
			}
			else if(result==2){
				message = ud.hm.get("message");
				return 2;
			}
			else{
				message = ud.hm.get("message");
				return 3;
			}
	}
	public void setSellerImage(int sid,String ip){
		ud.connect();
		ud.setSellerImage(sid, ip);
	}
	public int registerUser(User user,HttpSession session){
		//try to register user
		//if done then set session and return true
		//else return false
		ud.connect();
		int result = ud.register(user);
		if(result==0){
			//put in session
			if(user.getRole().equals("true"))session.setAttribute("sid", ud.hm.get("sid"));
			else session.setAttribute("uid", ud.hm.get("uid"));
			session.setAttribute("firstname", ud.hm.get("firstname"));
			return 0;
		}
		else if(result==1){
			message = ud.hm.get("message");
			return 1;
		}
		else if(result==2){
			message = ud.hm.get("message");
			return 2;
		}
		else{
			message = ud.hm.get("message");
			return 3;
		}
	}
	public void logout(HttpSession session){
		//HttpSession session = request.getSession();
		session.removeAttribute("uid");
		session.removeAttribute("firstname");
		session.invalidate();
	}
}
