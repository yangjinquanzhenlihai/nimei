package com.chinasoft.shop.user.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.chinasoft.shop.goods.domain.Goods;
import com.chinasoft.shop.user.domain.User;
import com.chinasoft.shop.utils.JdbcUtil;
import com.chinasoft.shop.utils.base.dao.impl.BaseDaoImpl;
import com.chinasoft.shop.utils.domain.PageBean;

public class UserDaoImpl extends BaseDaoImpl<User>{
	QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());

public PageBean<User> findAllGoods(int pageSize, int pageCode){
		
		PageBean<User> pb = new PageBean<User>();
		pb.setPageSize(pageSize);
		pb.setPageCode(pageCode);

		String sql = "SELECT COUNT(*) FROM user";
		try {
			
			Number totalRecord = (Number) qr.query(sql, new ScalarHandler());
			sql = "SELECT * FROM user LIMIT ?,?";
			Object[] params = { (pageCode - 1) * pageSize, pageSize };
			List<User> beanList = qr.query(sql, params,
					new BeanListHandler<User>(User.class));
			pb.setBeanList(beanList);
			pb.setTotalRecord(totalRecord.intValue());
		} catch (SQLException e) {

			throw new RuntimeException(e);
		}
		return pb;
		
	}
	
}
