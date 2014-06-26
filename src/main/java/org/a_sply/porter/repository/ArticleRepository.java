package org.a_sply.porter.repository;

import org.a_sply.porter.domain.Article;

public interface ArticleRepository {

	Article save(Article article);

	Article get(int id);

	void sold(int id);

}
