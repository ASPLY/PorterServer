package org.a_sply.porter.repository;

import java.util.List;

import org.a_sply.porter.domain.ArticleList;

public interface BasketRepository {

	void save(int userId, int articleId);
	List<ArticleList> findByUserId(int id);

}
