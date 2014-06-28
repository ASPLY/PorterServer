package org.a_sply.porter.repository.jdbc;

import static org.a_sply.porter.repository.table_info.TableConst.PRODUCTS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.a_sply.porter.domain.User;
import org.a_sply.porter.repository.UserRepository;
import org.a_sply.porter.repository.jdbc.extractor.UserExtractor;
import org.a_sply.porter.util.CRC32Util;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import static org.a_sply.porter.repository.table_info.TableConst.*;

@Repository
public class JdbcUserRepository implements UserRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private KeyHolder keyHolder;
	
	private UserMapper userMapper = new UserMapper();

	@Override
	public int insert(final User user) {
		final String INSERT_SQL_1 = new SQL(){{
			INSERT_INTO(USERS.toString());
			VALUES(USERS.NAME(), 			"?"); // 1
			VALUES(USERS.NAME_CRC(),	 	"?"); // 2
			VALUES(USERS.EMAIL(),	 		"?"); // 3
			VALUES(USERS.EMAIL_CRC(),	 	"?"); // 4
			VALUES(USERS.PASSWORD(), 		"?"); // 5
			VALUES(USERS.TELEPHONE(),		"?"); // 6
		}}.toString();

		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_SQL_1, new String[] { "id" });
				ps.setString(1, user.getName());
				ps.setInt(2, CRC32Util.crcValue(user.getName()));
				ps.setString(3, user.getEmail());
				ps.setInt(4, CRC32Util.crcValue(user.getEmail()));
				ps.setString(5, user.getPassword());
				ps.setString(6, user.getTelephone());
				return ps;
			}
		}, keyHolder);
		
		final int id = keyHolder.getKey().intValue();
		
		final String INSERT_SQL_2 = new SQL(){{
			INSERT_INTO(USERS_AUTHORITIES.toString());
			VALUES(USERS_AUTHORITIES.USER_ID(), 		"?"); // 1
			VALUES(USERS_AUTHORITIES.AUTHORITY(),		 "?"); // 2
		}}.toString();
		
		jdbcTemplate.batchUpdate(INSERT_SQL_2, new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setInt(1, id);
				ps.setString(2, user.getAuthorities().toArray()[i].toString());
			}
			
			@Override
			public int getBatchSize() {
				return user.getAuthorities().size();
			}
		});
		
		System.out.println("user save id : " + keyHolder.getKey().intValue());
		return id;
	}

	@Override
	public boolean containsEmail(String email) {
		int count = jdbcTemplate.queryForObject("select count(*) from users where EMAIL_CRC = ? and EMAIL = ?", new Object[]{CRC32Util.crcValue(email), email}, Integer.class);
		if (count == 0)
			return false;
		return true;
	}

	@Override
	public boolean containsName(String name) {
		int count = jdbcTemplate.queryForObject("select count(*) from users where NAME_CRC = ? and NAME = ?", new Object[]{CRC32Util.crcValue(name), name}, Integer.class);
		if (count == 0)
			return false;
		return true;
	}

	@Override
	public boolean contains(User user) {
		int count = jdbcTemplate
				.queryForObject(
						"select count(*) from users where EMAIL_CRC = ? and EMAIL = ? and PASSWORD = ?",
						new Object[]{CRC32Util.crcValue(user.getEmail()), user.getEmail(),
						user.getPassword()}, Integer.class);
		if (count == 1)
			return true;
		return false;
	}

	@Override
	public User selectByEmail(final String email) {
		final String SQL = "select * from users where EMAIL_CRC = ? and EMAIL = ?";

		List<User> users = jdbcTemplate.query(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				PreparedStatement ps = con.prepareStatement(SQL);
				ps.setInt(1, CRC32Util.crcValue(email));
				ps.setString(2, email);
				return ps;
			}
		}, userMapper);

		if(users.size() == 0)
			return null;
		
		users.get(0).setAuthorities(findAuthoritysById(users.get(0).getId()));
		return users.get(0);
	}

	@Override
	public User selectByName(final String name) {
		final String SQL = "select * from users where NAME_CRC = ? and NAME = ?";

		List<User> users = jdbcTemplate.query(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				PreparedStatement ps = con.prepareStatement(SQL);
				ps.setInt(1, CRC32Util.crcValue(name));
				ps.setString(2, name);
				return ps;
			}
		}, userMapper);

		if(users.size() == 0)
			throw new UsernameNotFoundException("no user");
		
		users.get(0).setAuthorities(findAuthoritysById(users.get(0).getId()));
		return users.get(0);
	}

	private Collection<? extends GrantedAuthority> findAuthoritysById(int id) {
		String SQL = "select * from users_authorities where USER_ID = " + id;
		List<GrantedAuthority> grantedAuthorities = jdbcTemplate.query(SQL,
				new RowMapper<GrantedAuthority>() {
					@Override
					public GrantedAuthority mapRow(ResultSet rs, int rowNum) throws SQLException {
						return new SimpleGrantedAuthority(rs.getString("AUTHORITY"));
					}
				});
		return grantedAuthorities;
	}

	private class UserMapper implements RowMapper<User> {
		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new User(rs.getInt("id"), rs.getString("NAME"), rs.getString("EMAIL"), rs.getString("TELEPHONE"), rs.getString("PASSWORD"), null);
		}
	}

	@Override
	public User selectById(final int id) {
		final String SELECT_BY_ID = new SQL(){{
			SELECT("*");
			FROM(USERS.toString());
			LEFT_OUTER_JOIN(USERS_AUTHORITIES.toString() + " on " + USERS_AUTHORITIES.USER_ID() + " = " + USERS.ID());
			WHERE(USERS.ID() +" = "+ id);
		}}.toString();
		return jdbcTemplate.query(SELECT_BY_ID, new UserExtractor());
	}
}
