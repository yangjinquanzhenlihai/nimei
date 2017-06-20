package com.ssm.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssm.user.pojo.Goods;
import com.ssm.user.pojo.GoodsE;
import com.ssm.user.pojo.UserGoodsVo;
import com.ssm.user.service.UserService;
@Controller
public class UserHandler {
	@Autowired
	private UserService userService;
	@RequestMapping("findGoods_User.action")
	public String findGoods_User(GoodsE goodse,Model model){
		UserGoodsVo userGoodsVo = userService.findGoods_User(goodse);
		model.addAttribute("userGoodsVo", userGoodsVo);
		return "goods/goodsList";
	}
	@RequestMapping("deleteGoods.action")
	public String deleteGoods(int id,Model model){
		String message = userService.deleteGoods(id);
		model.addAttribute("message", message);
		return"goods/goodsQuery";
	}
	@RequestMapping("findGoodsById.action")
	public String findGoodsById(int id,Model model){
		Goods goods = userService.findGoodsById(id);
		model.addAttribute("goods", goods);
		return "goods/goodsUpdate";
		}
	@RequestMapping("updateGoods.action")
	public String updateGoods(int id,Model model){
		Goods goods = userService.findGoodsById(id);
		userService.updateGoods(goods);
		return null;
		
	}
}
