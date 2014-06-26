package org.a_sply.porter.services;

import org.a_sply.porter.dto.article_list.ArticleListsDTO;

public interface BasketService {
	
	void put(int articleId);
	ArticleListsDTO findByUser();
	
}
