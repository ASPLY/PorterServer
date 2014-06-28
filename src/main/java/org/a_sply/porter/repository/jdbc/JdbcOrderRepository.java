package org.a_sply.porter.repository.jdbc;

import static org.a_sply.porter.repository.table_info.TableConst.ORDERS;
import static org.a_sply.porter.repository.table_info.TableConst.PRODUCTS;
import static org.a_sply.porter.repository.table_info.TableConst.USERS;
import static org.a_sply.porter.repository.table_info.TableConst.USERS_AUTHORITIES;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.a_sply.porter.domain.order.Order;
import org.a_sply.porter.domain.order.OrderCondition;
import org.a_sply.porter.repository.OrderRepository;
import org.a_sply.porter.repository.jdbc.extractor.OrdersExtractor;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcOrderRepository implements OrderRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private KeyHolder keyHolder;

	@Override
	public long insert(final Order order) {
		final String INSERT_SQL = new SQL(){{
			INSERT_INTO(ORDERS.toString());
			VALUES(ORDERS.USER_ID(), 			"?"); // 1
			VALUES(ORDERS.PRODUCT_ID(),		 	"?"); // 2
			VALUES(ORDERS.AMOUNT(), 			"?"); // 3
		}}.toString();
		
		jdbcTemplate.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(INSERT_SQL, new String[] { "id" });
				ps.setInt(1, order.getUser().getId());
				ps.setLong(2, order.getProduct().getId());
				ps.setInt(3, order.getAmount());
				return ps;
			}
		}, keyHolder);
		
		return keyHolder.getKey().longValue();
	}

	@Override
	public List<Order> selectByCondition(final OrderCondition orderCondition) {
		final String SELECT_BY_CONDITION_SQL = new SQL(){{
			SELECT("*");
			FROM(ORDERS.toString());
			LEFT_OUTER_JOIN(USERS.toString() + " on " + USERS.ID() +" = "+ ORDERS.USER_ID());
			LEFT_OUTER_JOIN(USERS_AUTHORITIES.toString() + " on " + USERS_AUTHORITIES.USER_ID() +" = "+ USERS.ID());
			LEFT_OUTER_JOIN(PRODUCTS.toString() + " on " + ORDERS.PRODUCT_ID() +" = "+ PRODUCTS.ID());
			
			int userId = orderCondition.getUserId();
			if(userId != 0)
				WHERE(ORDERS.USER_ID() + " = " + userId);
			

			ORDER_BY(PRODUCTS.ID() + " DESC limit "+ orderCondition.getOffset() +"," + orderCondition.getCount());
			//ORDER BY ID DESC limit ?, ?
		}}.toString();
		
		return jdbcTemplate.query(SELECT_BY_CONDITION_SQL, new OrdersExtractor());
	}

/*
	@Override
	public List<ArticleList> selectByUserId(int id) {
		final String findSQL = "select * from users_basket"
				+ "inner join articles on users_basket.ARTICLE_ID = articles.ID"
				+ "inner join (select articles_descriptions.ARTICLE_ID, articles_descriptions.PREVIEW from articles_descriptions) as des on articles.ID = des.ARTICLE_ID"
				+ "inner join (select articles_images.ARTICLE_ID, articles_images.ARTICLE_LIST_THUMBNAIL from articles_images GROUP BY articles_images.ARTICLE_ID) as images on articles.ID = images.ARTICLE_ID"
				+ "where users_basket.USER_ID = ?";

		return null;
	}*/
	
	
}
