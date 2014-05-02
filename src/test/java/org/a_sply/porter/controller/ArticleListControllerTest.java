package org.a_sply.porter.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.a_sply.porter.dto.article_list.RequestArticleListsDTO;
import org.a_sply.porter.dto.article_list.SearchArticleListDTO;
import org.a_sply.porter.services.ArticleListService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.MockitoAnnotations.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class ArticleListControllerTest {

	private MockMvc mockMvc;

	@InjectMocks
	private ArticleListController partListController;

	@Mock
	private ArticleListService partListService;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(partListController).build();
	}

	@Test
	public void search_성공_결과2개() throws Exception {
		// when
		SearchArticleListDTO searchPartListDTO = UnitTestUtil
				.searchArticleListDTO();
		when(partListService.search(searchPartListDTO)).thenReturn(
				UnitTestUtil.partListsDTO(2));

		// givn
		mockMvc.perform(
				post("/articleLists")
						.param("count",String.valueOf(searchPartListDTO.getCount()))
						.param("keyword", searchPartListDTO.getKeyword())
						.param("largeCategory",searchPartListDTO.getLargeCategory())
						.param("middleCategory",searchPartListDTO.getMiddleCategory()))

		.andExpect(jsonPath("$.articleLists", hasSize(2)))
				.andExpect(status().isOk()).andDo(print());
		// then
		verify(partListService, times(1)).search(searchPartListDTO);
	}

	@Test
	public void search_성공_결과0개() throws Exception {
		// when
		SearchArticleListDTO searchPartListDTO = UnitTestUtil
				.searchArticleListDTO();
		when(partListService.search(searchPartListDTO)).thenReturn(
				UnitTestUtil.partListsDTO(0));

		// givn
		mockMvc.perform(
				post("/articleLists")
						.param("count",
								String.valueOf(searchPartListDTO.getCount()))
						.param("keyword", searchPartListDTO.getKeyword())
						.param("largeCategory",
								searchPartListDTO.getLargeCategory())
						.param("middleCategory",
								searchPartListDTO.getMiddleCategory()))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.articleLists", hasSize(0)));
		// then
		verify(partListService, times(1)).search(searchPartListDTO);
	}

	@Test
	public void requestMyArticleList_성공_결과2개() throws Exception {
		// when
		when(partListService.searchByUser()).thenReturn(UnitTestUtil.partListsDTO(2));

		// givn
		mockMvc.perform(
				post("/articleLists/user")).andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.articleLists", hasSize(2)));
		// then

		verify(partListService, times(1)).searchByUser();
	}

	@Test
	public void get_성공() throws Exception {
		// when
		RequestArticleListsDTO getArticleListsDTO = UnitTestUtil
				.getArticleListsDTO();
		when(partListService.get(getArticleListsDTO)).thenReturn(
				UnitTestUtil.partListsDTO(2));

		// givn
		mockMvc.perform(
				get("/articleLists")
						.param("largeCategory",String.valueOf(getArticleListsDTO.getLargeCategory()))
						.param("middleCategory", String.valueOf(getArticleListsDTO.getMiddleCategory()))
						.param("count",String.valueOf(getArticleListsDTO.getCount()))
						.param("offset",String.valueOf(getArticleListsDTO.getOffset())))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.articleLists", hasSize(2)));
		
		// then
		verify(partListService, times(1)).get(getArticleListsDTO);
	}
}
