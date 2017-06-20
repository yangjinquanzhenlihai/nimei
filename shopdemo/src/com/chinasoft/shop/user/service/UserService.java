package com.chinasoft.shop.user.service;

import com.chinasoft.shop.goods.domain.Goods;
import com.chinasoft.shop.user.domain.User;
import com.chinasoft.shop.utils.domain.PageBean;

public interface UserService {

	void register(User user);

	void activate(String token) throws UserException;

	User checkName(String name);

	User login(String name, String md5pwd) throws UserException;

	void changePwd(String uname, String md5old, String md5new) throws UserException;

	PageBean<User> findAll(int pageSize, int pageCode);

	void del(String id);

	

	void addBalance(User user);

	void update(User user);

}
