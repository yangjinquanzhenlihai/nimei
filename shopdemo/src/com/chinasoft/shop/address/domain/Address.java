package com.chinasoft.shop.address.domain;

/** 
 * @ClassName: Address 
 * @Description: TODO
 * @author: 李浩如
 * @date: 2017年5月18日 上午9:40:55  
 */
public class Address {
	private String id;
	private String uid;
	private String name;
	private String area;//所在地区
	private String street;//所在街道
	private String zipcode;//邮编
	private String telephone;
	private String cellphone;
	private String selected;//设置默认首选 1为选中
	
	public Address(){
		
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getSelected() {
		return selected;
	}

	public void setSelected(String selected) {
		this.selected = selected;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

}
