package org.a_sply.porter.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.a_sply.porter.domain.product.MultipartImageFile;
import org.a_sply.porter.domain.product.Product;
import org.a_sply.porter.domain.product.ProductCondition;
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
@SuppressWarnings("unchecked")
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
	public ResponseEntity<?> create(@Valid Product product, MultipartImageFile multipartImageFile) {
		LOGGER.debug("create : {}", product);
		productService.create(product, multipartImageFile);
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{productId}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable long productId) {
		LOGGER.debug("get : {}", productId);
		Product product = productService.get(productId);
		if (product == null)
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> search(@Valid ProductCondition productCondition) {
		LOGGER.debug("search : {}", productCondition);
		List<Product> products = productService.search(productCondition); 
		return products == null ? 
				new ResponseEntity(HttpStatus.BAD_REQUEST) : new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
	

	@RequestMapping(value = "/count", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> count(@Valid ProductCondition productCondition) {
		LOGGER.debug("count : {}", productCondition);
		int count = productService.count(productCondition);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("count", count);
		return new ResponseEntity<Object>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/mine", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> mine(@Valid ProductCondition productCondition) {
		LOGGER.debug("mine product");
		List<Product> products = productService.getMine(productCondition); 
		return products == null ? 
				new ResponseEntity(HttpStatus.BAD_REQUEST) : new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
	
}
