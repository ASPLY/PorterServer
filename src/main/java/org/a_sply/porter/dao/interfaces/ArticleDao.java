package org.a_sply.porter.dao.interfaces;

import org.a_sply.porter.domain.Article;

public interface ArticleDao {

	Article save(Article article);

	Article get(int id);

	void sold(int id);

}
