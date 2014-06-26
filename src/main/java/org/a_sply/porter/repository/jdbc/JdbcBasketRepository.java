package org.a_sply.porter.repository.jdbc;

import java.util.List;

import org.a_sply.porter.domain.ArticleList;
import org.a_sply.porter.repository.BasketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcBasketRepository implements BasketRepository {

	private static final String TABLE_NAME = "users_basket";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void save(int userId, int articleId) {
		final String saveSQL = "INSERT INTO " + TABLE_NAME + " VALUES (?, ?)";
		jdbcTemplate.update(saveSQL, userId, articleId);
	}

	@Override
	public List<ArticleList> findByUserId(int id) {
		final String findSQL = "select * from users_basket"
				+ "inner join articles on users_basket.ARTICLE_ID = articles.ID"
				+ "inner join (select articles_descriptions.ARTICLE_ID, articles_descriptions.PREVIEW from articles_descriptions) as des on articles.ID = des.ARTICLE_ID"
				+ "inner join (select articles_images.ARTICLE_ID, articles_images.ARTICLE_LIST_THUMBNAIL from articles_images GROUP BY articles_images.ARTICLE_ID) as images on articles.ID = images.ARTICLE_ID"
				+ "where users_basket.USER_ID = ?";

		return null;
	}
}
