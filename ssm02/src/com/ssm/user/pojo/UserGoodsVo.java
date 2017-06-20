package com.ssm.user.pojo;

import java.util.ArrayList;
import java.util.List;

public class UserGoodsVo {
	private List<User> users = new ArrayList<User>();
	private List<Goods> goods = new ArrayList<Goods>();
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public List<Goods> getGoods() {
		return goods;
	}
	public void setGoods(List<Goods> goods) {
		this.goods = goods;
	}
	@Override
	public String toString() {
		return "UserGoodsVo [users=" + users + ", goods=" + goods + "]";
	}
	
}
