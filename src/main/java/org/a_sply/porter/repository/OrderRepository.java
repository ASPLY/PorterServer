package org.a_sply.porter.repository;

import java.util.List;

import org.a_sply.porter.domain.order.Order;
import org.a_sply.porter.domain.order.OrderCondition;


public interface OrderRepository {

	long insert(Order order);
	List<Order> selectByCondition(OrderCondition orderCondition);

}
