package com.chinasoft.shop.utils;

/**
 * �����ʼ�
 * 1.��ȡsession
 * 2.��ȡ�ʼ�����(MimeMessage)����������Ӧ����
 * 3.�����ʼ���TransPort.send(msg)
 * @ClassName: MailUtil 
 * @Description: TODO
 * @author: �����
 * @date: 2017��5��8�� ����5:08:39
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
 * �����ʼ��� 1.��ȡsession���� 2.��ȡ�ʼ�����(MimeMessage),��������Ӧ������
 * 3.�����ʼ���TransPort.send(msg);
 * 
 * 
 */
public class MailUtil {
	public static void sendMail(String to, String token) {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.163.com");// ���÷���������
		props.put("mail.smtp.auth", "true");// ���ø�������Ҫ��֤
		Authenticator authenticator = new Authenticator() {
			// ��֤�û���������
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("15002821218", "gao123");
			}
		};

		// ��ȡSession����
		Session session = Session.getInstance(props, authenticator);
		MimeMessage msg = new MimeMessage(session);
		try {
			msg.setFrom(new InternetAddress("15002821218@163.com"));
			msg.setRecipient(RecipientType.TO, new InternetAddress(to));
			msg.setSubject("�����ʼ�");
			msg.setContent(
					"<a href=\"http://localhost:8080/askdemo/UserServlet?opr=activate&token="
							+ token
							+ "\">�������http://localhost:8080/shopdemo/UserServlet?opr=activate&token="
							+ token + "</a>", "text/html;charset=utf-8");
			// �����ʼ�
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
						+ 1111 + "\">�������</a>");
	}
}