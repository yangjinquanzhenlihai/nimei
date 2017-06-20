package com.chinasoft.shop.goods.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.chinasoft.shop.goods.domain.Goods;
import com.chinasoft.shop.goods.domain.QueryGoods;
import com.chinasoft.shop.utils.JdbcUtil;
import com.chinasoft.shop.utils.base.dao.impl.BaseDaoImpl;
import com.chinasoft.shop.utils.domain.PageBean;

public class GoodsDaoImpl extends BaseDaoImpl<Goods> {

	QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());

	public void addGoods(Goods goods) {
		String sql = "INSERT INTO goods VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] params = { goods.getId(), goods.getName(), goods.getCid(),
				goods.getPrice(), goods.getInventory(), goods.getSpprice(),
				goods.getBrand(), goods.getType(), goods.getPoint(),
				goods.getInfo(), goods.getSellCount(), goods.getgAvatar(),
				goods.getGoodsNum(), goods.getAddTime().toLocaleString() };

		try {
			qr.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public PageBean<Goods> findAllGoods(int pageSize, int pageCode) {

		PageBean<Goods> pb = new PageBean<Goods>();
		pb.setPageSize(pageSize);
		pb.setPageCode(pageCode);

		String sql = "SELECT COUNT(*) FROM goods";
		try {

			Number totalRecord = (Number) qr.query(sql, new ScalarHandler());
			sql = "SELECT * FROM goods LIMIT ?,?";
			Object[] params = { (pageCode - 1) * pageSize, pageSize };
			List<Goods> beanList = qr.query(sql, params,
					new BeanListHandler<Goods>(Goods.class));
			pb.setBeanList(beanList);
			pb.setTotalRecord(totalRecord.intValue());
		} catch (SQLException e) {

			throw new RuntimeException(e);
		}
		return pb;

	}

	public PageBean<Goods> findAll(QueryGoods queryGoods, int pageSize,
			int pageCode) {
		
		StringBuffer sql = new StringBuffer("SELECT * FROM goods WHERE 1=1");
		StringBuffer sql1 = new StringBuffer(
				"SELECT COUNT(*) FROM goods WHERE 1=1");
		String cid = queryGoods.getCid();
		System.out.println(cid);
		String name = queryGoods.getName();
		double spprice1 = queryGoods.getSpprice1();
		double spprice2 = queryGoods.getSpprice2();

		PageBean<Goods> pb = new PageBean<Goods>();
		List<Object> list = new ArrayList<Object>();
		List<Object> list1 = new ArrayList<Object>();

		if (cid != null && !cid.trim().isEmpty()) {

			sql.append(" AND cid LIKE ?");
			sql1.append(" AND cid LIKE ?");
			list.add("%" + cid + "%");
			list1.add("%" + cid + "%");
			System.out.println(sql+"111111111");
		}
		if (name != null && !name.trim().isEmpty()) {

			sql.append(" AND name=?");
			sql1.append(" AND name=?");
			list.add(name);
			list1.add(name);

		}
		if (spprice1 != 0 || spprice2 != 0) {

			sql.append(" AND spprice BETWEEN ? AND ?");
			sql1.append(" AND spprice BETWEEN ? AND ?");
			list.add(spprice1);
			list1.add(spprice2);
		}

		List<Goods> beanList1 = null;
		try {
			beanList1 = qr.query(sql.toString(), list.toArray(),
					new BeanListHandler<Goods>(Goods.class));
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		sql.append(" LIMIT ?,?");

		// sql1.append(" LIMIT ?,?");
		list.add((pageCode - 1) * pageSize);
		list.add(pageSize);
		// list1.add((pageCode - 1) * pageSize);
		// list1.add(pageSize);

		try {
			Number totalRecord = (Number) qr.query(sql1.toString(),
					list1.toArray(), new ScalarHandler());
			pb.setTotalRecord(totalRecord.intValue());
		} catch (SQLException e1) {
			throw new RuntimeException(e1);
		}

		List<Goods> beanList = null;
		try {
			beanList = qr.query(sql.toString(), list.toArray(),
					new BeanListHandler<Goods>(Goods.class));

			pb.setBeanList(beanList);
			pb.setPageCode(pageCode);
			pb.setPageSize(pageSize);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return pb;
	}



	public List<Goods> findHotGoods() {
		 String sql = "SELECT *	FROM goods ORDER BY sellCount DESC LIMIT 0,8";
		 List<Goods> hotList=null;
			try {
				hotList = qr.query(sql, new BeanListHandler<Goods>(Goods.class));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return hotList ;
		}
	

	public List<Goods> findNewGoods() {
		String sql="SELECT * FROM goods ORDER BY addTime DESC LIMIT 0,8";
		List<Goods> newList=null;
		try {
			newList = qr.query(sql, new BeanListHandler<Goods>(Goods.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return newList ;
	}

	public List<Goods> findHotGoodsByCid(String cid) {
		String sql = "SELECT *	FROM goods WHERE cid=? ORDER BY sellCount DESC LIMIT 0,8";
		Object[] params={cid};
		List<Goods> hotList=null;
		try {
			hotList=qr.query(sql, params, new BeanListHandler<Goods>(Goods.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return hotList;
	}

	public List<Goods> findNewGoodsByCid(String cid) {
		String sql="SELECT * FROM goods WHERE cid=? ORDER BY addTime DESC LIMIT 0,8";
		Object[] params={cid};
		List<Goods> newList=null;
		try {
			newList=qr.query(sql, params, new BeanListHandler<Goods>(Goods.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return newList;
	
	}

}
