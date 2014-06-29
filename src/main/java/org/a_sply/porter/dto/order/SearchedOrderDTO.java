package org.a_sply.porter.dto.order;

import java.util.ArrayList;
import java.util.List;

import org.a_sply.porter.domain.item.Item;

public class SearchedOrderDTO {

	private List<RequestedOrderDTO> orders;
	
	public SearchedOrderDTO(List<Item> domainOrders) {
		orders = new ArrayList<RequestedOrderDTO>();
		
		for (Item order : domainOrders)
			orders.add(new RequestedOrderDTO(order));		
	}

	public List<RequestedOrderDTO> getOrders() {
		return orders;
	}

	public void setOrders(List<RequestedOrderDTO> orders) {
		this.orders = orders;
	}

}
