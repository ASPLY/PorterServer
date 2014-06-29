package org.a_sply.porter.exception;

public class ProductQuantityShortageException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProductQuantityShortageException() {
		super("상품 수량이 부족합니다.");
	}
}
