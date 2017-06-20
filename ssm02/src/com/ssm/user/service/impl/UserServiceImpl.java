package com.ssm.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssm.user.mapper.UserMapper;
import com.ssm.user.pojo.Goods;
import com.ssm.user.pojo.GoodsE;
import com.ssm.user.pojo.UserGoodsVo;
import com.ssm.user.service.UserService;
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserMapper userMapper;
	@Override
	public UserGoodsVo findGoods_User(GoodsE goodse) {
		return userMapper.findGoods_User(goodse);
	}
	@Override
	public String deleteGoods(int id) {
		String message = "删除成功";
		try {
			userMapper.deleteGoods(id);
		} catch (Exception e) {
			message="删除失败，该数据不能删除";
		}
		return message;
	}
	@Override
	public void updateGoods(Goods goods) {
		userMapper.updateGoods( goods);
	}
	@Override
	public Goods findGoodsById(int id) {
		return userMapper.findGoodsById(id);
	}

}
