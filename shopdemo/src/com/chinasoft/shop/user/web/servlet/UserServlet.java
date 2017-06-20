package com.chinasoft.shop.user.web.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chinasoft.shop.category.domain.Category;
import com.chinasoft.shop.goods.domain.Goods;
import com.chinasoft.shop.user.domain.User;
import com.chinasoft.shop.user.service.UserException;
import com.chinasoft.shop.user.service.UserService;
import com.chinasoft.shop.user.service.impl.UserServiceImpl;
import com.chinasoft.shop.utils.base.servlet.BaseServlet;
import com.chinasoft.shop.utils.domain.PageBean;
import com.ndktools.javamd5.Mademd5;

@WebServlet("/UserServlet")
public class UserServlet extends BaseServlet {

	UserService userService = new UserServiceImpl();

	/*
	 * ����ע��
	 */
	public void register(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("��ע�ᡣ");
		String name = req.getParameter("account");
		String password = req.getParameter("password");
		String cellphone = req.getParameter("phoneNum");
		String email = req.getParameter("email");
		String md5pwd = new Mademd5().toMd5(password);// �ѵ�¼���������MD5����
		String vericode = req.getParameter("captcha");// ��֤��

		User user = new User();
		user.setId(UUID.randomUUID().toString().replace("-", ""));
		user.setName(name);
		user.setPassword(md5pwd);
		user.setCellphone(cellphone);
		user.setEmail(email);
		user.setAvatar(path + "/images/user/default.jpg");
		user.setRole("0");
		user.setState("0");
		user.setToken(user.getId());
		user.setOrderCount(0);
		user.setTotalPay(0);
		user.setBalance(100);
		Date regTime = new Date();
		user.setRegTime(regTime);

		if (checkVericode(req, resp)) {
			userService.register(user);
			resp.getWriter().print("��ȥ���伤�");
		}

	}

	/*
	 * ������
	 */
	public void activate(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String token = req.getParameter("token");
		try {
			userService.activate(token);
			// ����ɹ�����ת��activate_suc.jsp
			resp.sendRedirect(path + "/activate_suc.jsp");
		} catch (UserException e) {
			// ����ʧ�ܣ���ת��activate_suc.jsp
			resp.sendRedirect(path + "/activate_fail.jsp");
		}

	}

	// ����û���
	public void checkName(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String name = req.getParameter("account");

		User user = userService.checkName(name);
		if (user != null) {
			resp.getWriter().print("�û����Ѿ�����");

		} else {
			resp.getWriter().print("OK");

		}
	}

	// �����֤��
	public boolean checkVericode(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("��������֤�롣");
		String vericode = req.getParameter("captcha");
		HttpSession session = req.getSession();
		String veriImg4 = (String) session.getAttribute("veriImg4");
		// vericode=vericode.toUpperCase();
		// veriImg4=veriImg4.toUpperCase();

		if (!vericode.equals(veriImg4)) {
			req.setAttribute("msg", "��֤�����");
			req.getRequestDispatcher("/register.jsp").forward(req, resp);
			return false;

		} else {
			req.setAttribute("msg", "OK");
			return true;
		}
	}

