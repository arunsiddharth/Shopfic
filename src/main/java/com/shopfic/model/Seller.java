package com.shopfic.model;

public class Seller {
	String id_proof;
	String image_path;
	String company;
	
	public String getId_proof() {
		return id_proof;
	}
	public void setId_proof(String id_proof) {
		this.id_proof = id_proof;
	}
	public String getImage_path() {
		return image_path;
	}
	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	@Override
	public String toString() {
		return "Seller [id_proof=" + id_proof + ", image_path=" + image_path + ", company=" + company + "]";
	}
	
}
