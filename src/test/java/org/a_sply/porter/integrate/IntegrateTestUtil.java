package org.a_sply.porter.integrate;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.a_sply.porter.controller.UnitTestUtil;
import org.a_sply.porter.dto.article.CreateArticleDTO;
import org.a_sply.porter.dto.article.CreatedArticleDTO;
import org.a_sply.porter.dto.message.MessageListsDTO;
import org.a_sply.porter.dto.message.SendMessageDTO;
import org.a_sply.porter.dto.user.CreateUserDTO;
import org.a_sply.porter.util.DateFormat;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author Petri Kainulainen
 */
public class IntegrateTestUtil {

	private MockMvc mockMvc;
	private Gson gson = new Gson();

	public IntegrateTestUtil(MockMvc mockMvc) {
		super();
		this.mockMvc = mockMvc;
	}

	public void createUser(CreateUserDTO createUserDTO) throws Exception {
		mockMvc.perform(
				post("/users").param("email", createUserDTO.getEmail())
						.param("password", createUserDTO.getPassword())
						.param("telephone", createUserDTO.getTelephone())
						.param("name", createUserDTO.getName())).andDo(print());
	}

//	public CreatedApiKeyDTO loginUser(LoginUserDTO loginUserDTO) throws Exception {
//		MvcResult mvcResult = mockMvc
//				.perform(
//						post("/users/login").param("email",
//								loginUserDTO.getEmail()).param("password",
//								loginUserDTO.getPassword()))
//				.andExpect(status().isOk())
//				.andExpect(content().contentType(UnitTestUtil.APPLICATION_JSON_UTF8))
//				.andDo(print()).andReturn();
//		return gson.fromJson(mvcResult.getResponse().getContentAsString(), CreatedApiKeyDTO.class);
//	}

	public CreatedArticleDTO createArticle(CreateArticleDTO createArticleDTO, String buildBasicAuthHeaderValue) throws Exception {
		// given
		MvcResult mvcResult = mockMvc
				.perform(
						fileUpload("/articles")
								.file("imageFiles", createArticleDTO.getImageFiles()[0].getBytes())
								.file("imageFiles", createArticleDTO.getImageFiles()[1].getBytes())
								.header("Authorization", buildBasicAuthHeaderValue)
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
				.andExpect(jsonPath("$.isSold", is(false))).andDo(print())
				.andReturn();

		return gson.fromJson(mvcResult.getResponse().getContentAsString(), CreatedArticleDTO.class);
	}

	public void sendMessage(SendMessageDTO sendMessageDTO, String buildBasicAuthHeaderValue) throws Exception {
		mockMvc.perform(
				post("/messages/send")
						.header("Authorization", buildBasicAuthHeaderValue)
						.param("to", sendMessageDTO.getTo())
						.param("content", sendMessageDTO.getContent()))
				.andExpect(status().isOk()).andDo(print());
	}

	public MessageListsDTO getMessageList(String buildBasicAuthHeaderValue) throws Exception {
		MvcResult mvcResult = mockMvc
				.perform(post("/messageLists/user")
				.header("Authorization", buildBasicAuthHeaderValue))
				.andDo(print()).andExpect(status().isOk()).andReturn();

		Gson gson = new GsonBuilder().setDateFormat(DateFormat.pattern())
				.create();
		return gson.fromJson(mvcResult.getResponse().getContentAsString(),
				MessageListsDTO.class);
	}
}
