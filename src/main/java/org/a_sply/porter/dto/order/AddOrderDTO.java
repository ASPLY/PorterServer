package org.a_sply.porter.dto.order;


public class AddOrderDTO {
	
	private long productId;
	private int amount;
	
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public int getAmount() {
		return amount;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
}