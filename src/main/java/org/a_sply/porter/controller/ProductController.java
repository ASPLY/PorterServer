package org.a_sply.porter.controller;

import javax.validation.Valid;

import org.a_sply.porter.dto.product.CountProductDTO;
import org.a_sply.porter.dto.product.CountedProductDTO;
import org.a_sply.porter.dto.product.CreateProductDTO;
import org.a_sply.porter.dto.product.CreatedProductDTO;
import org.a_sply.porter.dto.product.RequestProductDTO;
import org.a_sply.porter.dto.product.RequestedProductDTO;
import org.a_sply.porter.dto.product.SearchProductDTO;
import org.a_sply.porter.dto.product.SearchedProductDTO;
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
	public ResponseEntity<?> request(@PathVariable long productId) {
		LOGGER.debug("request : {}", productId);
		RequestedProductDTO requestedProductDTO = productService.request(new RequestProductDTO(productId));
		if (requestedProductDTO == null)
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<RequestedProductDTO>(requestedProductDTO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> search(@Valid SearchProductDTO searchProductDTO) {
		LOGGER.debug("search : {}", searchProductDTO);
		SearchedProductDTO searchedProductDTO = productService.search(searchProductDTO); 
		return searchedProductDTO == null ? 
				new ResponseEntity(HttpStatus.BAD_REQUEST) : new ResponseEntity<SearchedProductDTO>(searchedProductDTO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/count", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> count(@Valid CountProductDTO countProductDTO) {
		LOGGER.debug("count : {}", countProductDTO);
		CountedProductDTO countedProductDTO = productService.count(countProductDTO);
		return new ResponseEntity<CountedProductDTO>(countedProductDTO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/mine", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> mine() {
		LOGGER.debug("mine product");
		SearchedProductDTO searchedProductDTO = productService.search(); 
		return searchedProductDTO == null ? 
				new ResponseEntity(HttpStatus.BAD_REQUEST) : new ResponseEntity<SearchedProductDTO>(searchedProductDTO, HttpStatus.OK);
	}
	
}
