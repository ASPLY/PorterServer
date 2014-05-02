package com.porter.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.plugins.MockMaker;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.porter.web.UnitTestUtil.APPLICATION_JSON_UTF8;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;


public class CategoryControllerTest {
	
	private MockMvc mockMvc;
	private CategoryController categoryController;
	
	@Before
    public void setUp() {
		categoryController = new CategoryController();
        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
    }
	
	@Test
	public void getAll_200() throws Exception{
		mockMvc.perform(get("/categorys"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.1000", is("외장부품")))
        	.andExpect(content().contentType(APPLICATION_JSON_UTF8));
	}
	
}
