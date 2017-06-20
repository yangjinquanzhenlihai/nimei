package com.chinasoft.shop.utils.base.dao.impl;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.chinasoft.shop.utils.JdbcUtil;
import com.chinasoft.shop.utils.base.dao.BaseDao;


public class BaseDaoImpl<T> implements BaseDao<T> {
	QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
	Class<T> clazz = null; // ���͵������
	String table = null;// ���ݿ���б�����

	public BaseDaoImpl() {
		// this.getClss() :�õ����������� ; getGenericSuperclass():��ȡ����Ĵ����͵������
		ParameterizedType type = (ParameterizedType) this.getClass()
				.getGenericSuperclass();
		// ��ȡ���еķ��͵�����
		Type[] types = type.getActualTypeArguments();
		this.clazz = (Class<T>) types[0];
		this.table = clazz.getSimpleName().toLowerCase();
	}

	@Override
	public void add(T t){
		// getClass() ��ȡ�����
		StringBuilder sql = new StringBuilder("INSERT " + table + " VALUES(");
		// ��ȡ��������
		Field[] fields = t.getClass().getDeclaredFields();
		
		List params = new ArrayList();
		// field����ÿһ�����Զ��󣺰������������������Ե�ֵ	
			try {
				for (Field field : fields) {
					sql.append("?,");
					field.setAccessible(true);
					// field.get(������); ʹ��ָ�������ȡ������					
//					if (field.get(t) instanceof Date) {
//						params.add(((Date)field.get(t)).toLocaleString());
//					}
					params.add(field.get(t));
				
				}
				sql.deleteCharAt(sql.length() - 1);
				sql.append(")");
				qr.update(sql.toString(), params.toArray());
			} catch (SecurityException e) {
				
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				
				e.printStackTrace();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
	}

	@Override
	public void delete(String id) {
		String sql = "DELETE FROM " + table + " WHERE id =?";
		Object[] params = { id };
		try {
			qr.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void update(T t) {
		StringBuilder sql = new StringBuilder("UPDATE " + table + " SET ");
		Field[] fields = clazz.getDeclaredFields();
		List params = new ArrayList();
		try {
			for (int i = 1; i < fields.length; i++) {
				fields[i].setAccessible(true);
				String fieldName = fields[i].getName();
				sql.append(fieldName + "=?,");
				params.add(fields[i].get(t));
			}
			sql.deleteCharAt(sql.length() - 1);
			sql.append(" WHERE " + fields[0].getName() + "=?");
			fields[0].setAccessible(true);
			params.add(fields[0].get(t));
			qr.update(sql.toString(), params.toArray());
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<T> findAll() {
		String sql = "SELECT * FROM "+table;
		try {
			return qr.query(sql,new BeanListHandler<T>(clazz));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public T findById(String id) {
		String sql = "SELECT * FROM "+table+" WHERE id=?";
		Object[] params = {id};
		try {
			return qr.query(sql, params, new BeanHandler<T>(clazz));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void test(T t) throws InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		Constructor[] cons = t.getClass().getConstructors();
		Constructor<T> con = cons[0];
		T t2 = con.newInstance(t);

	}

	@Override
	public T findByName(String name) {
		String sql="SELECT * FROM "+table+" WHERE name=?";
		Object[] params={name};
		
		try {
			
			return qr.query(sql, params, new BeanHandler<T>(clazz));
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	@Override
	public T findByToken(String token) {
		String sql = "SELECT * FROM "+table+" WHERE token=?";
		Object[] params = {token};
		try {
			return qr.query(sql, params, new BeanHandler<T>(clazz));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public T checkName(String name) {//ͨ���û�������
		String sql="SELECT * FROM "+table+" WHERE name=?";
		Object[] params={name};
		try {
			return qr.query(sql, params, new BeanHandler<T>(clazz));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<T> findAllByUid(String uid) {
		String sql="SELECT * FROM "+table+" WHERE uid=?";
		Object[] params={uid};
		
		try {
			return qr.query(sql, params, new BeanListHandler<T>(clazz));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

}
