package org.a_sply.porter.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.a_sply.porter.services.BasketService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class BasketControllerTest {

	private MockMvc mockMvc;

	@InjectMocks
	private BasketController basketController;

	@Mock
	private BasketService basketService;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(basketController).build();
	}

	@Test
	public void put_성공() throws Exception {
		int articleId = 1;

		// given
		mockMvc.perform(
				post("/basket")
				.param("articleId", String.valueOf(articleId)))
				.andExpect(status().isOk()).andDo(print());

		// then
		verify(basketService, times(1)).put(articleId);
	}
}
