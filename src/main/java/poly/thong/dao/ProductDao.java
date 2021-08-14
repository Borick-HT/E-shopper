package poly.thong.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import poly.thong.entity.Product;

public interface ProductDao extends JpaRepository<Product, Integer> {
	@Query("SELECT p from Product p WHERE p.category.id=?1")
	List<Product> findByCategoryId(String cid);

	
	

	

	
}
