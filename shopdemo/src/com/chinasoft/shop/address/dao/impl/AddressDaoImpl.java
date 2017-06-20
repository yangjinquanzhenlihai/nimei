package com.chinasoft.shop.address.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;

import com.chinasoft.shop.address.domain.Address;
import com.chinasoft.shop.utils.JdbcUtil;
import com.chinasoft.shop.utils.base.dao.impl.BaseDaoImpl;

public class AddressDaoImpl extends BaseDaoImpl<Address> {

	QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
	public void cancelSelected(String uid){//取消默认
		
		String sql="UPDATE address SET selected=0 WHERE uid=?";
		
		Object[] params={uid};
		
		try {
			qr.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	public void setSelected(String id) {//设置默认
		String sql="UPDATE address SET selected=1 WHERE id=?";
		
		Object[] params={id};
		
		try {
			qr.update(sql,params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
    
	
}
