package com.chinasoft.shop.car.web.servlet;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasoft.shop.car.domain.Car;
import com.chinasoft.shop.car.domain.SpareCar;
import com.chinasoft.shop.car.service.CarService;
import com.chinasoft.shop.car.service.imlp.CarServiceImpl;
import com.chinasoft.shop.goods.domain.Goods;
import com.chinasoft.shop.goods.service.GoodsService;
import com.chinasoft.shop.goods.service.impl.GoodsServiceImpl;
import com.chinasoft.shop.utils.base.servlet.BaseServlet;

@WebServlet("/CarServlet")
public class CarServlet extends BaseServlet {

	CarService cs = new CarServiceImpl();
	GoodsService gs = new GoodsServiceImpl();

	public void add(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("进入添加商品到购物车。");
		String uid = req.getParameter("uid");
		String gid = req.getParameter("gid");
		String number = req.getParameter("quantity");
		int quantity = Integer.parseInt(number);// 数量

		Goods goods = gs.findById(gid);

		String gName = goods.getName();
		String gAvatar = goods.getgAvatar();
		double spprice = goods.getSpprice();
		System.out.println(spprice);
		String id = UUID.randomUUID().toString().replace("-", "");

		double subtotal = spprice * quantity;// 小计
		System.out.println(subtotal);

		Car car = new Car();
		car.setId(id);
		car.setUid(uid);
		car.setGid(gid);
		car.setQuantity(quantity);
		car.setSubtotal(subtotal);

//		SpareCar scar = new SpareCar();
//		scar.setGoods(goods);
//		scar.setQuantity(quantity);
//		scar.setSubtotal(subtotal);
//		scar.setUig(uid);
		cs.add(car);

	}

	public void findAllByUid(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("进入查看购物车。");
		String uid = req.getParameter("uid");
		List<SpareCar> scarList = cs.findAllByUid(uid);
		int size=scarList.size();
		double totalPay=cs.calculateTotalPay(scarList);//计算总金额
		req.getSession().setAttribute("size", size);
		req.setAttribute("totalPay", totalPay);
		req.getSession().setAttribute("scarList", scarList);
		req.getRequestDispatcher("/cart.jsp").forward(req, resp);

	}
	
	public void findByUid(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("进入查看购物车商品数量。");
		String uid = req.getParameter("uid");
		List<SpareCar> scarList = cs.findAllByUid(uid);
		double totalPay=cs.calculateTotalPay(scarList);//计算总金额
		req.setAttribute("totalPay", totalPay);
		req.getSession().setAttribute("scarList", scarList);
		req.getRequestDispatcher("/navbar.jsp").forward(req, resp);

	}
}
