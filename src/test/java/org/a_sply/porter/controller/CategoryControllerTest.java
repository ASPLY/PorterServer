package org.a_sply.porter.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class CategoryControllerTest {

	private MockMvc mockMvc;
	private CategoryController categoryController;

	@Before
	public void setUp() {
		categoryController = new CategoryController();
		mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
	}

	@Test
	public void getAll_200() throws Exception {
		mockMvc.perform(get("/categorys"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.1000", is("외장부품")))
				.andExpect(
						content().contentType(
								UnitTestUtil.APPLICATION_JSON_UTF8));
	}

}
