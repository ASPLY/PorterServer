package org.a_sply.porter.domain.item;

import org.a_sply.porter.domain.base.Named;
import org.a_sply.porter.domain.product.Product;


public class Item extends Named{
	
	private long itemId;
	public static final String ITEM_ID = "itemId";
	public static final String NAMED_ITEM_ID = named(ITEM_ID);
	
	private long userId;
	public static final String USER_ID = "userId";
	public static final String NAMED_USER_ID = named(USER_ID);
	
	private long productId;
	public static final String PRODUCT_ID = "productId";
	public static final String NAMED_PRODUCT_ID = named(PRODUCT_ID);
	
	private int quantity;
	public static final String QUANTITY = "quantity";
	public static final String NAMED_QUANTITY = named(QUANTITY);
	
	private Product product;
	
	public long getItemId() {
		return itemId;
	}
	public void setItemId(long itemId) {
		this.itemId = itemId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
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
