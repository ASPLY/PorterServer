package org.a_sply.porter.services;

import org.a_sply.porter.dto.product.CountProductDTO;
import org.a_sply.porter.dto.product.CountedProductDTO;
import org.a_sply.porter.dto.product.CreateProductDTO;
import org.a_sply.porter.dto.product.CreatedProductDTO;
import org.a_sply.porter.dto.product.RequestProductDTO;
import org.a_sply.porter.dto.product.RequestedProductDTO;
import org.a_sply.porter.dto.product.SearchProductDTO;
import org.a_sply.porter.dto.product.SearchedProductDTO;

public interface ProductService {
	CreatedProductDTO create(CreateProductDTO createProductDTO);
	RequestedProductDTO request(RequestProductDTO requestProductDTO);
	SearchedProductDTO search();
	SearchedProductDTO search(SearchProductDTO searchProductDTO);
	CountedProductDTO count(CountProductDTO countProductDTO);
}
