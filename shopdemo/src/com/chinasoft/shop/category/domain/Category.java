package com.chinasoft.shop.category.domain;

public class Category {
	private String id;			//id
	private String name;		//分类名称
	private int goodsCount;//商品的数量
	
	public Category(){
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getGoodsCount() {
		return goodsCount;
	}

	public void setGoodsCount(int goodsCount) {
		this.goodsCount = goodsCount;
	}
	
}
