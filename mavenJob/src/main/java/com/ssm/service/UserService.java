package com.ssm.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.ssm.pojo.User;
import com.ssm.pojo.UserException;
public interface UserService {
	
//添加用户
	void register(User user)throws UserException;
//根据account查找用户
	User findUserByAccount(String account);
//	用户注册
	User login(User form) throws UserException;
//	跟新用户
	void update(User user);
	
	public String getIpAddr(HttpServletRequest request);
}
