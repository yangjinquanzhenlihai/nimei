package com.chinasoft.shop.category.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.chinasoft.shop.category.dao.impl.CategoryDaoImpl;
import com.chinasoft.shop.category.domain.Category;
import com.chinasoft.shop.category.service.CategoryException;
import com.chinasoft.shop.category.service.CategoryService;
import com.chinasoft.shop.utils.base.dao.BaseDao;


public class CategoryServiceImpl implements CategoryService {

	BaseDao<Category> cgd = new CategoryDaoImpl();
	

	@Override
	public void add(Category cg) {
		try {
			cgd.add(cg);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Category> findAll() {
		
		return cgd.findAll();
	}

	@Override
	public void del(String id) {
		
		cgd.delete(id);
	}

	@Override
	public void findByName(String name) throws CategoryException {
		
		Category category=cgd.findByName(name);
		if (category!=null) {
			throw new CategoryException("该分类已存在。");
		}	
	}
}
