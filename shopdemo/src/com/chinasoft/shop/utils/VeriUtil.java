package com.chinasoft.shop.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.servlet.http.HttpSession;

public class VeriUtil {

	public static final int width = 130;
	public static final int height = 35;
	private String content = "qwertyuiopasdfghjklzxcvbnm1234567890QWERTYUIOPASDFGHJKLZXCVBNM";
	Random random = new Random();
	String veriImg4="";

	public BufferedImage getImage() {
		// 创建图片
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g = image.createGraphics();// 获取画笔
		// g.setColor(Color.gray);//把画笔颜色设置为灰色
		// 把整个图片涂成灰色
		g.fillRect(0, 0, width, height);
		
		
		for (int i = 0; i < 4; i++) {
			g.setColor(getRandomColor());
			g.setFont(new Font("宋体", Font.BOLD, 20));
			String veriImg = content.charAt(random.nextInt(content.length()))
					+ "";
			veriImg4 +=veriImg;
			g.drawString(veriImg, 10 + i * 15, 30);

		}
		System.out.println(veriImg4);

		return image;
	}

	private Color getRandomColor() {
		int red = random.nextInt(130);
		int green = random.nextInt(130);
		int blue = random.nextInt(130);
		return new Color(red, green, blue);
	}

	public String getVeriValue() {

		return veriImg4;

	}
}
