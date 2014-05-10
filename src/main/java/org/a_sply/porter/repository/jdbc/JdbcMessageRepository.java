package org.a_sply.porter.repository.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.a_sply.porter.domain.Message;
import org.a_sply.porter.domain.User;
import org.a_sply.porter.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcMessageRepository implements MessageRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private MessageMapper messageMapper = new MessageMapper();

	private GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

	@Override
	public Message save(final Message message) {
		final String SQL = "INSERT INTO messages(TO_USER_ID, FROM_USER_ID, PREVIEW, CONTENT) VALUES (?,?,?,?)";
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(SQL,
						new String[] { "id" });
				ps.setInt(1, message.getTo().getId());
				ps.setInt(2, message.getFrom().getId());
				ps.setString(3, message.getPreview());
				ps.setString(4, message.getContent());
				return ps;
			}
		}, keyHolder);

		message.setId(keyHolder.getKey().intValue());
		return message;
	}

	@Override
	public Message findById(final int id) {
		final String SQL = "select messages.ID, messages.PREVIEW, messages.CONTENT, messages.SENDING_DATE, to_user.ID TO_USER_ID, to_user.NAME TO_USER_NAME, to_user.EMAIL TO_USER_EMAIL, to_user.TELEPHONE TO_USER_TELEPHONE, from_user.ID FROM_USER_ID, from_user.NAME FROM_USER_NAME, from_user.EMAIL FROM_USER_EMAIL, from_user.TELEPHONE FROM_USER_TELEPHONE from messages inner join users as to_user on messages.TO_USER_ID = to_user.ID inner join users as from_user on messages.FROM_USER_ID = from_user.ID where messages.ID = ?";

		List<Message> messages = jdbcTemplate.query(
				new PreparedStatementCreator() {

					@Override
					public PreparedStatement createPreparedStatement(
							Connection con) throws SQLException {
						PreparedStatement ps = con.prepareStatement(SQL);
						ps.setInt(1, id);
						return ps;
					}
				}, messageMapper);

		if (messages.size() == 0)
			return null;

		return messages.get(0);
	}

	private class MessageMapper implements RowMapper<Message> {

		@Override
		public Message mapRow(ResultSet rs, int rowNum) throws SQLException {
			Message message = new Message();
			message.setId(rs.getInt("ID"));
			message.setPreview(rs.getString("PREVIEW"));
			message.setContent(rs.getString("CONTENT"));
			message.setSending(rs.getTimestamp("SENDING_DATE"));

			User to = new User();
			to.setId(rs.getInt("TO_USER_ID"));
			to.setEmail(rs.getString("TO_USER_EMAIL"));
			to.setName(rs.getString("TO_USER_NAME"));
			to.setTelephone(rs.getString("TO_USER_TELEPHONE"));

			User from = new User();
			from.setId(rs.getInt("FROM_USER_ID"));
			from.setEmail(rs.getString("FROM_USER_EMAIL"));
			from.setName(rs.getString("FROM_USER_NAME"));
			from.setTelephone(rs.getString("FROM_USER_TELEPHONE"));

			message.setTo(to);
			message.setFrom(from);
			return message;
		}

	}

	@Override
	public void delete(int id) {
		jdbcTemplate.update("DELETE FROM messages WHERE id = ?", id);
	}

	public int countAll() {
		return jdbcTemplate.queryForInt("select count(*) from messages");
	}

}
