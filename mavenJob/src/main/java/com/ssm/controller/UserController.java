package com.ssm.controller;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.jcajce.provider.digest.GOST3411.Digest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssm.pojo.User;
import com.ssm.pojo.UserException;
import com.ssm.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;

	// 个人用户注册
	@RequestMapping("person_register.action")
	public String person_register(User user, Model model) {
		// 生成id
		user.setId(UUID.randomUUID().toString().replace("-", ""));
		// 设置权限
		user.setPermission(0);
		// 密码加密（MD5）
		user.setPassword(DigestUtils.md5Hex(user.getPassword()));
		try {
			userService.register(user);
			return "users/skip";
		} catch (UserException e) {
			model.addAttribute("msg", e.getMessage());
			return "users/reg_user";
		}
	}

	// 公司注册
	@RequestMapping("com_register.action")
	public String com_register(User user, Model model) {
		String id = UUID.randomUUID().toString().replace("-", "");
		int permission = 1;
		String password = DigestUtils.md5Hex(user.getPassword());
		user.setId(id);
		user.setPermission(permission);
		user.setPassword(password);
		try {
			userService.register(user);
			return "users/login";
		} catch (UserException e) {
			model.addAttribute("msg", e.getMessage());
			return "users/reg_user";
		}
	}

	// ajxs
	@RequestMapping("checkUserName.action")
	public void checkUserName(String account, HttpServletResponse response) {
		System.out.println("进入checkUserName.action");
		User user = userService.findUserByAccount(account);
		if (user != null) {
			try {
				response.getWriter().print("该用户名已经存在");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				response.getWriter().print("该用户名可以用");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// 登录
	@RequestMapping("login.action")
	public String login(User form, String expire, Model model,
			HttpSession session, HttpServletRequest req) {
		if (expire != null) {
			Cookie cookie1 = new Cookie("account", form.getAccount());
			Cookie cookie2 = new Cookie("password", form.getPassword());
			cookie1.setMaxAge(60 * 60 * 24 * 7);
			cookie2.setMaxAge(60 * 60 * 24 * 7);
			model.addAttribute("cookie1", cookie1);
			model.addAttribute("cookie2", cookie2);
			// response.addCookie(cookie1);
			// response.addCookie(cookie2);
		}
		String password = DigestUtils.md5Hex(form.getPassword());
		form.setPassword(password);
		User user;
		try {
			user = userService.login(form);
			String lastdate=user.getLastdate();
			session.setAttribute("user", user);
			session.setAttribute("lastdate", lastdate);
			String ip = userService.getIpAddr(req);
			session.setAttribute("ip", ip);
			String str = new Date().toLocaleString();
			user.setLastdate(str);
			userService.update(user);
			return "forward:/personal/index.jsp";
		} catch (UserException e) {
			model.addAttribute("msg", e.getMessage());
			return "users/login";
		}
	}

	// 退出登录
	@RequestMapping("logout.action")
	public String logout(HttpSession session) {
		session.removeAttribute("user");
		return "index";
	}
}
