package com.chinasoft.shop.utils.base.dao;

import java.sql.SQLException;
import java.util.List;

import com.chinasoft.shop.address.domain.Address;
import com.chinasoft.shop.goods.domain.Goods;
import com.chinasoft.shop.user.domain.User;
import com.chinasoft.shop.utils.domain.PageBean;

public interface BaseDao<T> {
	
	void add(T t) throws IllegalArgumentException, IllegalAccessException, SQLException;//���ʵ����
	void delete(String id);//ɾ��ʵ����
	void update(T t);//��������
	List<T> findAll();//��ѯ���ж���
	T findById(String id);//����id��ѯ����
	T findByToken(String token);//����token��ѯ����
	T findByName(String name);//����name��ѯ����
	T checkName(String name);
	List<T> findAllByUid(String uid);
	
	
	
}
