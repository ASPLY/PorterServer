package org.a_sply.porter.dao.impl_jdbc;

import static org.a_sply.porter.dao.table_info.TableConst.USERS;
import static org.a_sply.porter.dao.table_info.TableConst.USERS_AUTHORITIES;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.sql.DataSource;

import org.a_sply.porter.dao.interfaces.SelectType;
import org.a_sply.porter.dao.interfaces.UserDao;
import org.a_sply.porter.domain.user.User;
import org.a_sply.porter.domain.user.UserCondition;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcUserDao implements UserDao {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private KeyHolder keyHolder;

	@Override
	public long insert(final User user) {
		final long userId = insertUser(user);
		user.setUserId(userId);
		insertUserAuthorities(user);
		return userId;
	}

	private long insertUser(final User user) {
		return new SimpleJdbcInsert(dataSource)
		.withTableName(USERS.toString())
		.usingGeneratedKeyColumns(USERS.FEILD_USER_ID)
		.executeAndReturnKeyHolder(new MappedBeanPropertySqlParameterSource(user))
		.getKey().longValue();
	}
	
	
	private void insertUserAuthorities(final User user) {
		final String INSERT_SQL = new SQL(){{
			INSERT_INTO(USERS_AUTHORITIES.toString());
			VALUES(USERS_AUTHORITIES.FEILD_USER_ID, 		 User.NAMED_USER_ID); // 1
			VALUES(USERS_AUTHORITIES.FEILD_AUTHORITY,		 User.NAMED_AUTHORITY); // 2
		}}.toString();
		
		List<MapSqlParameterSource> parameters = new ArrayList<MapSqlParameterSource>();
		
		for (GrantedAuthority grantedAuthority : user.getAuthorities())
			parameters.add(new MapSqlParameterSource()
					.addValue(User.USER_ID, 	user.getUserId())
					.addValue(User.AUTHORITY, 	grantedAuthority.toString()));
		
		namedParameterJdbcTemplate.batchUpdate(INSERT_SQL, parameters.toArray(new MapSqlParameterSource[0]));
	}
	
	@Override
	public List<User> selectByCondition(SelectType selectType, final UserCondition userCondition) {
		
		final String SELECT_BY_CONDITION = new SQL(){{
			SELECT("*");
			FROM(USERS.toString());
			if(userCondition.getEmail() != null){
				WHERE(USERS.FEILD_EMAIL + 		" = " + UserCondition.NAMED_EMAIL);
				WHERE(USERS.FEILD_EMAIL_CRC + 	" = " + UserCondition.NAMED_EMAIL_CRC);
			}
			if(userCondition.getName() != null){
				WHERE(USERS.FEILD_NAME + 		" = " + UserCondition.NAMED_NAME);
				WHERE(USERS.FEILD_NAME_CRC  + 	" = " + UserCondition.NAMED_NAME_CRC);
			}
		}}.toString();
		
		List<User> users = namedParameterJdbcTemplate.query(SELECT_BY_CONDITION, 
				new MappedBeanPropertySqlParameterSource(userCondition), 
				new ExtendedBeanPropertyRowMapper<User>(User.class));
		
		if(selectType == SelectType.WITH_DETAIL)
			for (User user : users) 
				user.setAuthorities(selectUserAuthoritiesByUserId(user.getUserId()));
		
		return users;
	}

	@Override
	public User selectByUserId(SelectType selectType, final long userId) {
		final String SELECT_BY_ID = new SQL(){{
			SELECT("*");
			FROM(USERS.toString());
			WHERE(USERS.FEILD_USER_ID +" = "+ User.NAMED_USER_ID);
		}}.toString();
	
		User user = namedParameterJdbcTemplate.queryForObject(SELECT_BY_ID, 
				new MapSqlParameterSource(User.USER_ID, userId), 
				new ExtendedBeanPropertyRowMapper<User>(User.class));
		
		if(selectType == SelectType.WITH_DETAIL)
			user.setAuthorities(selectUserAuthoritiesByUserId(user.getUserId()));
			
		return user;
	}

	private Collection<? extends GrantedAuthority> selectUserAuthoritiesByUserId(final long userId) {
		final String SELECT_USER_AUTHORITIES_BY_USER_ID = new SQL(){{
			SELECT("*");
			FROM(USERS_AUTHORITIES.toString());
			WHERE(USERS_AUTHORITIES.FEILD_USER_ID + " = " + User.NAMED_USER_ID);
		}}.toString();
		
		return namedParameterJdbcTemplate.query(SELECT_USER_AUTHORITIES_BY_USER_ID, 
				new MapSqlParameterSource(User.USER_ID, userId), 
				new RowMapper<SimpleGrantedAuthority>(){
			@Override
			public SimpleGrantedAuthority mapRow(ResultSet rs, int rowNum) throws SQLException {
				return new SimpleGrantedAuthority(rs.getString(USERS_AUTHORITIES.FEILD_AUTHORITY));
			}
		});
	}
}
