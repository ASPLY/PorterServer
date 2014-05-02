package org.a_sply.porter.services;

import org.a_sply.porter.dto.article_list.ArticleListsDTO;
import org.a_sply.porter.dto.article_list.RequestArticleListsDTO;
import org.a_sply.porter.dto.article_list.SearchArticleListDTO;

public interface ArticleListService {
	ArticleListsDTO search(SearchArticleListDTO searchPartListDTO);
	ArticleListsDTO searchByUser();
	ArticleListsDTO get(RequestArticleListsDTO getArticleListsDTO);
}
