package org.a_sply.porter.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.a_sply.porter.services.MessageListService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class MessageListControllerTest {

	private MockMvc mockMvc;

	@InjectMocks
	private MessageListController messageListController;

	@Mock
	private MessageListService messageListService;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(messageListController).build();
	}

	@Test
	public void requsetMyMessage_성공() throws Exception {
		// given
		int count = 2;
		when(messageListService.searchByUser()).thenReturn(UnitTestUtil.messageListsDTO(count));

		// when
		mockMvc.perform(
				post("/messageLists/user")).andDo(print())
				.andExpect(jsonPath("$.messageLists", hasSize(count)))
				.andExpect(status().isOk());
		// then
		verify(messageListService).searchByUser();
	}

}
