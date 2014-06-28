package org.a_sply.porter.repository.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.a_sply.porter.domain.Article;
import org.a_sply.porter.domain.Description;
import org.a_sply.porter.domain.Image;
import org.a_sply.porter.domain.Part;
import org.a_sply.porter.domain.User;
import org.a_sply.porter.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcArticleRepository implements ArticleRepository {

	private GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private ArticleMapper articleMapper = new ArticleMapper();

	@Override
	public Article save(final Article article) {
		final String defaultArticleSQL = "insert into articles(USER_ID, NAME, MIDDLE_CATEGORY, LARGE_CATEGORY, PRICE, STATE, QUANTITY, REGION, MAKER) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";

		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(
						defaultArticleSQL, new String[] { "id" });
				ps.setInt(1, article.getUser().getId());
				ps.setString(2, article.getPart().getName());
				ps.setString(3, article.getPart().getMiddleCategory());
				ps.setString(4, article.getPart().getLargeCategory());
				ps.setString(5, article.getPart().getPrice());
				ps.setString(6, article.getPart().getState());
				ps.setString(7, article.getPart().getQuantity());
				ps.setString(8, article.getPart().getRegion());
				ps.setString(9, article.getPart().getMaker());
				return ps;
			}
		}, keyHolder);
		System.out.println("article save id : " + keyHolder.getKey().intValue());
		article.setId(keyHolder.getKey().intValue());
 
		jdbcTemplate.update(
				"insert into articles_descriptions values(?, ?, ?)",
				article.getId(), article.getPart().getDescription()
						.getContent(), article.getPart().getDescription()
						.getPreview());

		for (Image image : article.getPart().getImages()) {
			jdbcTemplate.update("insert into articles_images values(?, ?, ?, ?)",
					article.getId(), image.getOriginal(), image.getArticleThumbnail(), image.getArticleListThumbnail());
		}

		for (String keyword : article.getPart().getKeywords()) {
			jdbcTemplate.update("insert into articles_keywords values(?, ?)", article.getId(), keyword);
		}

		return article;
	}

	@Override
	public Article get(final int id) {
		List<Article> articles = getArticleListAndSetDafaultInfo(id);

		if (articles.size() == 0)
			return null;

		// id 값 세팅
		articles.get(0).setId(id);

		getImagesAndSetInfo(id, articles);
		getKeywordsAndSetInfo(id, articles);

		return articles.get(0);
	}

	private void getKeywordsAndSetInfo(final int id, List<Article> articles) {
		final String keywordSQL = "select * from articles_keywords where article_id = ?";
		List<String> keywords = jdbcTemplate.query(
				new PreparedStatementCreator() {

					@Override
					public PreparedStatement createPreparedStatement(
							Connection con) throws SQLException {
						PreparedStatement ps = con.prepareStatement(keywordSQL);
						ps.setInt(1, id);
						return ps;
					}
				}, new RowMapper<String>() {

					@Override
					public String mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						return rs.getString("KEYWORD");
					}
				});

		articles.get(0).getPart().setKeywords(keywords);
	}

	private void getImagesAndSetInfo(final int id, List<Article> articles) {
		final String imageSQL = "select * from articles_images where article_id = ?";
		List<Image> images = jdbcTemplate.query(
				new PreparedStatementCreator() {
					@Override
					public PreparedStatement createPreparedStatement(
							Connection con) throws SQLException {
						PreparedStatement ps = con.prepareStatement(imageSQL);
						ps.setInt(1, id);
						return ps;
					}
				}, new RowMapper<Image>() {
					@Override
					public Image mapRow(ResultSet rs, int rowNum) throws SQLException {
						String original = rs.getString("ORIGINAL");
						String articleThumbnail = rs.getString("ARTICLE_THUMBNAIL");
						String articleListThumbnail = rs.getString("ARTICLE_LIST_THUMBNAIL");
						return new Image(original, articleThumbnail, articleListThumbnail);
					}
				});
		articles.get(0).getPart().setImages(images);
	}

	private List<Article> getArticleListAndSetDafaultInfo(final int id) {
		final String SQL = "select * from users,articles,articles_descriptions where articles.ID = ? and users.ID = articles.USER_ID and articles.ID = articles_descriptions.ARTICLE_ID;";

		// id, images, keywords을 제외한 나머지 값 세팅
		List<Article> articles = jdbcTemplate.query(
				new PreparedStatementCreator() {
					@Override
					public PreparedStatement createPreparedStatement(
							Connection con) throws SQLException {
						PreparedStatement ps = con.prepareStatement(SQL);
						ps.setInt(1, id);
						return ps;
					}
				}, articleMapper);
		return articles;
	}

	private class ArticleMapper implements RowMapper<Article> {

		@Override
		public Article mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setId(rs.getInt("USER_ID"));
			user.setEmail(rs.getString("EMAIL"));
			user.setTelephone(rs.getString("TELEPHONE"));
			user.setName(rs.getString("USERS.NAME"));

			Part part = new Part();
			Description description = new Description(rs.getString("CONTENT"), rs.getString("PREVIEW"));
			part.setDescription(description);
			part.setLargeCategory(rs.getString("LARGE_CATEGORY"));
			part.setMaker(rs.getString("MAKER"));
			part.setMiddleCategory(rs.getString("MIDDLE_CATEGORY"));
			part.setName(rs.getString("ARTICLES.NAME"));
			part.setPrice(rs.getString("PRICE"));
			part.setQuantity(rs.getString("QUANTITY"));
			part.setRegion(rs.getString("REGION"));
			part.setState(rs.getString("STATE"));

			Article article = new Article();
			if (rs.getInt("IS_SOLD") != 0)
				article.setIsSold(true);
			else
				article.setIsSold(false);

			article.setUser(user);
			article.setPart(part);
			return article;
		}

	}

	@Override
	public void sold(int id) {
		jdbcTemplate.update("update articles set IS_SOLD = ? where ID = ?", 1,
				id);
	}
}
