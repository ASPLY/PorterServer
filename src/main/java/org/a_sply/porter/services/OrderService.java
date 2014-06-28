package org.a_sply.porter.services;

import org.a_sply.porter.dto.order.AddOrderDTO;
import org.a_sply.porter.dto.order.AddedOrderDTO;
import org.a_sply.porter.dto.order.SearchedOrderDTO;

public interface OrderService {
	
	AddedOrderDTO add(AddOrderDTO addOrderDTO);
	SearchedOrderDTO search();
	
}
