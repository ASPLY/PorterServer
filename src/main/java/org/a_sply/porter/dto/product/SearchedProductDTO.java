package org.a_sply.porter.dto.product;

import java.util.ArrayList;
import java.util.List;

import org.a_sply.porter.domain.product.Product;

public class SearchedProductDTO {
	
	private List<RequestedProductDTO> products;
	
	public SearchedProductDTO(){}

	public SearchedProductDTO(List<Product> domainProducts) {
		List<RequestedProductDTO> requestedProductDTOs = new ArrayList<RequestedProductDTO>();
		
		for (Product product : domainProducts) 
			requestedProductDTOs.add(new RequestedProductDTO(product));
		
		this.products = requestedProductDTOs;
	}

	public List<RequestedProductDTO> getProducts() {
		return products;
	}

	public void setProducts(List<RequestedProductDTO> products) {
		this.products = products;
	}
	
}
