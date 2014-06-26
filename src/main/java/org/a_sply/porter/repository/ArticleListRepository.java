package org.a_sply.porter.repository;

import java.util.List;

import org.a_sply.porter.domain.ArticleList;
import org.a_sply.porter.domain.RequestArticleLists;
import org.a_sply.porter.domain.Search;

public interface ArticleListRepository {

	List<ArticleList> search(Search search);

	List<ArticleList> searchByEmail(String email);

	List<ArticleList> get(RequestArticleLists getArticleLists);

}
