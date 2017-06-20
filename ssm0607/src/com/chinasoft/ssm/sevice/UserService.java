package com.chinasoft.ssm.sevice;

import java.util.List;



import com.chinasoft.ssm.pojo.Orders;
import com.chinasoft.ssm.pojo.User;

public interface UserService {

	List<User> findAll();
	List<Orders> findGood_User();
}
