package com.ssm.user.mapper;

import com.ssm.user.pojo.Goods;
import com.ssm.user.pojo.GoodsE;
import com.ssm.user.pojo.UserGoodsVo;

public interface UserMapper {
	UserGoodsVo findGoods_User(GoodsE goodse);

	void deleteGoods(int id);

	Goods updateGoods(Goods goods);

	Goods findGoodsById(int id);
}
