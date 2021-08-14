package poly.thong.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poly.thong.dao.CategoryDao;
import poly.thong.entity.Category;
import poly.thong.service.CategoryService;

@Service
public class CategoryServicempl implements CategoryService{
	@Autowired
	CategoryDao cdao;
	@Override
	public List<Category> findAll() {
		return cdao.findAll();
	}

}
