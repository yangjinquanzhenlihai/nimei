package com.chinasoft.shop.category.web.servlet;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasoft.shop.category.domain.Category;
import com.chinasoft.shop.category.service.CategoryException;
import com.chinasoft.shop.category.service.CategoryService;
import com.chinasoft.shop.category.service.impl.CategoryServiceImpl;
import com.chinasoft.shop.utils.base.servlet.BaseServlet;

@WebServlet("/CategoryServlet")
public class CategoryServlet extends BaseServlet{
	

	CategoryService cgs= new CategoryServiceImpl();
	
	//��Ʒ�������
	public void add(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("������Ʒ�������");
		String name=req.getParameter("name");
		String id=UUID.randomUUID().toString().replace("-", "");
		int goodsCount=10;
		
		Category cg=new Category();
		
		cg.setId(id);
		cg.setName(name);
		cg.setGoodsCount(goodsCount);
		
		cgs.add(cg);
		
		req.setAttribute("msg", "��ӳɹ�");
		req.getRequestDispatcher("/admin/category_add.jsp").forward(req, resp);
	}
	
	//�������з���ת���������б�
	public void findAll(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("����������з��ಢת���������б�");
		List<Category> categories=cgs.findAll();
		req.setAttribute("categories", categories);
		req.getRequestDispatcher("/admin/category_list.jsp").forward(req, resp);
	}

	public void del(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("����ɾ�����ࡣ");
		String id = req.getParameter("id");
		cgs.del(id);
		findAll(req,resp);
	}
	
	//�������з���ת������Ʒ���ҳ��
	public void findAllToAdd(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("����������з��ಢת������Ʒ���ҳ�档");
		List<Category> categories=cgs.findAll();
		req.setAttribute("categories", categories);
		req.getRequestDispatcher("/admin/goods_add.jsp").forward(req, resp);
	}
	
	
	public void findByName(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	
		System.out.println("��������������Ƿ��ظ���");
		String name=req.getParameter("name");
		try {
			cgs.findByName(name);
			resp.getWriter().print("OK");
		} catch (CategoryException e) {
			resp.getWriter().print(e.getMessage());
		}
	}
	public void find(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		//System.out.println("����������з��ಢ����session");
		List<Category> categories=cgs.findAll();
		req.setAttribute("categories", categories);
		//req.getSession().setAttribute("categories", categories);
		req.getRequestDispatcher("/search.jsp").forward(req, resp);
	}
}
