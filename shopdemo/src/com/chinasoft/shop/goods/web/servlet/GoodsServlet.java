package com.chinasoft.shop.goods.web.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasoft.shop.car.domain.SpareCar;
import com.chinasoft.shop.car.service.CarService;
import com.chinasoft.shop.car.service.imlp.CarServiceImpl;
import com.chinasoft.shop.category.domain.Category;
import com.chinasoft.shop.category.service.CategoryService;
import com.chinasoft.shop.category.service.impl.CategoryServiceImpl;
import com.chinasoft.shop.goods.domain.Goods;
import com.chinasoft.shop.goods.domain.QueryGoods;
import com.chinasoft.shop.goods.service.GoodsException;
import com.chinasoft.shop.goods.service.GoodsService;
import com.chinasoft.shop.goods.service.impl.GoodsServiceImpl;
import com.chinasoft.shop.user.domain.User;
import com.chinasoft.shop.utils.base.servlet.BaseServlet;
import com.chinasoft.shop.utils.domain.PageBean;

@WebServlet("/GoodsServlet")
public class GoodsServlet extends BaseServlet {

	GoodsService gs = new GoodsServiceImpl();
	CategoryService cgs = new CategoryServiceImpl();
	CarService cs = new CarServiceImpl();
	

	public void creatId(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("进入生成商品货号。");
		String name = req.getParameter("name");

		if (name != null && name != "") {
			String goodsNum = gs.creatId();
			resp.getWriter().print(goodsNum);
		}
	}

	public void checkGoods(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("进入检查商品名，品牌，型号。");
		String name = req.getParameter("name");
		String info = req.getParameter("info");
		String[] infoAll = info.split("，");
		if (info.indexOf("，") == -1) {
			List<Category> categories = cgs.findAll();
			req.setAttribute("categories", categories);
			resp.getWriter().print("*请输入正确产品描述。");
		} else {
			String brand = infoAll[0];
			String type = infoAll[1];

			try {
				gs.checkGoods(name, brand, type);
				resp.getWriter().print("OK");

			} catch (GoodsException e) {
				resp.getWriter().print(e.getMessage());
			}

		}
	}

	public void findAll(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("进入商品列表");
		List<Category> categories = cgs.findAll();
		req.getSession().setAttribute("categories", categories);
		int pageSize = 10;
		int pageCode = getPageCode(req);
		System.out.println("当前页:" + pageCode);
		PageBean<Goods> pb = gs.findAll(pageSize, pageCode);
		pb.setUrl(getUrl(req));
		req.setAttribute("pb", pb);
		req.getRequestDispatcher("/admin/goods_list.jsp").forward(req, resp);

	}

	/*
	 * 获取请求url
	 */
	private String getUrl(HttpServletRequest request) {
		String url = null;
		try {
			url = URLDecoder.decode(request.getQueryString(), "UTF-8");// 获取请求参数
			int index = url.lastIndexOf("&");
			url = url.substring(0, index);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return url;
	}

	// 获取当前页码
	private int getPageCode(HttpServletRequest req) {

		String pageCode = req.getParameter("pageCode");
		if (pageCode == null || pageCode.trim().isEmpty()) {
			return 1;
		}
		return Integer.parseInt(pageCode);
	}
	
	
	public void del(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("进入删除商品。");
		String id=req.getParameter("id");
		gs.del(id);
		findAll(req,resp);
	}
	
//	多条件查询
	public void query(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("进入高级查询。");
		String cid = req.getParameter("categoryId");
		String name = req.getParameter("name");
		String spprice1 = req.getParameter("queryGoods.spprice1");
		String spprice2 = req.getParameter("queryGoods.spprice2");
		QueryGoods queryGoods = new QueryGoods();
		queryGoods.setCid(cid);
		queryGoods.setName(name);
		if(spprice1!=""&&spprice1!=null){
			queryGoods.setSpprice1(Double.parseDouble(spprice1));
		}
		if(spprice2!=""&&spprice2!=null){
			queryGoods.setSpprice2(Double.parseDouble(spprice2));
		}
		req.getSession().setAttribute("queryGoods", queryGoods);
		int pageSize = 10;//定义每页 的记录数
		int pageCode = getPageCode(req);
		String url = getUrl(req);
		PageBean<Goods> pb =gs.findAll(queryGoods,pageSize,pageCode);
		pb.setUrl(url);
		req.setAttribute("pb", pb);
		req.getRequestDispatcher("admin/goods_list.jsp").forward(req, resp);

	}
	
	public void allInformation(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		System.out.println("进入查找热卖、新品、分类。");
		//List<Goods> list = new ArrayList<Goods>();
		List<Category> categories = cgs.findAll();
		req.getSession().setAttribute("categories", categories);
		List<Goods> hotlist = gs.findHotGoods();
		List<Goods> newlist = gs.findNewGoods();
		//req.getSession().setAttribute("list", list);
		req.getSession().setAttribute("hotlist",hotlist);
		req.getSession().setAttribute("newlist", newlist);	
		req.getRequestDispatcher("/index.jsp").forward(req, resp);
	}
	
	public void goodsview(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		System.out.println("进入商品信息。");
		String id = req.getParameter("id");
		Goods goods = gs.findById(id);
		List<Goods> hotlist = gs.findHotGoods();
		List<Goods> newlist = gs.findNewGoods();
		req.getSession().setAttribute("goods",goods);
		req.getSession().setAttribute("hotlist",hotlist);
		req.getSession().setAttribute("newlist", newlist);
		req.getRequestDispatcher("/goods_view.jsp").forward(req, resp);
	}

	public void findByCid(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		System.out.println("进入通过分类ID查找商品。");
		String cid=req.getParameter("cid");
		List<Goods> hotlist = gs.findHotGoodsByCid(cid);
		List<Goods> newlist = gs.findNewGoodsByCid(cid);
		req.getSession().setAttribute("hotlist",hotlist);
		req.getSession().setAttribute("newlist", newlist);	
		req.getRequestDispatcher("/index.jsp").forward(req, resp);
		
	}
}
