package com.chinasoft.shop.category.service;

import java.util.List;

import com.chinasoft.shop.category.domain.Category;

public interface CategoryService {

	void add(Category cg);

	List<Category> findAll();

	void del(String id);

	void findByName(String name) throws CategoryException;

}
