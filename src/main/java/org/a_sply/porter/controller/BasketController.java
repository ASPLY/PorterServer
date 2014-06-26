package org.a_sply.porter.controller;

import org.a_sply.porter.dto.article_list.ArticleListsDTO;
import org.a_sply.porter.services.BasketService;
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

//@Controller
//@RequestMapping(value = "/basket")
public class BasketController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(BasketController.class);
	
	@Autowired
	private BasketService basketService;
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void put(int articleId) {
		LOGGER.debug("put : {}", articleId);
		basketService.put(articleId);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<ArticleListsDTO> user() {
		LOGGER.debug("user");
		return new ResponseEntity<ArticleListsDTO>(basketService.findByUser(), HttpStatus.OK);
	}
}
