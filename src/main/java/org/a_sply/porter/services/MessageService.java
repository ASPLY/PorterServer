package org.a_sply.porter.services;

import java.util.List;

import org.a_sply.porter.domain.Message;


public interface MessageService {
	void send(Message message);
	
	List<Message> getMine();
	Message get(long messageId);
	
	void remove(long[] messageIds);
}
