package org.a_sply.porter.integrate;

import javax.annotation.Resource;

import org.a_sply.porter.config.CoreConfig;
import org.a_sply.porter.config.MVCConfig;
import org.a_sply.porter.config.SecurityConfig;
import org.a_sply.porter.config.TestMVCConfig;
import org.a_sply.porter.controller.UnitTestUtil;
import org.a_sply.porter.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestMVCConfig.class, CoreConfig.class, SecurityConfig.class})
@WebAppConfiguration
public class MessageListControllerIntegrateTest {

	private MockMvc mockMvc;
	private IntegrateTestUtil integrateTestUtil;

	@Resource
	private WebApplicationContext webApplicationContext;
//	private CreatedApiKeyDTO createdUserAApiKeyDTO;
//	private CreatedApiKeyDTO createdUserBApiKeyDTO;
	private User userB;

	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.build();
		integrateTestUtil = new IntegrateTestUtil(mockMvc);

		User userA = UnitTestUtil.userA();
		integrateTestUtil.createUser(UnitTestUtil.createUserDTO(userA));
//		createdUserAApiKeyDTO = integrateTestUtil.loginUser(UnitTestUtil.loginUserDTO(userA));

		userB = UnitTestUtil.userB();
		integrateTestUtil.createUser(UnitTestUtil.createUserDTO(userB));
//		createdUserBApiKeyDTO = integrateTestUtil.loginUser(UnitTestUtil.loginUserDTO(userB));

		integrateTestUtil.createUser(UnitTestUtil.createUserDTO(userB));
	}

	@Test
	public void user_성공_2개() throws Exception {
//		int count = 2;
//		for (int i = 0; i < count; i++) {
//			integrateTestUtil.sendMessage(UnitTestUtil.sendMessageDTO(userB.getName()));
//		}
//		// given
//
//		// when
//		mockMvc.perform(
//				post("/messageLists/user").param("apiKey", requestMyMessageDTO.getApiKey())).andDo(print())
//				.andExpect(jsonPath("$.messageLists", hasSize(count)))
//				.andExpect(status().isOk());
	}

	@Test
	public void user_성공_0개() throws Exception {
//		// given
//
//		// when
//		mockMvc.perform(
//				post("/messageLists/user").param("apiKey",requestMyMessageDTO.getApiKey())).andDo(print())
//				.andExpect(jsonPath("$.messageLists", hasSize(0)))
//				.andExpect(status().isOk());
	}
}
