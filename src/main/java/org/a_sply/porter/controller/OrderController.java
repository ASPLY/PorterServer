package org.a_sply.porter.controller;

import org.a_sply.porter.dto.order.AddOrderDTO;
import org.a_sply.porter.dto.order.AddedOrderDTO;
import org.a_sply.porter.dto.order.SearchedOrderDTO;
import org.a_sply.porter.services.OrderService;
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
@RequestMapping(value = "/Orders")
public class OrderController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	private OrderService ordersService;
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<AddedOrderDTO> add(AddOrderDTO addOrderDTO) {
		LOGGER.debug("add : {}", addOrderDTO);
		AddedOrderDTO addedOrderDTO = ordersService.add(addOrderDTO);
		return new ResponseEntity<AddedOrderDTO>(addedOrderDTO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/mine", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<SearchedOrderDTO> mine() {
		LOGGER.debug("mine orders");
		SearchedOrderDTO searchedOrderDTO = ordersService.search();
		return  searchedOrderDTO == null ? 
				new ResponseEntity<SearchedOrderDTO>(HttpStatus.BAD_REQUEST) :
				new ResponseEntity<SearchedOrderDTO>(searchedOrderDTO, HttpStatus.OK);
	}
}
