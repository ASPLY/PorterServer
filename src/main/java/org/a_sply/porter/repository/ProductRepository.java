package org.a_sply.porter.repository;

import org.a_sply.porter.domain.product.Product;

public interface ProductRepository {
	long insert(Product product);
	Product selectById(int productId);
}
