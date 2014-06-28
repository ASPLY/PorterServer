package org.a_sply.porter.domain.order;

import org.a_sply.porter.domain.User;
import org.a_sply.porter.domain.product.Product;

public class Order {
	
	private long id;
	
	private User user;
	private Product product;
	private int amount;
	
	public Order(User user, Product product, int amount) {
		this(new Long(0), user, product, amount);
	}
	
	public Order(Long id, User user, Product product, int amount) {
		this.id = id;
		this.user = user;
		this.product = product;
		this.amount = amount;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}

}
