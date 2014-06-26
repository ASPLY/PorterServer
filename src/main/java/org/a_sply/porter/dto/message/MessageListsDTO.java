package org.a_sply.porter.dto.message;

import java.util.List;

public class MessageListsDTO {

	private List<MessageListDTO> messageLists;

	public MessageListsDTO() {
	}

	public List<MessageListDTO> getMessageLists() {
		return messageLists;
	}

	public void setMessageLists(List<MessageListDTO> messageLists) {
		this.messageLists = messageLists;
	}

	public int size() {
		return messageLists.size();
	}

}
