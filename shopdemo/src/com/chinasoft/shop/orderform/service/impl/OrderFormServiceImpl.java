package com.chinasoft.shop.orderform.service.impl;


import com.chinasoft.shop.orderform.dao.impl.OrderFormDaoImpl;
import com.chinasoft.shop.orderform.domain.OrderForm;
import com.chinasoft.shop.orderform.service.OrderFormService;

public class OrderFormServiceImpl implements OrderFormService {
	OrderFormDaoImpl orderDao = new OrderFormDaoImpl();

	public void add(OrderForm orderform) {
		 orderDao.add(orderform);;
	}
}
