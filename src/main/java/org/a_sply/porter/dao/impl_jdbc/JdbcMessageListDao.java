package org.a_sply.porter.dao.impl_jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.a_sply.porter.dao.interfaces.MessageListDao;
import org.a_sply.porter.domain.MessageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcMessageListDao implements MessageListDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private MessageListMapper messageListMapper = new MessageListMapper();

	@Override
	public List<MessageList> findByUserId(final long id) {
		final String SQL = "select messages.ID, messages.PREVIEW, messages.SENDING_DATE, from_user.NAME from messages, users as from_user where messages.FROM_USER_ID = ? and from_user.ID = ? ORDER BY messages.ID DESC;";
		return jdbcTemplate.query(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				PreparedStatement ps = con.prepareStatement(SQL);
				ps.setLong(1, id);
				ps.setLong(2, id);
				return ps;
			}
		}, messageListMapper);
	}

	private class MessageListMapper implements RowMapper<MessageList> {

		@Override
		public MessageList mapRow(ResultSet rs, int rowNum) throws SQLException {
			MessageList messageList = new MessageList(rs.getInt("ID"),
					rs.getString("NAME"), rs.getString("PREVIEW"),
					rs.getTimestamp("SENDING_DATE"));
			return messageList;
		}

	}

}
