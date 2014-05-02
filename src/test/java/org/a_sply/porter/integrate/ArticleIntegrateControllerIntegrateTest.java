package org.a_sply.porter.integrate;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;

import javax.annotation.Resource;

import org.a_sply.porter.config.CoreConfig;
import org.a_sply.porter.config.MVCConfig;
import org.a_sply.porter.config.SecurityConfig;
import org.a_sply.porter.controller.UnitTestUtil;
import org.a_sply.porter.domain.User;
import org.a_sply.porter.dto.article.CreateArticleDTO;
import org.a_sply.porter.dto.article.CreatedArticleDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.support.MultipartFilter;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MVCConfig.class, CoreConfig.class, SecurityConfig.class})
@WebAppConfiguration
public class ArticleIntegrateControllerIntegrateTest {

	private static final int INCORRECT_ID = -1;
	private MockMvc mockMvc;
	private IntegrateTestUtil integrateTestUtil;

	@Resource
	private WebApplicationContext webApplicationContext;
	private CreatedArticleDTO createdArticleDTO;
	private User userA;
	
	@Resource
    private FilterChainProxy springSecurityFilterChain;

	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).addFilter(springSecurityFilterChain).build();
		integrateTestUtil = new IntegrateTestUtil(mockMvc);

		userA = UnitTestUtil.userA();
		integrateTestUtil.createUser(UnitTestUtil.createUserDTO(userA));
		//createdArticleDTO = integrateTestUtil.createArticle(UnitTestUtil.createArticleDTO(), UnitTestUtil.buildBasicAuthHeaderValue(userA));
	}

	@Test
	public void create_성공() throws Exception {
		// when
		CreateArticleDTO createArticleDTO = UnitTestUtil.createArticleDTO();

		HashMap<String, String> contentTypeParams = new HashMap<String, String>();
        contentTypeParams.put("boundary", "---"+System.currentTimeMillis()+"---");
        MediaType mediaType = new MediaType("multipart", "form-data", contentTypeParams);
		
		// given
		mockMvc.perform(
				fileUpload("/articles")
						.file("imageFiles", createArticleDTO.getImageFiles()[0].getBytes())
						.file("imageFiles", createArticleDTO.getImageFiles()[1].getBytes())
						.contentType(mediaType)
						.header("Authorization", UnitTestUtil.buildBasicAuthHeaderValue(userA))
						.param("name", createArticleDTO.getName())
						.param("middleCategory", createArticleDTO.getMiddleCategory())
						.param("largeCategory", createArticleDTO.getLargeCategory())
						.param("keywords", createArticleDTO.getKeywords()[0])
						.param("keywords", createArticleDTO.getKeywords()[1])
						.param("price", createArticleDTO.getPrice())
						.param("maker", createArticleDTO.getMaker())
						.param("state", createArticleDTO.getState())
						.param("quantity", createArticleDTO.getQuantity())
						.param("region", createArticleDTO.getRegion())
						.param("description", createArticleDTO.getDescription())
						)
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", notNullValue()))
				.andExpect(jsonPath("$.user", notNullValue()))
				.andExpect(jsonPath("$.part", notNullValue()))
				.andExpect(jsonPath("$.isSold", is(false))).andDo(print());
		// then
	}

	@Test
	public void create_실패_인증실패() throws Exception {
		// when
		CreateArticleDTO createArticleDTO = UnitTestUtil.createArticleDTO();

		// given
		mockMvc.perform(
				fileUpload("/articles")
						.file("imageFiles", createArticleDTO.getImageFiles()[0].getBytes())
						.file("imageFiles", createArticleDTO.getImageFiles()[1].getBytes())
						.param("name", createArticleDTO.getName())
						.param("middleCategory", createArticleDTO.getMiddleCategory())
						.param("largeCategory", createArticleDTO.getLargeCategory())
						.param("keywords", createArticleDTO.getKeywords()[0])
						.param("keywords", createArticleDTO.getKeywords()[1])
						.param("price", createArticleDTO.getPrice())
						.param("maker", createArticleDTO.getMaker())
						.param("state", createArticleDTO.getState())
						.param("quantity", createArticleDTO.getQuantity())
						.param("region", createArticleDTO.getRegion())
						.param("description", createArticleDTO.getDescription())
						.contentType(MediaType.APPLICATION_FORM_URLENCODED))
				.andExpect(status().isUnauthorized()).andDo(print());

		// then
	}

	@Test
	public void get_성공() throws Exception {
		// when
		// given
		mockMvc.perform(get("/articles/" + createdArticleDTO.getId()))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.user", notNullValue()))
				.andExpect(jsonPath("$.part", notNullValue()))
				.andExpect(jsonPath("$.isSold", is(false)))
				.andExpect(status().isOk()).andDo(print());
		// then
	}

	@Test
	public void get_실패_없는ID() throws Exception {
		// when

		// given
		mockMvc.perform(get("/articles/" + INCORRECT_ID))
				.andExpect(status().isBadRequest()).andDo(print());

		// then
	}

	@Test
	public void sold_성공() throws Exception {
		// when

		// given
		mockMvc.perform(get("/articles/" + createdArticleDTO.getId() + "/sold"))
				.andExpect(status().isOk()).andDo(print());

		// then
	}

	@Test
	public void sold_없는ID() throws Exception {
		// when

		// given
		mockMvc.perform(get("/articles/" + INCORRECT_ID + "/sold"))
				.andExpect(status().isBadRequest()).andDo(print());

		// then
	}
}
