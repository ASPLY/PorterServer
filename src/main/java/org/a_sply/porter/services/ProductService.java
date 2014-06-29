package org.a_sply.porter.services;

import java.util.List;

import org.a_sply.porter.domain.product.MultipartImageFile;
import org.a_sply.porter.domain.product.Product;
import org.a_sply.porter.domain.product.ProductCondition;

public interface ProductService {
	void create(Product product, MultipartImageFile multipartImageFile);
	Product get(long productId);
	List<Product> getMine(ProductCondition productCondition);
	List<Product> search(ProductCondition productCondition);
	int count(ProductCondition productCondition);
}
