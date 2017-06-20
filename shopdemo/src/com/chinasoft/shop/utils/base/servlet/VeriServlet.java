package com.chinasoft.shop.utils.base.servlet;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chinasoft.shop.utils.VeriUtil;


@WebServlet(urlPatterns = "/VeriServlet")
public class VeriServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("½øVeriServlet");
		VeriUtil vu = new VeriUtil();
		BufferedImage image = vu.getImage();
		String veriImg4=vu.getVeriValue();
		ImageIO.write(image, "JPG", resp.getOutputStream());
		
		HttpSession session=req.getSession();
		session.setAttribute("veriImg4", veriImg4);

	}

}
