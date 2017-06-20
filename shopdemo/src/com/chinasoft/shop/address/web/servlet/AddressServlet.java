package com.chinasoft.shop.address.web.servlet;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasoft.shop.address.domain.Address;
import com.chinasoft.shop.address.service.AddressService;
import com.chinasoft.shop.address.service.impl.AddressServiceImpl;
import com.chinasoft.shop.user.domain.User;
import com.chinasoft.shop.utils.base.servlet.BaseServlet;

@WebServlet("/AddressServlet")
public class AddressServlet extends BaseServlet{

	AddressService addressService=new AddressServiceImpl();
	
	
	public void add(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("����ӵ�ַ");
		
		User user=(User) req.getSession().getAttribute("user");
		String uid=user.getId();//��ǰ�û�ID
		
		String id=req.getParameter("id");
		
		if(id==null||id.equals("")){
			id=UUID.randomUUID().toString().replace("-", "");
		}
		
		
		String name=req.getParameter("address.accept");//�ջ�������
		
		StringBuilder province=new StringBuilder(req.getParameter("address.province"));
		StringBuilder city=new StringBuilder(req.getParameter("address.city"));
		StringBuilder area=new StringBuilder(req.getParameter("address.area"));
		
		area.insert(0, city).insert(0, province);
		String street=req.getParameter("address.address");//�ֵ�
		
		String zipcode=req.getParameter("address.zip");
		String telephone=req.getParameter("address.phoneNum");
		String cellphone=req.getParameter("address.mobile");
		String selected=req.getParameter("address.isdefault")==null?"0":"1";
		
		Address address=new Address();
		address.setId(id);
		address.setUid(uid);
		address.setName(name);
		address.setArea(area.toString());
		address.setStreet(street);
		address.setCellphone(cellphone);
		address.setTelephone(telephone);
		address.setZipcode(zipcode);
		address.setSelected(selected);
		
		if (selected.equals("1")) {
			
			addressService.cancelSelected(address.getUid());
			
		}
		
		addressService.add(address);
		
		findAllByUid(req,resp);
		
		req.getRequestDispatcher("usercenter/address_list.jsp").forward(req, resp);
		
	}
	
	//���ҵ�ǰ�û��������ջ���ַ
	public void findAllByUid(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("������ҵ�ǰ�û��������ջ���ַ��");
		User user=(User) req.getSession().getAttribute("user");
		String uid=user.getId();//��ǰ�û�ID
		
		List<Address> userAddressList= addressService.findAllByUid(uid);
		
		req.setAttribute("userAddressList", userAddressList);
		req.getRequestDispatcher("usercenter/address_list.jsp").forward(req, resp);
		
	}
	
	
	public void cancelSelected(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String uid=req.getParameter("uid");
		addressService.cancelSelected(uid);
		req.getRequestDispatcher("usercenter/address_list.jsp").forward(req, resp);
		findAllByUid(req,resp);
		
	}
	
	//����Ĭ�ϵ�ַ
	public void setSelected(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		User user=(User) req.getSession().getAttribute("user");
		String uid=user.getId();//��ǰ�û�ID
		
		String id=req.getParameter("id");
		addressService.cancelSelected(uid);
		
		addressService.setSelected(id);
		
		findAllByUid(req,resp);
		
	}
	
	//�޸ĵ�ַ
	public void alterAddress(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("�����޸ĵ�ַ��");
		String id=req.getParameter("id");
		Address address=addressService.findAllByid(id);
		
		req.setAttribute("address", address);
		req.getRequestDispatcher("/usercenter/address_list.jsp").forward(req, resp);
		
	}
	
	//ɾ����ַ
	public void deleteAddress(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("����ɾ����ַ��");
		String id=req.getParameter("id");
		
		addressService.deleteAddress(id);
		findAllByUid(req,resp);
	}
	
}
