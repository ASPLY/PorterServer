package org.a_sply.porter.domain.item;

import java.util.List;

public class Cart {
	
	private List<Item> items;
	private double total;
	
	public Cart(List<Item> items) {
		super();
		this.items = items;
		this.total = 0;
		for (Item item : items) 
			total = +item.cost();
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	
}
