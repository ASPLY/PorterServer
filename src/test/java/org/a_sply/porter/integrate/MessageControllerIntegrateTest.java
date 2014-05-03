package org.a_sply.porter.integrate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.annotation.Resource;

import org.a_sply.porter.config.CoreConfig;
import org.a_sply.porter.config.MVCConfig;
import org.a_sply.porter.config.SecurityConfig;
import org.a_sply.porter.config.TestMVCConfig;
import org.a_sply.porter.controller.UnitTestUtil;
import org.a_sply.porter.domain.User;
import org.a_sply.porter.dto.message.MessageListsDTO;
import org.a_sply.porter.dto.message.SendMessageDTO;
import org.apache.commons.codec.binary.Base64;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
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
@ContextConfiguration(loader = WebContextLoader.class, classes = {TestMVCConfig.class, CoreConfig.class , SecurityConfig.class})
@WebAppConfiguration
public class MessageControllerIntegrateTest {

	private MockMvc mockMvc;
	private IntegrateTestUtil integrateTestUtil;
	
	@Resource
	private WebApplicationContext webApplicationContext;
//	private CreatedApiKeyDTO createdUserAApiKeyDTO;
//	private CreatedApiKeyDTO createdUserBApiKeyDTO;
	private User userB;
	private User userA;

	@Resource
    private FilterChainProxy springSecurityFilterChain;

	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).addFilter(springSecurityFilterChain).build();
		integrateTestUtil = new IntegrateTestUtil(mockMvc);

		userA = UnitTestUtil.userA();
		integrateTestUtil.createUser(UnitTestUtil.createUserDTO(userA));

		userB = UnitTestUtil.userB();
		integrateTestUtil.createUser(UnitTestUtil.createUserDTO(userB));
		
		integrateTestUtil.sendMessage(UnitTestUtil.sendMessageDTO(userB.getName()), UnitTestUtil.buildBasicAuthHeaderValue(userA));
	}

	@Test
	public void send_성공() throws Exception {
		// given
		SendMessageDTO sendMessageDTO = UnitTestUtil.sendMessageDTO(userB.getName());

		// when
		mockMvc.perform(
				post("/messages/send")
				.header("Authorization", UnitTestUtil.buildBasicAuthHeaderValue(userA))
						.param("to", sendMessageDTO.getTo())
						.param("content", sendMessageDTO.getContent()))
				.andDo(print()).andExpect(status().isOk());
	}

	@Ignore
	// send 후 id 값을 반환하지 않으므로 테스트 하기가 힘듬.
	public void get_성공() throws Exception {
		// when
		mockMvc.perform(get("/messages/3")).andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	public void delete_성공() throws Exception {
		
		// given
		String buildBasicAuthHeaderValue = UnitTestUtil.buildBasicAuthHeaderValue(userA);
		MessageListsDTO messageListsDTO = integrateTestUtil.getMessageList(buildBasicAuthHeaderValue);

		// when
		mockMvc.perform(
				delete("/messages/" + messageListsDTO.getMessageLists().get(0).getMessageId())
				.header("Authorization", buildBasicAuthHeaderValue))
				.andDo(print())
				.andExpect(status().isOk());
	}

}
