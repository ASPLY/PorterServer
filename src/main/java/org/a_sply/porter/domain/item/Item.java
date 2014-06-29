package org.a_sply.porter.domain.item;

import org.a_sply.porter.domain.product.Product;


public class Item {
	
	private long itemId;
	
	private int userId;
	
	private long productId;
	
	private Product product;
	private int quantity;
	
	public long getItemId() {
		return itemId;
	}
	public void setItemId(long itemId) {
		this.itemId = itemId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
	public double cost() {
		return product.getPrice() * quantity;
	}
}
