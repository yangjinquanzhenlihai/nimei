package com.chinasoft.ssm.mapper;

import java.util.List;



import com.chinasoft.ssm.pojo.Orders;
import com.chinasoft.ssm.pojo.User;

public interface UserMapper {

	List<User> findAll();
	
	List<Orders> findGood_User();
}
