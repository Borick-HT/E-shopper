package poly.thong.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poly.thong.dao.ProductDao;
import poly.thong.entity.Product;
import poly.thong.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	ProductDao pdao;

	@Override
	public List<Product> findAll() {
		return pdao.findAll();
	}



	@Override
	public Product findById(Integer id) {
		return pdao.findById(id).get();
	}



	@Override
	public List<Product> findByCategoryId(String cid) {
		// TODO Auto-generated method stub
		return pdao.findByCategoryId(cid);
	}



	@Override
	public Product create(Product product) {
		return pdao.save(product);
	}



	@Override
	public Product update(Product product) {
		return pdao.save(product);
	}

	@Override
	public void delete(Integer id) {
			pdao.deleteById(id);		
	}



	


	

	

	



	
}
