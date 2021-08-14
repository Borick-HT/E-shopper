package poly.thong.service;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

import poly.thong.entity.Order;

public interface OrderService {

	Order create(JsonNode orderData);

	Object findById(Long id);

	List<Order> findByUsername(String username);



}
