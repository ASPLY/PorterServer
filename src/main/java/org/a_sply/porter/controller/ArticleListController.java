package org.a_sply.porter.controller;

import org.a_sply.porter.dto.article_list.ArticleListsDTO;
import org.a_sply.porter.dto.article_list.RequestArticleListsDTO;
import org.a_sply.porter.dto.article_list.SearchArticleListDTO;
import org.a_sply.porter.services.ArticleListService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Process request related to Article list 
 * @author LCH
 */

@Controller
@RequestMapping("/articleLists")
public class ArticleListController extends BaseController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ArticleListController.class);

	@Autowired
	private ArticleListService articleListService;

	/**
	 * Process request that find a article content.
	 * @param searchPartListDTO searching condition.
	 * @return a article list.
	 * @author LCH
	 */
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> search(SearchArticleListDTO searchPartListDTO) {
		LOGGER.debug("search : {}", searchPartListDTO);
		ArticleListsDTO articleListsDTO = articleListService.search(searchPartListDTO);
		return new ResponseEntity<ArticleListsDTO>(articleListsDTO,HttpStatus.OK);
	}
	
	/**
	 * Process request that get a article content.
	 * @param requestArticleListsDTO getting condition.
	 * @return a article list.
	 * @author LCH
	 */

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(RequestArticleListsDTO requestArticleListsDTO) {
		LOGGER.debug("get : {}", requestArticleListsDTO);
		ArticleListsDTO articleListsDTO = articleListService.get(requestArticleListsDTO);
		return new ResponseEntity<ArticleListsDTO>(articleListsDTO,HttpStatus.OK);
	}
	
	/**
	 * Process request that get user owned the articles.
	 * @return a article list.
	 * @author LCH
	 */

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> user() {
		LOGGER.debug("user");
		ArticleListsDTO articleListsDTO = articleListService.searchByUser();
		return new ResponseEntity<ArticleListsDTO>(articleListsDTO,HttpStatus.OK);
	}

	public void setPartListService(ArticleListService articleListService) {
		this.articleListService = articleListService;
	}

}
