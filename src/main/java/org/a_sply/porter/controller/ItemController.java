package org.a_sply.porter.controller;

import org.a_sply.porter.domain.item.Cart;
import org.a_sply.porter.domain.item.Item;
import org.a_sply.porter.services.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping(value = "/items")
public class ItemController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ItemController.class);
	
	@Autowired
	private ItemService itemService;
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void add(Item item) {
		LOGGER.debug("add : {}", item);
		itemService.add(item);
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void remove(long[] itemIds) {
		LOGGER.debug("remove : {}", itemIds);
		itemService.remove(itemIds);
	}
	
	
	@RequestMapping(value = "/mine", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Cart> mine() {
		LOGGER.debug("mine orders");
		Cart cart = itemService.getMine();
		return  cart == null ? 
				new ResponseEntity<Cart>(HttpStatus.BAD_REQUEST) :
				new ResponseEntity<Cart>(cart, HttpStatus.OK);
	}
}
