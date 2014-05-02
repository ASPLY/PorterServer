package org.a_sply.porter.services;

import org.a_sply.porter.domain.Message;
import org.a_sply.porter.domain.User;
import org.a_sply.porter.dto.message.MessageDTO;
import org.a_sply.porter.dto.message.SendMessageDTO;
import org.a_sply.porter.repository.MessageRepository;
import org.a_sply.porter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageRepository messageRository;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AuthenticationService authenticationService;

	@Override
	public void send(SendMessageDTO sendMessageDTO) {
		User from = authenticationService.getCurrentUser();
		User to = userRepository.findByName(sendMessageDTO.getTo());
		Message message = new Message(to, from, sendMessageDTO.getContent());
		messageRository.save(message);
	}

	@Override
	public MessageDTO get(int id) {
		Message message = messageRository.findById(id);
		if (message == null)
			return null;
		return message.messageDTO();
	}

	@Override
	public void remove(int id) {
		messageRository.delete(id);
	}

}
