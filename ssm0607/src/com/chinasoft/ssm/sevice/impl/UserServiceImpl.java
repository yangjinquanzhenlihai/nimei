package com.chinasoft.ssm.sevice.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.chinasoft.ssm.mapper.UserMapper;
import com.chinasoft.ssm.pojo.Orders;
import com.chinasoft.ssm.pojo.User;
import com.chinasoft.ssm.sevice.UserService;

public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;
	public List<User> findAll() {
		return userMapper.findAll();
	}
	@Override
	public List<Orders> findGood_User() {
		return userMapper.findGood_User();
	}

}
