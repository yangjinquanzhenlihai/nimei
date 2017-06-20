package com.chinasoft.ssm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.chinasoft.ssm.pojo.Orders;
import com.chinasoft.ssm.pojo.User;
import com.chinasoft.ssm.sevice.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	@RequestMapping("findAll.action")
	public ModelAndView findAll(){
		ModelAndView mav = new ModelAndView("user/userList");
		List<User> userList = userService.findAll();
		mav.addObject("userList",userList);
		return mav;
	}
	@RequestMapping("findGoods_User.action")
	public ModelAndView findGoods_User(){
		ModelAndView mav = new ModelAndView("goods/goodsList");
		List<Orders> goodsList = userService.findGood_User();
		mav.addObject("goodsList",goodsList);
		return mav;
		
	}
}
