package org.a_sply.porter.repository;

import org.a_sply.porter.domain.Message;

public interface MessageRepository {
	Message save(Message message);

	Message findById(int id);

	void delete(int id);
}
