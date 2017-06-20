package com.chinasoft.shop.orderform.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;













import com.chinasoft.shop.address.domain.Address;
import com.chinasoft.shop.address.service.AddressService;
import com.chinasoft.shop.address.service.impl.AddressServiceImpl;
import com.chinasoft.shop.goods.domain.Goods;
import com.chinasoft.shop.goods.service.GoodsService;
import com.chinasoft.shop.goods.service.impl.GoodsServiceImpl;
import com.chinasoft.shop.orderform.domain.OrderDetail;
import com.chinasoft.shop.orderform.domain.OrderForm;
import com.chinasoft.shop.orderform.service.OrderFormService;
import com.chinasoft.shop.orderform.service.impl.OrderFormServiceImpl;
import com.chinasoft.shop.user.domain.User;
import com.chinasoft.shop.utils.base.servlet.BaseServlet;


@WebServlet("/OrderFormServlet")
public class OrderFormServlet extends BaseServlet{
	OrderFormService orderformService = new OrderFormServiceImpl();
	GoodsService goodsService = new GoodsServiceImpl();
	AddressService addressService = new AddressServiceImpl();
//	根据id查找所有的地址转发到订单jsp
	public void findAll(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String muns = req.getParameter("buyNums");
		String id = req.getParameter("gid");
		Goods goods = goodsService.findById(id);
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setGoods(goods);
		orderDetail.setNums(Integer.parseInt(muns));
		List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();
		orderDetails.add(orderDetail);
		req.setAttribute("orderDetails", orderDetails);
		String uid = req.getParameter("uid");
		List addresses = addressService.findAllByUid(uid);
		req.setAttribute("addresses", addresses);
		req.getRequestDispatcher("/order_add.jsp").forward(req, resp);
	}
	
//	添加订单
	public void orderAdd(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("进入添加订单。");
		User user = (User) req.getSession().getAttribute("user");
		String gid = req.getParameter("orderDetails[0].goods.id");
		String goodsCount = req.getParameter("orderDetails[0].nums");

		String aid = req.getParameter("order.address.id");
		String deliveryType = req.getParameter("order.deliveryType");
		String deliveryTime = req.getParameter("order.deliveryTime");
		String payType = req.getParameter("order.payType");
		String totalMoney = req.getParameter("order.totalMoney");
	
		
		OrderForm orderform = new OrderForm();
		orderform.setId(UUID.randomUUID().toString().replace("-", ""));
		orderform.setDate(new Date());
		orderform.setUid(user.getId());
		orderform.setAid(aid);
		orderform.setPayType(payType);
		orderform.setTotalMoney(Double.parseDouble(totalMoney));
		orderform.setDeliveryTime(deliveryTime);
		orderform.setDeliveryType(deliveryType);
		orderform.setGoodsCount(Integer.parseInt(goodsCount));
		
		orderformService.add(orderform);
		req.setAttribute("orderform", orderform);
		req.getRequestDispatcher("/order_submit.jsp").forward(req, resp);
		
	}
	
}
