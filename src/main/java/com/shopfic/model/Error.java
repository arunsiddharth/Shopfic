package com.shopfic.model;

public class Error {
	@Override
	public String toString() {
		return "Error [message=" + message + "]";
	}
	String message;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Error(String message){
		this.message=message;
	}
	public Error(){
		this.message="";
	}
}
