package com.chinasoft.shop.user.service.impl;


import java.sql.SQLException;
import java.util.Date;

import com.chinasoft.shop.goods.domain.Goods;
import com.chinasoft.shop.user.dao.impl.UserDaoImpl;
import com.chinasoft.shop.user.domain.User;
import com.chinasoft.shop.user.service.UserException;
import com.chinasoft.shop.user.service.UserService;
import com.chinasoft.shop.utils.MailUtil;
import com.chinasoft.shop.utils.base.dao.BaseDao;
import com.chinasoft.shop.utils.domain.PageBean;

public class UserServiceImpl implements UserService {
	
	BaseDao<User> userDao=new UserDaoImpl();
	
	UserDaoImpl ud=new UserDaoImpl();
	@Override
	public void register(User user) {
		
	     try {
			userDao.add(user);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	  MailUtil.sendMail(user.getEmail(),user.getToken());
	   
	}
	@Override
	public void activate(String token) throws UserException {
		
		User user = userDao.findByToken(token);
		
		long time=new Date().getTime()-user.getRegTime().getTime();
		if (time/1000/60>10) {
			userDao.delete(user.getId());
			throw new UserException("激活码已过期");
		}
		user.setState("1");
		userDao.update(user);
		
	}
	@Override
	public User checkName(String name) {
		
		return userDao.checkName(name);
	}
	@Override
	public User login(String name, String md5pwd) throws UserException {
		
		User user=userDao.findByName(name);
		if (user==null) {
			throw new UserException("用户名不存在");
		}
		if (!user.getPassword().equals(md5pwd)) {
			throw new UserException("密码错误");
		}
		
		if (user.getState().equals("0")) {
			throw new UserException("该用户未激活");
		}
		return user;
	}
	@Override
	public void changePwd(String name, String md5old, String md5new) throws UserException {
		User user=userDao.findByName(name);
		if (!user.getPassword().equals(md5old)) {
			throw new UserException("输入的旧密码有误");
		}
		user.setPassword(md5new);
		userDao.update(user);
		
	}
	@Override
	public PageBean<User> findAll(int pageSize, int pageCode) {
		
		return  ud.findAllGoods(pageSize,pageCode);
	}
	@Override
	public void del(String id) {
		userDao.delete(id);
		
	}
	@Override
	public void addBalance(User user) {
		userDao.update(user);
		
	}
	@Override
	public void update(User user) {
		
		userDao.update(user);
	}

}
