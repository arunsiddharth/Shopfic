package com.shopfic.model;

public class Cart extends Product{
	int uid;
	int pid;
	int count;
	int sid;
	int request;
	int mark;
	int brought;
	String transaction_id;
	int cod;
	String date_request;
	String date_brought;
	
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public int getRequest() {
		return request;
	}
	public void setRequest(int request) {
		this.request = request;
	}
	public int getMark() {
		return mark;
	}
	public void setMark(int mark) {
		this.mark = mark;
	}
	public int getBrought() {
		return brought;
	}
	public void setBrought(int brought) {
		this.brought = brought;
	}
	public String getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}
	public int getCod() {
		return cod;
	}
	public void setCod(int cod) {
		this.cod = cod;
	}
	public String getDate_request() {
		return date_request;
	}
	public void setDate_request(String date_request) {
		this.date_request = date_request;
	}
	public String getDate_brought() {
		return date_brought;
	}
	public void setDate_brought(String date_brought) {
		this.date_brought = date_brought;
	}
	
}
