package com.ssm.user.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class Orders {
	private int id;
	private Date createtime;
	private String description;
	private User user;
	private List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {

		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	@Override
	public String toString() {
		return "Orders [id=" + id + ", createtime=" + createtime
				+ ", description=" + description + ", user=" + user
				+ ", orderDetails=" + orderDetails 
				+ "]";
	}


}
