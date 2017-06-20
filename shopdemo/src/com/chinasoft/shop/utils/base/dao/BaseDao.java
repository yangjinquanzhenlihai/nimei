package com.chinasoft.shop.utils.base.dao;

import java.sql.SQLException;
import java.util.List;

import com.chinasoft.shop.address.domain.Address;
import com.chinasoft.shop.goods.domain.Goods;
import com.chinasoft.shop.user.domain.User;
import com.chinasoft.shop.utils.domain.PageBean;

public interface BaseDao<T> {
	
	void add(T t) throws IllegalArgumentException, IllegalAccessException, SQLException;//添加实体类
	void delete(String id);//删除实体类
	void update(T t);//更新属性
	List<T> findAll();//查询所有对象
	T findById(String id);//根据id查询对象
	T findByToken(String token);//根据token查询对象
	T findByName(String name);//根据name查询对象
	T checkName(String name);
	List<T> findAllByUid(String uid);
	
	
	
}
