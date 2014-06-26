package org.a_sply.porter.controller;

import javax.validation.Valid;

import org.a_sply.porter.dto.product.CreateProductDTO;
import org.a_sply.porter.dto.product.CreatedProductDTO;
import org.a_sply.porter.dto.product.RequestedProductDTO;
import org.a_sply.porter.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/products")
public class ProductController extends BaseController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	private ProductService productService;
	
	/**
	 * Process request that register a new product.
	 * @param createArticleDTO a new article information.
	 * @return a registered article content.
	 * @author LCH
	 */

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> create(@Valid CreateProductDTO createProductDTO) {
		LOGGER.debug("create : {}", createProductDTO);
		CreatedProductDTO createdProductDTO = productService.create(createProductDTO);
		return new ResponseEntity<CreatedProductDTO>(createdProductDTO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{productId}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> request(@PathVariable int productId) {
		LOGGER.debug("request : {}", productId);
		RequestedProductDTO requestedProductDTO = productService.get(productId);
		if (requestedProductDTO == null)
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<RequestedProductDTO>(requestedProductDTO, HttpStatus.OK);
	}
	

}
