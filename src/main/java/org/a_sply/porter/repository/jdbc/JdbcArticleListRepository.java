package org.a_sply.porter.repository.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.a_sply.porter.domain.ArticleList;
import org.a_sply.porter.domain.RequestArticleLists;
import org.a_sply.porter.domain.Search;
import org.a_sply.porter.repository.ArticleListRepository;
import org.a_sply.porter.util.CRC32Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcArticleListRepository implements ArticleListRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private ArticleListMapper articleListMapper = new ArticleListMapper();

	@Override
	public List<ArticleList> search(Search search) {
		String keyword = search.getKeyword();
		List<ArticleList> articleLists = new ArrayList<ArticleList>();

		if (keyword == null)
			return articleLists;

		if ("".equals(keyword))
			return articleLists;

		if (keyword.matches("^\\s*$"))
			return articleLists;

		if (containsWriteSpace(keyword))
			articleLists = searchMutiCondition(search);
		else
			articleLists = searchOneCondition(search);

		// 카테고리 처리
		if (search.getLargeCategory() != null)
			for (int i = 0; i < articleLists.size(); i++) {
				ArticleList articleList = articleLists.get(i);
				if (!equalsCategory(search, articleList))
					articleLists.remove(i);
			}

		return articleLists;
	}

	private boolean equalsCategory(Search search, ArticleList articleList) {
		return articleList.getLargeCategory().equals(search.getLargeCategory())
				&& articleList.getMiddleCategory().equals(
						search.getMiddleCategory());
	}

	private List<ArticleList> searchOneCondition(Search search) {
		if (search.getOffset() == null)
			return jdbcTemplate
					.query("select articles.ID, articles.IS_SOLD, articles.MIDDLE_CATEGORY, articles.LARGE_CATEGORY, articles.NAME, articles.PRICE, (select articles_descriptions.PREVIEW from articles_descriptions where articles_descriptions.ARTICLE_ID = articles.ID) AS PREVIEW, (select articles_images.ARTICLE_LIST_THUMBNAIL from articles_images where articles_images.ARTICLE_ID = articles.ID limit 1) AS ARTICLE_LIST_THUMBNAIL "
							+ "from articles "
							+ "where REPLACE(articles.NAME,' ','') "
							+ "like ? ORDER BY ID DESC limit ?",
							new Object[] { "%" + search.getKeyword() + "%",
									search.getCount() }, articleListMapper);
		else
			return jdbcTemplate
					.query("select articles.ID, articles.IS_SOLD, articles.MIDDLE_CATEGORY, articles.LARGE_CATEGORY, articles.NAME, articles.PRICE, (select articles_descriptions.PREVIEW from articles_descriptions where articles_descriptions.ARTICLE_ID = articles.ID) AS PREVIEW, (select articles_images.ARTICLE_LIST_THUMBNAIL from articles_images where articles_images.ARTICLE_ID = articles.ID limit 1) AS ARTICLE_LIST_THUMBNAIL from articles where REPLACE(articles.NAME,' ','') like ? ORDER BY ID DESC limit ?, ?",
							new Object[] { "%" + search.getKeyword() + "%",
									search.getOffset(), search.getCount() },
							articleListMapper);

	}

	private List<ArticleList> searchMutiCondition(Search search) {
		String[] keywords = search.getKeyword().split(" ");
		String selectSQL = "select articles.ID, articles.IS_SOLD, articles.MIDDLE_CATEGORY, articles.LARGE_CATEGORY, articles.NAME, articles.PRICE, (select articles_descriptions.PREVIEW from articles_descriptions where articles_descriptions.ARTICLE_ID = articles.ID) AS PREVIEW, (select articles_images.ARTICLE_LIST_THUMBNAIL from articles_images "
				+ "where articles_images.ARTICLE_ID = articles.ID limit 1) AS ARTICLE_LIST_THUMBNAIL from articles ";
		String where = "where ";
		for (int i = 0; i < keywords.length; i++) {
			if (i != 0)
				where += " and ";
			where += "REPLACE(articles.NAME,' ','') like '%" + keywords[i]
					+ "%'";
		}
		String limit = null;
		if (search.getOffset() == null)
			limit = "limit " + search.getCount();
		else
			limit = "limit " + search.getOffset() + " , " + search.getCount();

		String order = "ORDER BY ID DESC";
		String sql = selectSQL + " " + where + " " + order + " " + limit;
		return jdbcTemplate.query(sql, articleListMapper);
	}

	private boolean containsWriteSpace(String keyword) {
		return keyword.indexOf(' ') != -1;
	}

	@Override
	public List<ArticleList> searchByEmail(String email) {

		SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(
				"select * from users where email_crc = ? and email = ?",
				CRC32Util.crcValue(email), email);
		sqlRowSet.next();
		final int userId = sqlRowSet.getInt("ID");

		final String SQL = "select articles.ID, articles.IS_SOLD, articles.MIDDLE_CATEGORY, articles.LARGE_CATEGORY, articles.NAME, articles.PRICE, (select articles_descriptions.PREVIEW from articles_descriptions where articles_descriptions.ARTICLE_ID = articles.ID) AS PREVIEW, (select articles_images.ARTICLE_LIST_THUMBNAIL from articles_images where articles_images.ARTICLE_ID = articles.ID limit 1) AS ARTICLE_LIST_THUMBNAIL from articles where USER_ID = ? ORDER BY ID DESC";
		List<ArticleList> articleLists = jdbcTemplate.query(
				new PreparedStatementCreator() {

					@Override
					public PreparedStatement createPreparedStatement(
							Connection con) throws SQLException {
						PreparedStatement ps = con.prepareStatement(SQL);
						ps.setInt(1, userId);
						return ps;
					}
				}, articleListMapper);

		return articleLists;
	}

	private class ArticleListMapper implements RowMapper<ArticleList> {

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
	}

	@Override
	public List<ArticleList> get(RequestArticleLists getArticleLists) {
		int count = getArticleLists.getCount();
		List<ArticleList> articleLists = new ArrayList<ArticleList>();

		if (count == 0)
			return articleLists;

		int offset = getArticleLists.getOffset();
		if (offset == 0)
			return jdbcTemplate
					.query("select articles.ID, articles.IS_SOLD, articles.MIDDLE_CATEGORY, articles.LARGE_CATEGORY, articles.NAME, articles.PRICE, (select articles_descriptions.PREVIEW from articles_descriptions where articles_descriptions.ARTICLE_ID = articles.ID) AS PREVIEW, (select articles_images.ARTICLE_LIST_THUMBNAIL from articles_images where articles_images.ARTICLE_ID = articles.ID limit 1) AS ARTICLE_LIST_THUMBNAIL from articles where articles.LARGE_CATEGORY = ? and articles.MIDDLE_CATEGORY = ? ORDER BY ID DESC limit ?",
							new Object[] { getArticleLists.getLargeCategory(),
									getArticleLists.getMiddleCategory(), count },
							articleListMapper);
		else
			return jdbcTemplate
					.query("select articles.ID, articles.IS_SOLD, articles.MIDDLE_CATEGORY, articles.LARGE_CATEGORY, articles.NAME, articles.PRICE, (select articles_descriptions.PREVIEW from articles_descriptions where articles_descriptions.ARTICLE_ID = articles.ID) AS PREVIEW, (select articles_images.ARTICLE_LIST_THUMBNAIL from articles_images where articles_images.ARTICLE_ID = articles.ID limit 1) AS ARTICLE_LIST_THUMBNAIL from articles where articles.LARGE_CATEGORY = ? and articles.MIDDLE_CATEGORY = ? ORDER BY ID DESC limit ?, ?",
							new Object[] { getArticleLists.getLargeCategory(),
									getArticleLists.getMiddleCategory(),
									offset, count }, articleListMapper);
	}
}
