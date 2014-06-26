package org.a_sply.porter.services;

import org.a_sply.porter.dto.product.CreateProductDTO;
import org.a_sply.porter.dto.product.CreatedProductDTO;
import org.a_sply.porter.dto.product.RequestedProductDTO;

public interface ProductService {
	CreatedProductDTO create(CreateProductDTO createProductDTO);
	RequestedProductDTO get(int productId);
}
