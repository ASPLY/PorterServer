package org.a_sply.porter.services;

import org.a_sply.porter.dto.message.MessageDTO;
import org.a_sply.porter.dto.message.SendMessageDTO;

public interface MessageService {
	void send(SendMessageDTO sendMessageDTO);
	MessageDTO get(int id);
	void remove(int id);
}
