package com.chinasoft.shop.goods.domain;

public class QueryGoods {
	private String cid;//����id
	private String name;//��Ʒ����
	private double spprice1;//���ۼ�1
	private double spprice2;//���ۼ�1

	public QueryGoods(){
		
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSpprice1() {
		return spprice1;
	}

	public void setSpprice1(double spprice1) {
		this.spprice1 = spprice1;
	}

	public double getSpprice2() {
		return spprice2;
	}

	public void setSpprice2(double spprice2) {
		this.spprice2 = spprice2;
	}
	
}
