package com.chinasoft.ssm.pojo;

public class OrderDetail {
	private int id;
	private int goodscount;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getGoodscount() {
		return goodscount;
	}
	public void setGoodscount(int goodscount) {
		this.goodscount = goodscount;
	}
	@Override
	public String toString() {
		return "OrderDetail [id=" + id + ", goodscount=" + goodscount + "]";
	}
}
