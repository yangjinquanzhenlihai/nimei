package com.ssm.user.service;

import com.ssm.user.pojo.Goods;
import com.ssm.user.pojo.GoodsE;
import com.ssm.user.pojo.UserGoodsVo;

public interface UserService {
	UserGoodsVo findGoods_User(GoodsE goodse);

	String deleteGoods(int id);

	 void updateGoods(Goods goods);

	Goods findGoodsById(int id);
}
