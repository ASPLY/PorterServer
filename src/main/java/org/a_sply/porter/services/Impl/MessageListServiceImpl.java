package org.a_sply.porter.services.Impl;

import java.util.ArrayList;
import java.util.List;

import org.a_sply.porter.domain.MessageList;
import org.a_sply.porter.domain.User;
import org.a_sply.porter.dto.message.MessageListDTO;
import org.a_sply.porter.dto.message.MessageListsDTO;
import org.a_sply.porter.repository.MessageListRepository;
import org.a_sply.porter.services.AuthenticationService;
import org.a_sply.porter.services.MessageListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * MessageListService implementation. 
 * @author LCH
 */

@Service
public class MessageListServiceImpl implements MessageListService {

	@Autowired
	private MessageListRepository messageListRepository;
	
	@Autowired
	private AuthenticationService authenticationService;

	@Override
	public MessageListsDTO searchByUser() {
		User currentUser = authenticationService.getCurrentUser();
		List<MessageList> messageLists = messageListRepository.findByUserId(currentUser.getId());
		return toDTO(messageLists);
	}

	private MessageListsDTO toDTO(List<MessageList> messageLists) {
		MessageListsDTO messageListsDTO = new MessageListsDTO();
		List<MessageListDTO> messageListDTOs = new ArrayList<MessageListDTO>();
		for (MessageList messageList : messageLists)
			messageListDTOs.add(messageList.messageListDTO());
		messageListsDTO.setMessageLists(messageListDTOs);
		return messageListsDTO;
	}

}
