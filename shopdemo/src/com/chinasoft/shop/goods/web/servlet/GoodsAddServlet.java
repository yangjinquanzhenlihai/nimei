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
	

	// 商品添加
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("进入添加商品。");
		// 解决中文乱码
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");

		String id = null;
		// 从前端获取值
		String name = null;
		String cid = null;// 分类id
		String inventory = null;// 库存
		String price = null;
		String spprice = null;
		String gAvatar = null;
		String info = null;
		String gAvatarPath = null;
		String brand = null;
		String type = null;
		String goodsNum=null;

		// 创建把硬盘文件解析为表单项对象的工厂
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 创建解析request的解析器
		ServletFileUpload sfu = new ServletFileUpload(factory);
		Goods goods = new Goods();
		try {
			List<FileItem> items = sfu.parseRequest(req);
			for (FileItem ft : items) {// 遍历所有表单项
				if (ft.isFormField()) {// 如果是普通表单项，并获取相应的表单项的值
					
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
						req.setAttribute("erorrMsg","*必须上传图片");
						req.getRequestDispatcher("/admin/goods_add.jsp")
						.forward(req, resp);
						return;
					}
					int index1 = gAvatar.lastIndexOf("/");
					if (index1 != 1) {// index=-1表示没有"/"

						gAvatar = gAvatar.substring(index1 + 1);
					}

					int index = gAvatar.lastIndexOf(".");// 获取文件名称中最后.的下标
					String suffix = gAvatar.substring(index + 1);// 截取后缀
					suffix = suffix.toUpperCase();
					System.out.println(suffix);
					

						// 根据后缀名判断是否是图片文件
						if (suffix.equals("PNG") || suffix.equals("JPG")
								|| suffix.equals("JPEG")
								|| suffix.equals("GIF")) {

							String path = this.getServletContext().getRealPath(
									"/images/goods");
							System.out.println(path);
							// 使用hash打散的方式，上传该文件
							int hash = gAvatar.hashCode();
							String hex = Integer.toHexString(hash);
							gAvatarPath = hex.charAt(0) + "/" + hex.charAt(1);
							File file = new File(path, gAvatarPath);
							// 创建此抽象路径名指定的目录，包括所有必需但不存在的父目录。
							file.mkdirs();
							ft.write(new File(path + "/" + gAvatarPath, gAvatar));

						} else {

							req.setAttribute("erorrMsg", "只能上传图片类型文件");
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

		String[] infoAll = info.split("，");
		
		
		brand = infoAll[0];
		type = infoAll[1];
		
		try {//检查商品名，品牌，型号
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

		req.setAttribute("erorrMsg", "添加成功！");
		req.getRequestDispatcher("/admin/goods_add.jsp").forward(req, resp);

	}

}
