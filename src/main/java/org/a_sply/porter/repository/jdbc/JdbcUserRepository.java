package org.a_sply.porter.repository.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.a_sply.porter.domain.User;
import org.a_sply.porter.repository.UserRepository;
import org.a_sply.porter.util.CRC32Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcUserRepository implements UserRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	private GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
	private UserMapper userMapper = new UserMapper();

	@Override
	public User save(final User user) {
		final String userInsertSQL = "INSERT INTO users(NAME, NAME_CRC, EMAIL, EMAIL_CRC, PASSWORD, TELEPHONE) VALUES (?,?,?,?,?,?)";

		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(userInsertSQL,
						new String[] { "id" });
				ps.setString(1, user.getName());
				ps.setInt(2, CRC32Util.crcValue(user.getName()));
				ps.setString(3, user.getEmail());
				ps.setInt(4, CRC32Util.crcValue(user.getEmail()));
				ps.setString(5, user.getPassword());
				ps.setString(6, user.getTelephone());
				return ps;
			}
		}, keyHolder);
		
		user.setId(keyHolder.getKey().intValue());
		
		for (GrantedAuthority authority : user.getAuthorities()) {
			jdbcTemplate.update("insert into users_authorities values(?, ?)", user.getId(), authority.getAuthority());
		}
		
		System.out.println("user save id : " + keyHolder.getKey().intValue());
		return user;
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
	public User findByEmail(final String email) {
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
	public User findByName(final String name) {
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
}
