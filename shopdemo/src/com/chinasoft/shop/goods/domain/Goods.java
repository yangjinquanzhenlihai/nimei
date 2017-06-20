package com.chinasoft.shop.goods.domain;

import java.util.Date;

public class Goods {
	private String id;
	private String name;
	private String cid;
	private double price; //�г���
	private int inventory;//���
	private double spprice;//��ϲ��
	private String brand;//Ʒ��
	private String type;//�ͺ�
	private String point;//����
	private String info;
	private int sellCount;//����
	private String gAvatar;//ͼƬ
	private String goodsNum;//��Ʒ����
	private Date addTime;

	public Goods(){
		
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

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getInventory() {
		return inventory;
	}

	public void setInventory(int inventory) {
		this.inventory = inventory;
	}

	public double getSpprice() {
		return spprice;
	}

	public void setSpprice(double spprice) {
		this.spprice = spprice;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPoint() {
		return point;
	}

	public void setPoint(String point) {
		this.point = point;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public int getSellCount() {
		return sellCount;
	}

	public void setSellCount(int sellCount) {
		this.sellCount = sellCount;
	}

	public String getgAvatar() {
		return gAvatar;
	}

	public void setgAvatar(String gAvatar) {
		this.gAvatar = gAvatar;
	}

	public String getGoodsNum() {
		return goodsNum;
	}

	public void setGoodsNum(String goodsNum) {
		this.goodsNum = goodsNum;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	
}
