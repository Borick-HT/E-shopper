package poly.thong.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import poly.thong.dao.OrderDao;
import poly.thong.dao.OrderDetailDao;
import poly.thong.entity.Order;
import poly.thong.entity.OrderDetail;
import poly.thong.service.OrderService;

@Service
public class OrderServicelmpl implements OrderService{
	@Autowired
	OrderDao odao;
	
	@Autowired
	OrderDetailDao ddao;

	@Override
	public Order create(JsonNode orderData) {
		
		  ObjectMapper mapper=new ObjectMapper();
		  
		  Order order=mapper.convertValue(orderData, Order.class); 
		  odao.save(order);
		  
		  TypeReference<List<OrderDetail>> type=new TypeReference<List<OrderDetail>>(){};
		  List<OrderDetail> details=mapper.convertValue(orderData.get("orderDetails"), type)
				  .stream().peek(d -> d.setOrder(order)).collect(Collectors.toList());
		  ddao.saveAll(details);
		  return order;
		 
		}

	@Override
	public Object findById(Long id) {
		// TODO Auto-generated method stub
		return odao.findById(id).get();
	}

	@Override
	public List<Order> findByUsername(String username) {
		// TODO Auto-generated method stub
		return odao.findByUsername(username);
	}
	}



