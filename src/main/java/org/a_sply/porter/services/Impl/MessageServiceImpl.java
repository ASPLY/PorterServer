package org.a_sply.porter.services.Impl;

import java.util.List;

import org.a_sply.porter.dao.interfaces.MessageDao;
import org.a_sply.porter.dao.interfaces.UserDao;
import org.a_sply.porter.domain.Message;
import org.a_sply.porter.services.AuthenticationService;
import org.a_sply.porter.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * MessageService implementation. 
 * @author LCH
 */


@Service
@Transactional
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageDao messageDao;

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private AuthenticationService authenticationService;
	/*
	@Override
	public void send(SendMessageDTO sendMessageDTO) {
		User from = authenticationService.getCurrentUser();
		User to = userDao.selectByName(sendMessageDTO.getTo());
		Message message = new Message(to, from, sendMessageDTO.getContent());
		messageDao.save(message);
	}

	@Override
	public MessageDTO get(int id) {
		Message message = messageDao.findById(id);
		if (message == null)
			return null;
		return message.messageDTO();
	}

	@Override
	public void remove(int id) {
		messageDao.delete(id);
	}*/

	@Override
	public void send(Message message) {
	}

	@Override
	public List<Message> getMine() {
		return null;
	}

	@Override
	public Message get(long messageId) {
		return null;
	}

	@Override
	public void remove(long[] messageIds) {
	}
}
