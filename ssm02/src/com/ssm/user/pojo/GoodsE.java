package com.ssm.user.pojo;

public class GoodsE {
	private String name;
	private Double minprice;
	private Double maxprice;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getMinprice() {
		return minprice;
	}

	public void setMinprice(Double minprice) {
		this.minprice = minprice;
	}

	public Double getMaxprice() {
		return maxprice;
	}

	public void setMaxprice(Double maxprice) {
		this.maxprice = maxprice;
	}

	public String toString() {
		return "GoodsE [name=" + name + ", minprice=" + minprice
				+ ", maxprice=" + maxprice + "]";
	}
	
}
