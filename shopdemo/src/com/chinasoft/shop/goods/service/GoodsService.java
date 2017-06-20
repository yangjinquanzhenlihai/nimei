package com.chinasoft.shop.goods.service;

import java.util.List;

import com.chinasoft.shop.car.domain.SpareCar;
import com.chinasoft.shop.goods.domain.Goods;
import com.chinasoft.shop.goods.domain.QueryGoods;
import com.chinasoft.shop.utils.domain.PageBean;

public interface GoodsService {

	void add(Goods goods);

	void checkGoods(String name, String brand, String type)  throws GoodsException;

	String creatId();

	PageBean<Goods> findAll(int pageSize, int pageCode);

	void del(String id);

	PageBean<Goods> findAll(QueryGoods queryGoods, int pageSize, int pageCode);

	List<Goods> findHotGoods();

	List<Goods> findNewGoods();

	Goods findById(String id);

	List<Goods> findHotGoodsByCid(String cid);

	List<Goods> findNewGoodsByCid(String cid);

}
