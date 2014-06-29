package org.a_sply.porter.dao.impl_jdbc;

import static org.a_sply.porter.dao.table_info.TableConst.ITEMS;
import static org.a_sply.porter.dao.table_info.TableConst.PRODUCTS;
import static org.a_sply.porter.dao.table_info.TableConst.PRODUCTS_STATE;
import static org.a_sply.porter.dao.table_info.TableConst.USERS;
import static org.a_sply.porter.dao.table_info.TableConst.USERS_AUTHORITIES;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.a_sply.porter.dao.interfaces.ItemDao;
import org.a_sply.porter.dao.interfaces.SelectType;
import org.a_sply.porter.domain.item.Item;
import org.a_sply.porter.domain.product.Product;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class jdbcItemDao implements ItemDao{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void insert(Item item) {
		final String INSERT_SQL = new SQL(){{
			INSERT_INTO(ITEMS.toString());
			VALUES(ITEMS.USER_ID(), 			"?"); // 1
			VALUES(ITEMS.PRODUCT_ID(), 			"?"); // 2
			VALUES(ITEMS.QUANTITY(),	 		"?"); // 3
		}}.toString();
		
		jdbcTemplate.update(INSERT_SQL, item.getUserId(), item.getProductId(), item.getQuantity());
	}

	private final String DELETE_SQL = new SQL(){{
		DELETE_FROM(ITEMS.toString());
		WHERE(ITEMS.ITEM_ID() + " = " + " ? ");
	}}.toString();
	
	@Override
	public void delete(final long itemId) {
		jdbcTemplate.update(DELETE_SQL, itemId);
	}

	@Override
	public void delete(final long[] itemIds) {
		jdbcTemplate.batchUpdate(DELETE_SQL, new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setLong(1, itemIds[i]);
			}
			@Override
			public int getBatchSize() {
				return itemIds.length;
			}
		});
	}

	@Override
	public List<Item> selectByUserId(final int userId) {
		String SELECT_BY_USER_ID = new SQL(){{
			SELECT("*");
			FROM(ITEMS.toString());
			WHERE(ITEMS.USER_ID() + "=" + userId);
		}}.toString();

		return jdbcTemplate.query(SELECT_BY_USER_ID, new ExtendedBeanPropertyRowMapper<Item>(Item.class));
	}
	
	
	/*
	 * final String SELECT_BY_CONDITION_SQL = new SQL(){{
			SELECT("*");
			FROM(ORDERS.toString());
			LEFT_OUTER_JOIN(USERS.toString() + " on " + USERS.ID() +" = "+ ORDERS.USER_ID());
			LEFT_OUTER_JOIN(USERS_AUTHORITIES.toString() + " on " + USERS_AUTHORITIES.USER_ID() +" = "+ USERS.ID());
			LEFT_OUTER_JOIN(PRODUCTS.toString() + " on " + ORDERS.PRODUCT_ID() +" = "+ PRODUCTS.PRODUCT_ID());
			
			int userId = orderCondition.getUserId();
			if(userId != 0)
				WHERE(ORDERS.USER_ID() + " = " + userId);
			

			ORDER_BY(PRODUCTS.PRODUCT_ID() + " DESC limit "+ orderCondition.getOffset() +"," + orderCondition.getCount());
			//ORDER BY ID DESC limit ?, ?
		}}.toString();
	 * */
}
