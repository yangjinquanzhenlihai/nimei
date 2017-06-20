package com.chinasoft.shop.car.domain;

import com.chinasoft.shop.goods.domain.Goods;


public class SpareCar {//显示用、备用
	private String id;
	private String uig;
	private int quantity;//数量
	private double subtotal;//小计
	private Goods goods;
	public String getUig() {
		return uig;
	}
	public void setUig(String uig) {
		this.uig = uig;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

}
