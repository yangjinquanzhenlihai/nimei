package com.chinasoft.shop.goods.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import com.chinasoft.shop.goods.dao.impl.GoodsDaoImpl;
import com.chinasoft.shop.goods.domain.Goods;
import com.chinasoft.shop.goods.domain.QueryGoods;
import com.chinasoft.shop.goods.service.GoodsException;
import com.chinasoft.shop.goods.service.GoodsService;
import com.chinasoft.shop.user.domain.User;
import com.chinasoft.shop.utils.base.dao.BaseDao;
import com.chinasoft.shop.utils.domain.PageBean;

public class GoodsServiceImpl implements GoodsService {

	GoodsDaoImpl gd = new GoodsDaoImpl();
	Goods goods = new Goods();

	@Override
	public void add(Goods goods) {

		gd.add(goods);

	}

	@Override
	public void checkGoods(String name, String brand, String type)
			throws GoodsException {
		goods = gd.findByName(name);

		if (goods != null) {
			if (goods.getBrand().equals(brand)) {
				if (goods.getType().equals(type)) {
					throw new GoodsException("商品名：" + name + "，品牌：" + brand
							+ "，型号：" + type + ":此商品已存在，请核对信息后重新添加。");
				}
			}

		}

	}

	@Override
	public String creatId() {

		return UUID.randomUUID().toString().replace("-", "");

	}

	@Override
	public PageBean<Goods> findAll(int pageSize, int pageCode) {

		return gd.findAllGoods(pageSize, pageCode);
	}

	@Override
	public void del(String id) {
		gd.delete(id);

	}

	@Override
	public PageBean<Goods> findAll(QueryGoods queryGoods, int pageSize,
			int pageCode) {
		
		return gd.findAll(queryGoods,pageSize,pageCode);
	}

	@Override
	public List<Goods> findHotGoods() {
		
		return gd.findHotGoods();
	}

	@Override
	public List<Goods> findNewGoods() {
		
		return gd.findNewGoods();
	}

	@Override
	public Goods findById(String id) {
		
		return gd.findById(id);
	}

	@Override
	public List<Goods> findHotGoodsByCid(String cid) {
		
		return gd.findHotGoodsByCid(cid);
	}

	@Override
	public List<Goods> findNewGoodsByCid(String cid) {
		
		return gd.findNewGoodsByCid(cid);
	}
}
