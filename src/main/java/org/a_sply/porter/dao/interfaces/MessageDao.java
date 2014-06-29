package org.a_sply.porter.dao.interfaces;

import org.a_sply.porter.domain.Message;

public interface MessageDao {
	Message save(Message message);

	Message findById(int id);

	void delete(int id);
}
