package org.a_sply.porter.controller;

import org.a_sply.porter.dto.article_list.ArticleListsDTO;
import org.a_sply.porter.dto.article_list.RequestArticleListsDTO;
import org.a_sply.porter.dto.article_list.SearchArticleListDTO;
import org.a_sply.porter.services.ArticleListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/articleLists")
public class ArticleListController extends BaseController {

	@Autowired
	private ArticleListService articleListService;

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> search(SearchArticleListDTO searchPartListDTO) {
		ArticleListsDTO articleListsDTO = articleListService.search(searchPartListDTO);
		return new ResponseEntity<ArticleListsDTO>(articleListsDTO,HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(RequestArticleListsDTO getArticleListsDTO) {
		ArticleListsDTO articleListsDTO = articleListService.get(getArticleListsDTO);
		return new ResponseEntity<ArticleListsDTO>(articleListsDTO,HttpStatus.OK);
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> user() {
		ArticleListsDTO articleListsDTO = articleListService.searchByUser();
		return new ResponseEntity<ArticleListsDTO>(articleListsDTO,HttpStatus.OK);
	}

	public void setPartListService(ArticleListService articleListService) {
		this.articleListService = articleListService;
	}

}
