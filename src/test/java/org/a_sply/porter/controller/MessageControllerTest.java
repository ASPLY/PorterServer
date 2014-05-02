package org.a_sply.porter.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.a_sply.porter.dto.message.SendMessageDTO;
import org.a_sply.porter.services.MessageService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class MessageControllerTest {

	private MockMvc mockMvc;

	@InjectMocks
	private MessageController messageController;

	@Mock
	private MessageService messageService;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(messageController).build();
	}

	@Test
	public void send_성공() throws Exception {
		// given
		SendMessageDTO sendMessageDTO = UnitTestUtil.sendMessageDTO();

		// when
		mockMvc.perform(
				post("/messages/send")
						.param("to", sendMessageDTO.getTo())
						.param("content", sendMessageDTO.getContent()))
				.andDo(print()).andExpect(status().isOk());

		// then
		verify(messageService).send(sendMessageDTO);
	}

	@Test
	public void send_실패_빈값() throws Exception {
		// given
		SendMessageDTO sendMessageDTO = UnitTestUtil.sendMessageDTO();
		sendMessageDTO.setContent(null);

		// when
		mockMvc.perform(
				post("/messages/send")
						.param("from", sendMessageDTO.getTo())
						.param("content", sendMessageDTO.getContent()))
				.andExpect(status().isBadRequest()).andDo(print());

		// then
		verifyNoMoreInteractions(messageService);
	}

	@Test
	public void delete_성공() throws Exception {
		// given
		int id = 1;

		// when
		mockMvc.perform(delete("/messages/" + id)).andExpect(status().isOk())
				.andDo(print());

		// then
		verify(messageService).remove(id);
	}

}
