package com.chinasoft.shop.address.domain;

/** 
 * @ClassName: Address 
 * @Description: TODO
 * @author: �����
 * @date: 2017��5��18�� ����9:40:55  
 */
public class Address {
	private String id;
	private String uid;
	private String name;
	private String area;//���ڵ���
	private String street;//���ڽֵ�
	private String zipcode;//�ʱ�
	private String telephone;
	private String cellphone;
	private String selected;//����Ĭ����ѡ 1Ϊѡ��
	
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
