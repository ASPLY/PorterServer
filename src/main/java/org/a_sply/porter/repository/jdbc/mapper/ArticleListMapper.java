package org.a_sply.porter.repository.jdbc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.a_sply.porter.domain.ArticleList;
import org.springframework.jdbc.core.RowMapper;

public class ArticleListMapper implements RowMapper<ArticleList> {
	
	private static final ArticleListMapper instance = new ArticleListMapper(); 

	private ArticleListMapper() {
	}
	
	@Override
	public ArticleList mapRow(ResultSet rs, int rowNum) throws SQLException {
		ArticleList articleList = new ArticleList();
		articleList.setArticleId(rs.getInt("ID"));
		articleList.setDescription(rs.getString("PREVIEW"));
		articleList.setImageUrl(rs.getString("ARTICLE_LIST_THUMBNAIL"));
		articleList.setLargeCategory(rs.getString("LARGE_CATEGORY"));
		articleList.setMiddleCategory(rs.getString("MIDDLE_CATEGORY"));
		articleList.setName(rs.getString("NAME"));
		articleList.setPrice(String.valueOf(rs.getInt("PRICE")));
		if (rs.getInt("IS_SOLD") == 0) {
			articleList.setIsSold("false");
		} else {
			articleList.setIsSold("true");
		}
		return articleList;
	}
	
	public static ArticleListMapper getInstance(){
		return instance;
	}
}