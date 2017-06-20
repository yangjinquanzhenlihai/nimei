package com.chinasoft.shop.goods.web.servlet;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.chinasoft.shop.category.domain.Category;
import com.chinasoft.shop.category.service.CategoryService;
import com.chinasoft.shop.category.service.impl.CategoryServiceImpl;
import com.chinasoft.shop.goods.domain.Goods;
import com.chinasoft.shop.goods.service.GoodsException;
import com.chinasoft.shop.goods.service.GoodsService;
import com.chinasoft.shop.goods.service.impl.GoodsServiceImpl;

@WebServlet("/GoodsAddServlet")
public class GoodsAddServlet extends HttpServlet {

	GoodsService gs = new GoodsServiceImpl();
	CategoryService cgs= new CategoryServiceImpl();
	

	// ��Ʒ���
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("���������Ʒ��");
		// �����������
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");

		String id = null;
		// ��ǰ�˻�ȡֵ
		String name = null;
		String cid = null;// ����id
		String inventory = null;// ���
		String price = null;
		String spprice = null;
		String gAvatar = null;
		String info = null;
		String gAvatarPath = null;
		String brand = null;
		String type = null;
		String goodsNum=null;

		// ������Ӳ���ļ�����Ϊ�������Ĺ���
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// ��������request�Ľ�����
		ServletFileUpload sfu = new ServletFileUpload(factory);
		Goods goods = new Goods();
		try {
			List<FileItem> items = sfu.parseRequest(req);
			for (FileItem ft : items) {// �������б���
				if (ft.isFormField()) {// �������ͨ�������ȡ��Ӧ�ı����ֵ
					
					if (ft.getFieldName().equals("name")) {
						name = ft.getString("UTF-8");
					} else if (ft.getFieldName().equals("categoryId")) {
						cid = ft.getString("UTF-8");
					} else if (ft.getFieldName().equals("inventory")) {
						inventory = ft.getString("UTF-8");
					} else if (ft.getFieldName().equals("price")) {
						price = ft.getString("UTF-8");
					} else if (ft.getFieldName().equals("spprice")) {
						spprice = ft.getString("UTF-8");
					} else if (ft.getFieldName().equals("info")) {
						info = ft.getString("UTF-8");
					}else if (ft.getFieldName().equals("goodsNum")) {
						goodsNum=ft.getString("UTF-8");
					}

				} else {
					gAvatar = ft.getName();
					
					if (gAvatar=="") {
						List<Category> categories=cgs.findAll();
						req.setAttribute("categories", categories);
						req.setAttribute("erorrMsg","*�����ϴ�ͼƬ");
						req.getRequestDispatcher("/admin/goods_add.jsp")
						.forward(req, resp);
						return;
					}
					int index1 = gAvatar.lastIndexOf("/");
					if (index1 != 1) {// index=-1��ʾû��"/"

						gAvatar = gAvatar.substring(index1 + 1);
					}

					int index = gAvatar.lastIndexOf(".");// ��ȡ�ļ����������.���±�
					String suffix = gAvatar.substring(index + 1);// ��ȡ��׺
					suffix = suffix.toUpperCase();
					System.out.println(suffix);
					

						// ���ݺ�׺���ж��Ƿ���ͼƬ�ļ�
						if (suffix.equals("PNG") || suffix.equals("JPG")
								|| suffix.equals("JPEG")
								|| suffix.equals("GIF")) {

							String path = this.getServletContext().getRealPath(
									"/images/goods");
							System.out.println(path);
							// ʹ��hash��ɢ�ķ�ʽ���ϴ����ļ�
							int hash = gAvatar.hashCode();
							String hex = Integer.toHexString(hash);
							gAvatarPath = hex.charAt(0) + "/" + hex.charAt(1);
							File file = new File(path, gAvatarPath);
							// �����˳���·����ָ����Ŀ¼���������б��赫�����ڵĸ�Ŀ¼��
							file.mkdirs();
							ft.write(new File(path + "/" + gAvatarPath, gAvatar));

						} else {

							req.setAttribute("erorrMsg", "ֻ���ϴ�ͼƬ�����ļ�");
							req.getRequestDispatcher("/admin/goods_add.jsp")
									.forward(req, resp);
							return;
						}

				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		id = UUID.randomUUID().toString().replace("-", "");

		String[] infoAll = info.split("��");
		
		
		brand = infoAll[0];
		type = infoAll[1];
		
		try {//�����Ʒ����Ʒ�ƣ��ͺ�
			gs.checkGoods(name, brand, type);
		} catch (GoodsException e) {
			req.setAttribute("errorMsg", e.getMessage());
			req.getRequestDispatcher("/admin/goods_add.jsp").forward(req, resp);
			return;
		}

		goods.setId(id);
		goods.setName(name);
		goods.setCid(cid);

		goods.setInventory(Integer.parseInt(inventory));
		goods.setPrice(Double.parseDouble(price));
		goods.setSpprice(Double.parseDouble(spprice));
		//goods.setgAvatar(gAvatarPath + "/" + gAvatar);
		goods.setgAvatar("images/goods/"+gAvatar);
		goods.setInfo(info);
		goods.setPoint("0");
		goods.setGoodsNum(id);
		goods.setBrand(brand);
		goods.setType(type);
		goods.setGoodsNum(goodsNum);
		
		Date addTime=new Date();
		goods.setAddTime(addTime);

		req.getSession().setAttribute("goods", goods);

		gs.add(goods);

		req.setAttribute("erorrMsg", "��ӳɹ���");
		req.getRequestDispatcher("/admin/goods_add.jsp").forward(req, resp);

	}

}
