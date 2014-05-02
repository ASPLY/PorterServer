package org.a_sply.porter.services;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.a_sply.porter.controller.UnitTestUtil;
import org.a_sply.porter.domain.Message;
import org.a_sply.porter.domain.User;
import org.a_sply.porter.dto.message.SendMessageDTO;
import org.a_sply.porter.repository.MessageRepository;
import org.a_sply.porter.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class MessageServiceTest {

	@InjectMocks
	private MessageService messageService = new MessageServiceImpl();

	@Mock
	private MessageRepository messageRepository;

	@Mock
	private UserRepository userRepository;
	
	@Mock
	private AuthenticationService authenticationService;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void send_성공() {
		// when
		SendMessageDTO sendMessageDTO = UnitTestUtil.sendMessageDTO();
		User userA = UnitTestUtil.userA();
		when(userRepository.findByEmail("kd980311@naver.com")).thenReturn(userA);
		when(userRepository.findByName(sendMessageDTO.getTo())).thenReturn(UnitTestUtil.userB());
		when(authenticationService.getCurrentUser()).thenReturn(userA);

		// given
		messageService.send(sendMessageDTO);

		// then
		verify(messageRepository).save(Matchers.<Message> any());
	}

	@Test
	public void remove_성공() {
		// when
		int id = 1;

		// given
		messageService.remove(id);

		// then
		verify(messageRepository).delete(id);
	}
}
