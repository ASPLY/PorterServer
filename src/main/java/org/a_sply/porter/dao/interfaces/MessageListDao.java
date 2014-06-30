package org.a_sply.porter.dao.interfaces;

import java.util.List;

import org.a_sply.porter.domain.MessageList;

public interface MessageListDao {
	List<MessageList> findByUserId(long id);
}
