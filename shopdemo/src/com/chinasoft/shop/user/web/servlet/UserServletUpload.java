package com.chinasoft.shop.user.web.servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.chinasoft.shop.user.domain.User;
import com.chinasoft.shop.user.service.UserService;
import com.chinasoft.shop.user.service.impl.UserServiceImpl;

@WebServlet("/UserServletUpload")
public class UserServletUpload extends HttpServlet{
	UserService userService = new UserServiceImpl();
//	修改图片
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		User user = (User) req.getSession().getAttribute("user");
		String avatar=null;
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload sfu = new ServletFileUpload(factory);
		try {
			List<FileItem> items = sfu.parseRequest(req);
			for (FileItem item : items) {
				if (!item.isFormField()) {

					avatar = item.getName();//获取上传文件的名称
					int index = avatar.lastIndexOf(".");//获取文件名称中.的下标
					String suffix = avatar.substring(index + 1);//.的下标值，截取文件的后缀名
					suffix = suffix.toUpperCase();
					if (suffix!=null) {
						if (suffix.equals("PNG") || suffix.equals("JPG")
								|| suffix.equals("JPEG")||suffix.equals("GIF")) {//根据后缀名判断是否是图片文件
							//使用hash打散的方式，上传该文件
							String path = this.getServletContext().getRealPath(
									"/images/user");
//							int hash = avatar.hashCode();
//							String hex = Integer.toHexString(hash);
//							String imagePath =  hex.charAt(0) + "/"+ hex.charAt(1);
							File file = new File(path);
							file.mkdir();
							item.write(new File(path , avatar));
					}
					} else {
						req.setAttribute("errorMsg", "亲，文件类型只能是图片！！！");
						req.getRequestDispatcher("/usercenter/user_avatar.jsp").forward(req, resp);
						return;
					}
				}
			}
			user.setAvatar("images/user/"+avatar);
			userService.update(user);
			resp.sendRedirect(req.getContextPath()+"/usercenter/index.jsp");
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}