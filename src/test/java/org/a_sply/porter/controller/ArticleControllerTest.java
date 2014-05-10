package org.a_sply.porter.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.a_sply.porter.dto.article.CreateArticleDTO;
import org.a_sply.porter.services.ArticleService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class ArticleControllerTest {

	private static final int ID = 1;

	private MockMvc mockMvc;

	@InjectMocks
	private ArticleController articleController;

	@Mock
	private ArticleService articleService;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(articleController).build();
	}

	@Test
	public void create_성공() throws Exception {
		// when
		CreateArticleDTO createArticleDTO = UnitTestUtil.createArticleDTO();
		when(articleService.create(createArticleDTO)).thenReturn(UnitTestUtil.createdArticleDTO());

		// given
		mockMvc.perform(
				fileUpload("/articles")
						.file("imageFiles",createArticleDTO.getImageFiles()[0].getBytes())
						.file("imageFiles",createArticleDTO.getImageFiles()[1].getBytes())
						.param("name", createArticleDTO.getName())
						.param("middleCategory",createArticleDTO.getMiddleCategory())
						.param("largeCategory",createArticleDTO.getLargeCategory())
						.param("keywords", createArticleDTO.getKeywords()[0])
						.param("keywords", createArticleDTO.getKeywords()[1])
						.param("price", createArticleDTO.getPrice())
						.param("maker", createArticleDTO.getMaker())
						.param("state", createArticleDTO.getState())
						.param("quantity", createArticleDTO.getQuantity())
						.param("description", createArticleDTO.getDescription()))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", notNullValue()))
				.andExpect(jsonPath("$.user", notNullValue()))
				.andExpect(jsonPath("$.part", notNullValue()))
				.andExpect(jsonPath("$.isSold", is(false))).andDo(print());

		// then
		verify(articleService, times(1)).create(createArticleDTO);
	}

	@Test
	public void get_성공() throws Exception {
		// when
		when(articleService.get(ID)).thenReturn(UnitTestUtil.articleDTO());
		// given
		mockMvc.perform(get("/articles/" + ID)).andExpect(status().isOk())
				.andExpect(jsonPath("$.user", notNullValue()))
				.andExpect(jsonPath("$.part", notNullValue()))
				.andExpect(jsonPath("$.isSold", is(false)))
				.andExpect(status().isOk()).andDo(print());

		// then
		verify(articleService, times(1)).get(ID);
	}

	@Test
	public void get_실패_없는ID() throws Exception {
		// when
		when(articleService.get(ID)).thenReturn(null);

		// given
		mockMvc.perform(get("/articles/" + ID))
				.andExpect(status().isBadRequest()).andDo(print());

		// then
		verify(articleService, times(1)).get(ID);
	}

	@Test
	public void sold_성공() throws Exception {
		// when
		when(articleService.sold(ID)).thenReturn(true);

		// given
		mockMvc.perform(get("/articles/" + ID + "/sold"))
				.andExpect(status().isOk()).andDo(print());

		// then
		verify(articleService, times(1)).sold(ID);
	}

	@Test
	public void sold_없는ID() throws Exception {
		// when
		when(articleService.sold(ID)).thenReturn(false);

		// given
		mockMvc.perform(get("/articles/" + ID + "/sold"))
				.andExpect(status().isBadRequest()).andDo(print());

		// then
		verify(articleService, times(1)).sold(ID);
	}
}
