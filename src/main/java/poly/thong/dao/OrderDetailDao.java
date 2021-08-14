package poly.thong.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import poly.thong.entity.OrderDetail;

public interface OrderDetailDao extends JpaRepository<OrderDetail, Long>{

}
