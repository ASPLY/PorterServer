package org.a_sply.porter.integrate;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.a_sply.porter.config.CoreConfig;
import org.a_sply.porter.config.SecurityConfig;
import org.a_sply.porter.config.TestMVCConfig;
import org.a_sply.porter.controller.UnitTestUtil;
import org.a_sply.porter.domain.User;
import org.a_sply.porter.dto.article.CreatedArticleDTO;
import org.a_sply.porter.dto.article_list.RequestArticleListsDTO;
import org.a_sply.porter.dto.article_list.SearchArticleListDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestMVCConfig.class, CoreConfig.class, SecurityConfig.class})
@WebAppConfiguration
public class ArticleListControllerIntegrateTest {

	private static final int INSERT_COUNT = 3;
	private MockMvc mockMvc;
	private IntegrateTestUtil integrateTestUtil;

	@Autowired
	private WebApplicationContext webApplicationContext;
	
	private CreatedArticleDTO createdArticleDTO;
	
	@Autowired
    private FilterChainProxy springSecurityFilterChain;
	private User userA;
	

	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).addFilter(springSecurityFilterChain).build();
		integrateTestUtil = new IntegrateTestUtil(mockMvc);

		userA = UnitTestUtil.userA();
		integrateTestUtil.createUser(UnitTestUtil.createUserDTO(userA));
		for (int i = 0; i < INSERT_COUNT; i++)
			createdArticleDTO = integrateTestUtil.createArticle(UnitTestUtil.createArticleDTO(), UnitTestUtil.buildBasicAuthHeaderValue(userA));
		
	}

	@Test
	public void search_성공_결과2개() throws Exception {
		// when
		SearchArticleListDTO searchPartListDTO = UnitTestUtil.searchArticleListDTO(createdArticleDTO.getPart());

		// given
		mockMvc.perform(
				post("/articleLists")
						.param("count", String.valueOf(searchPartListDTO.getCount()))
						.param("keyword", searchPartListDTO.getKeyword())
						.param("largeCategory", searchPartListDTO.getLargeCategory())
						.param("middleCategory", searchPartListDTO.getMiddleCategory()))
				.andDo(print())
				.andExpect(jsonPath("$.articleLists", hasSize(searchPartListDTO.getCount())))
				.andExpect(status().isOk());
		// then
	}

	@Test
	public void search_성공_결과0개() throws Exception {
		// when
		SearchArticleListDTO searchPartListDTO = UnitTestUtil.searchArticleListDTO("틀린검색어");

		// givn
		mockMvc.perform(
				post("/articleLists")
						.param("count", String.valueOf(searchPartListDTO.getCount()))
						.param("keyword", searchPartListDTO.getKeyword())
						.param("largeCategory", searchPartListDTO.getLargeCategory())
						.param("middleCategory", searchPartListDTO.getMiddleCategory()))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.articleLists", hasSize(0)));
		// then
	}

	@Test
	public void requestMyArticleList_성공_결과INSERT_COUNT개() throws Exception {
		// when

		// givn
		mockMvc.perform(
				post("/articleLists/user")
				.header("Authorization", UnitTestUtil.buildBasicAuthHeaderValue(userA)))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.articleLists", hasSize(INSERT_COUNT)));
		// then

	}

	@Test
	public void get_성공() throws Exception {
		// when
		int expectedCount = 2;
		RequestArticleListsDTO getArticleListsDTO = UnitTestUtil.getArticleListsDTO(createdArticleDTO.getPart()
						.getMiddleCategory(), createdArticleDTO.getPart()
						.getLargeCategory(), expectedCount);

		// givn
		mockMvc.perform(
				get("/articleLists")
						.param("largeCategory", String.valueOf(getArticleListsDTO.getLargeCategory()))
						.param("middleCategory", String.valueOf(getArticleListsDTO.getMiddleCategory()))
						.param("count", String.valueOf(getArticleListsDTO.getCount()))
						.param("offset", String.valueOf(getArticleListsDTO.getOffset())))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.articleLists", hasSize(expectedCount)));
		// then

	}
}
