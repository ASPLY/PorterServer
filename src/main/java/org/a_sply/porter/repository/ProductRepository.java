package org.a_sply.porter.repository;

import java.util.List;

import org.a_sply.porter.domain.product.Product;
import org.a_sply.porter.domain.product.ProductCondition;

public interface ProductRepository {
	long insert(Product product);
	Product selectById(long id);
	List<Product> selectByCondition(ProductCondition productCondition);
	int countByCondition(ProductCondition productCondition);
}