	public void login(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("�����¼.");
		// ��ȡ�Ƿ�ѡ�Զ���¼��ֵ
		// String autoLogin=req.getParameter("autoLogin");
		String name = req.getParameter("account");
		System.out.println(name);
		String password = req.getParameter("password");
		String md5pwd = new Mademd5().toMd5(password);

		try {
			User user = userService.login(name, md5pwd);
			// UserListener uListener=new UserListener();

			// ����ø�ѡ��û�б�ѡ�У���õ���valueֵΪnull��
			// if (autoLogin!=null) {//�ж��û��Ƿ�ѡ�иø�ѡ��
			name = URLEncoder.encode(name, "UTF-8");
			Cookie cookie1 = new Cookie("account", name);
			Cookie cookie2 = new Cookie("password", password);

			cookie1.setMaxAge(60 * 60 * 24 * 7);// cookieʱ�䵥λ����
			cookie2.setMaxAge(60 * 60 * 24 * 7);
			resp.addCookie(cookie1);
			resp.addCookie(cookie2);
			// }

			String role = user.getRole();
			Integer roleInt = Integer.parseInt(role);
			req.getSession().setAttribute("user", user);

			if (roleInt == 0) {
				resp.sendRedirect(path + "/beforeindex.jsp");
			} else if (roleInt == 1) {
				resp.sendRedirect(path + "/admin/index.jsp");
			} else
				resp.sendRedirect(path + "/admin2/index.jsp");

			// req.setAttribute("count", uListener.getCount());
			// System.out.println(uListener.getCount());
			// req.getRequestDispatcher("/index.jsp").forward(req, resp);

		} catch (UserException e) {
			req.setAttribute("errorMsg", e.getMessage());
			System.out.println(e.getMessage());
			req.getRequestDispatcher("/login.jsp").forward(req, resp);

		}
	}

	// �޸�����
	public void changePwd(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("�����޸�����");
		User user = (User) req.getSession().getAttribute("user");
		String name = user.getName();
		System.out.println(name);
		String oldpwd = req.getParameter("oldpwd");
		String newpwd = req.getParameter("newpwd");
		String renewpwd = req.getParameter("renewpwd");
		String md5old = new Mademd5().toMd5(oldpwd);
		String md5new = new Mademd5().toMd5(newpwd);
		if (!newpwd.equals(renewpwd)) {
			req.setAttribute("errorMsg2", "�����������벻һ��");
			req.getRequestDispatcher("/change_pwd.jsp").forward(req, resp);
		} else {
			try {
				userService.changePwd(name, md5old, md5new);
				req.getSession().invalidate();// ����session
				req.getRequestDispatcher("/changepwd_suc.jsp").forward(req,
						resp);
			} catch (UserException e) {
				req.setAttribute("errorMsg", e.getMessage());
				req.getRequestDispatcher("/change_pwd.jsp").forward(req, resp);
			}
		}
	}

	// �˳�
	public void logout(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.getSession().invalidate();// ����session
		req.getRequestDispatcher("/beforeindex.jsp").forward(req, resp);
		// ת��·�������Ŀ
		// �ض�������ڷ�����
		// resp.sendRedirect(path+"/index.jsp");
	}

	// �������û�
	public void findAll(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("��������û���");
		int pageSize = 10;
		int pageCode = getPageCode(req);
		System.out.println("��ǰҳ:" + pageCode);
		PageBean<User> pb = userService.findAll(pageSize, pageCode);
		pb.setUrl(getUrl(req));
		req.setAttribute("pb", pb);
		req.getRequestDispatcher("/admin/member_list.jsp").forward(req, resp);
	}

	/*
	 * ��ȡ����url
	 */
	private String getUrl(HttpServletRequest request) {
		String url = null;
		try {
			url = URLDecoder.decode(request.getQueryString(), "UTF-8");// ��ȡ�������
			int index = url.lastIndexOf("&");
			url = url.substring(0, index);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return url;
	}

	// ��ȡ��ǰҳ��
	private int getPageCode(HttpServletRequest req) {

		String pageCode = req.getParameter("pageCode");
		if (pageCode == null || pageCode.trim().isEmpty()) {
			return 1;
		}
		return Integer.parseInt(pageCode);
	}

	// ɾ���û�
	public void del(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("����ɾ���û���");
		String id = req.getParameter("id");
		userService.del(id);

		findAll(req, resp);
	}

	// ��Ǯ
	public void addBalance(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("�����ֵ��");
		String addMoney = req.getParameter("recharge");
		User user = (User) req.getSession().getAttribute("user");
		double balance = user.getBalance() + Double.parseDouble(addMoney);
		user.setBalance(balance);
		userService.addBalance(user);
		req.setAttribute("msg", "��ֵ�ɹ���");
		req.getRequestDispatcher("/usercenter/online_recharge.jsp").forward(
				req, resp);
	}
}
