package org.a_sply.porter.repository;

import java.util.List;

import org.a_sply.porter.domain.MessageList;

public interface MessageListRepository {
	List<MessageList> findByUserId(int id);
}
