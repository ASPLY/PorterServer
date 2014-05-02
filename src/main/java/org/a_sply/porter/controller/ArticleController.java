package org.a_sply.porter.controller;

import javax.validation.Valid;

import org.a_sply.porter.dto.article.ArticleDTO;
import org.a_sply.porter.dto.article.CreateArticleDTO;
import org.a_sply.porter.dto.article.CreatedArticleDTO;
import org.a_sply.porter.services.ArticleService;
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

@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("/articles")
public class ArticleController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ArticleController.class);

	@Autowired
	private ArticleService articleService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable int id) {
		ArticleDTO requestArticleDTO = articleService.get(id);
		if (requestArticleDTO == null)
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<ArticleDTO>(requestArticleDTO, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> create(@Valid CreateArticleDTO createArticleDTO) {
		LOGGER.debug("Received createArticleDTO : {}", createArticleDTO);
		CreatedArticleDTO createdArticleDTO = articleService.create(createArticleDTO);
		return new ResponseEntity<CreatedArticleDTO>(createdArticleDTO, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}/sold", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> sold(@PathVariable int id) {
		if (articleService.sold(id))
			return new ResponseEntity(HttpStatus.OK);
		else
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
	}

	public void setArticleService(ArticleService articleService) {
		this.articleService = articleService;
	}
}
