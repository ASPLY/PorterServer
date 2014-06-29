package org.a_sply.porter.dao.interfaces;

import java.util.List;

import org.a_sply.porter.domain.product.Product;
import org.a_sply.porter.domain.product.ProductCondition;

public interface ProductDao {
	
	long insert(Product product);
	Product selectById(SelectType selectType, long productId);
	List<Product> selectByCondition(SelectType selectType, ProductCondition productCondition);
	int countByCondition(ProductCondition productCondition);
}
