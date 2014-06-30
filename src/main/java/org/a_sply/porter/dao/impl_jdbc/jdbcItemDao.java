package org.a_sply.porter.dao.impl_jdbc;

import static org.a_sply.porter.dao.table_info.TableConst.ITEMS;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.a_sply.porter.dao.interfaces.ItemDao;
import org.a_sply.porter.domain.item.Item;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class jdbcItemDao implements ItemDao{

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	private DataSource dataSource;
	
	public jdbcItemDao() {
	}
	
	@Override
	public void insert(Item item) {
		new SimpleJdbcInsert(dataSource).withTableName(ITEMS.toString()).execute(new MappedBeanPropertySqlParameterSource(item));
	}

	private final String DELETE_SQL = new SQL(){{
		DELETE_FROM(ITEMS.toString());
		WHERE(ITEMS.FIELD_ITEM_ID + " = " + Item.NAMED_ITEM_ID);
	}}.toString();
	
	@Override
	public void delete(final long itemId) {
		namedParameterJdbcTemplate.update(DELETE_SQL, new MapSqlParameterSource(Item.ITEM_ID, itemId));
	}

	@Override
	public void delete(final long[] itemIds) {
		List<MapSqlParameterSource> parameters = new ArrayList<MapSqlParameterSource>();
		for (long itemId : itemIds)
			parameters.add(new MapSqlParameterSource(Item.ITEM_ID, itemId));
		
		namedParameterJdbcTemplate.batchUpdate(DELETE_SQL, parameters.toArray(new MapSqlParameterSource[0]));
	}

	@Override
	public List<Item> selectByUserId(final long userId) {
		String SELECT_BY_USER_ID = new SQL(){{
			SELECT("*");
			FROM(ITEMS.toString());
			WHERE(ITEMS.FIELD_USER_ID + "=" + Item.NAMED_ITEM_ID);
		}}.toString();

		return namedParameterJdbcTemplate.query(SELECT_BY_USER_ID, 
				new MapSqlParameterSource(Item.ITEM_ID, userId), 
				new ExtendedBeanPropertyRowMapper<Item>(Item.class));
	}
}
