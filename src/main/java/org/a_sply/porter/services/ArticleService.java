package org.a_sply.porter.services;

import org.a_sply.porter.dto.article.ArticleDTO;
import org.a_sply.porter.dto.article.CreateArticleDTO;
import org.a_sply.porter.dto.article.CreatedArticleDTO;

public interface ArticleService {

	ArticleDTO get(int id);
	CreatedArticleDTO create(CreateArticleDTO createArticleDTO);
	boolean sold(int id);

}
