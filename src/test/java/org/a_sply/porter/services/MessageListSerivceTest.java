package org.a_sply.porter.services;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.a_sply.porter.controller.UnitTestUtil;
import org.a_sply.porter.domain.MessageList;
import org.a_sply.porter.domain.User;
import org.a_sply.porter.dto.message.MessageListsDTO;
import org.a_sply.porter.repository.MessageListRepository;
import org.a_sply.porter.services.Impl.MessageListServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class MessageListSerivceTest {

	@InjectMocks
	private MessageListService messageListService = new MessageListServiceImpl();

	@Mock
	private MessageListRepository messageListRepository;
	
	@Mock
	private AuthenticationService authenticationService;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void searchByUserId_성공() {
		// given
		List<MessageList> messages = new ArrayList<MessageList>();
		messages.add(UnitTestUtil.messageList());
		User userA = UnitTestUtil.userA();
		when(authenticationService.getCurrentUser()).thenReturn(userA);
		when(messageListRepository.findByUserId(userA.getId())).thenReturn(messages);

		// when
		MessageListsDTO expected = messageListService.searchByUser();

		// then
		assertThat(messages.size(), is(expected.size()));
		verify(messageListRepository).findByUserId(userA.getId());
	}

}
