package com.chinasoft.shop.utils;

/**
 * 发送邮件
 * 1.获取session
 * 2.获取邮件对象(MimeMessage)，并设置相应属性
 * 3.发送邮件：TransPort.send(msg)
 * @ClassName: MailUtil 
 * @Description: TODO
 * @author: 李浩如
 * @date: 2017年5月8日 下午5:08:39
 */

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

/**
 * 发送邮件： 1.获取session对象 2.获取邮件对象(MimeMessage),并设置相应的属性
 * 3.发送邮件：TransPort.send(msg);
 * 
 * 
 */
public class MailUtil {
	public static void sendMail(String to, String token) {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.163.com");// 配置发件服务器
		props.put("mail.smtp.auth", "true");// 设置该邮箱需要验证
		Authenticator authenticator = new Authenticator() {
			// 验证用户名、密码
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("15002821218", "gao123");
			}
		};

		// 获取Session对象
		Session session = Session.getInstance(props, authenticator);
		MimeMessage msg = new MimeMessage(session);
		try {
			msg.setFrom(new InternetAddress("15002821218@163.com"));
			msg.setRecipient(RecipientType.TO, new InternetAddress(to));
			msg.setSubject("激活邮件");
			msg.setContent(
					"<a href=\"http://localhost:8080/askdemo/UserServlet?opr=activate&token="
							+ token
							+ "\">点击激活http://localhost:8080/shopdemo/UserServlet?opr=activate&token="
							+ token + "</a>", "text/html;charset=utf-8");
			// 发送邮件
			Transport.send(msg);
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		sendMail("1910215465@qq.com", "sdfdfsd");
		System.out
				.println("<a href=\"http://localhost:8080/shopdemo/UserServlet?opr=activate&token="
						+ 1111 + "\">点击激活</a>");
	}
}