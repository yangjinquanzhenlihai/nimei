package com.ssm.user.pojo;

public class Goods {
	private int id;
	private String gname;
	private double price;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getGname() {
		return gname;
	}
	public void setGname(String gname) {
		this.gname = gname;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Goods [id=" + id + ", gname=" + gname + ", price=" + price
				+ "]";
	}
	
}
