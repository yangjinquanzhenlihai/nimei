package com.chinasoft.shop.orderform.domain;
import com.chinasoft.shop.goods.domain.Goods;



public class OrderDetail {


	private Goods goods;
	private int nums;
	
//	getter and setter
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	public int getNums() {
		return nums;
	}
	public void setNums(int nums) {
		this.nums = nums;
	}
	
}
