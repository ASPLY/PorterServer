package org.a_sply.porter.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.a_sply.porter.dto.email.CheckEmailDTO;
import org.a_sply.porter.dto.user.CheckNameDTO;
import org.a_sply.porter.dto.user.CreateUserDTO;
import org.a_sply.porter.services.UserService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.MockitoAnnotations.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {ApplicationContext.class})
//@WebAppConfiguration
public class UserControllerTest {

	private static final String LEE = "Lee";
	private static final String LOGN_PASSWORD_LENGTH = UnitTestUtil.createStringWithLength(20);
	private static final String SHORHT_PASSWORD_LENGTH = UnitTestUtil.createStringWithLength(4);
	private static final String CORRECT_PASSWORD = UnitTestUtil.createStringWithLength(9);
	private static final String INCORRECT_PASSWORD = UnitTestUtil.createStringWithLength(9);
	private static final String EMPTY = "";
	private static final String _010_555_1111 = "010-555-1111";
	private static final String KD980311 = "kd980311";
	private static final String KD980311_NAVER_COM = "kd980311@naver.com";
	private static final String TELEPHONE = "telephone";
	private static final String PASSWORD = "password";
	private static final String EMAIL = "email";
	private static final String NAME = "name";

	private MockMvc mockMvc;

	// @Resource
	// private WebApplicationContext webApplicationContext;

	@InjectMocks
	private UserController userController;

	@Mock
	private UserService userService;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
	}

	@Test
	public void create_성공() throws Exception {
		mockMvc.perform(
				post("/users").param(NAME, LEE)
						.param(EMAIL, KD980311_NAVER_COM)
						.param(PASSWORD, CORRECT_PASSWORD)
						.param(TELEPHONE, _010_555_1111))
				.andExpect(status().isOk()).andDo(print());
		verify(userService, times(1)).createUser(
				Mockito.any(CreateUserDTO.class));
	}

	@Test
	public void create_실패_이메일_잘못된_형식() throws Exception {
		mockMvc.perform(
				post("/users").param(NAME, LEE).param(EMAIL, KD980311)
						.param(PASSWORD, CORRECT_PASSWORD)
						.param(TELEPHONE, EMPTY))
				.andExpect(status().isBadRequest())
				.andExpect(
						content().contentType(
								UnitTestUtil.APPLICATION_JSON_UTF8))
				.andDo(print());
		verify(userService, times(0)).createUser(
				Mockito.any(CreateUserDTO.class));
	}

	@Test
	public void create_실패_전화번호_빈경우() throws Exception {
		mockMvc.perform(
				post("/users").param(NAME, LEE)
						.param(EMAIL, KD980311_NAVER_COM)
						.param(PASSWORD, CORRECT_PASSWORD)
						.param(TELEPHONE, EMPTY))
				.andExpect(status().isBadRequest())
				.andExpect(
						content().contentType(
								UnitTestUtil.APPLICATION_JSON_UTF8))
				.andDo(print());
		verify(userService, times(0)).createUser(
				Mockito.any(CreateUserDTO.class));
	}

	@Test
	public void create_실패_패스워드_짧은_길이() throws Exception {
		mockMvc.perform(
				post("/users").param(NAME, LEE)
						.param(EMAIL, KD980311_NAVER_COM)
						.param(PASSWORD, SHORHT_PASSWORD_LENGTH)
						.param(TELEPHONE, _010_555_1111))
				.andExpect(status().isBadRequest())
				.andExpect(
						content().contentType(
								UnitTestUtil.APPLICATION_JSON_UTF8))
				.andDo(print());
		verify(userService, times(0)).createUser(
				Mockito.any(CreateUserDTO.class));
	}

	@Test
	public void create_실패_패스워드_긴_길이() throws Exception {
		mockMvc.perform(
				post("/users").param(NAME, LEE)
						.param(EMAIL, KD980311_NAVER_COM)
						.param(PASSWORD, LOGN_PASSWORD_LENGTH)
						.param(TELEPHONE, _010_555_1111))
				.andExpect(status().isBadRequest())
				.andExpect(
						content().contentType(
								UnitTestUtil.APPLICATION_JSON_UTF8))
				.andDo(print());
		verify(userService, times(0)).createUser(
				Mockito.any(CreateUserDTO.class));
	}

	@Test
	public void checkEmail_실패_이메일() throws Exception {
		CheckEmailDTO checkEmailDTO = new CheckEmailDTO();
		checkEmailDTO.setEmail(KD980311_NAVER_COM);

		when(userService.check(checkEmailDTO)).thenReturn(false);

		mockMvc.perform(
				post("/users/check/email").param("email", KD980311_NAVER_COM))
				.andExpect(status().isBadRequest()).andDo(print());
		verify(userService, times(1)).check(checkEmailDTO);
	}

	@Test
	public void checkEmail_실패_이메일_형식_문제() throws Exception {
		CheckEmailDTO checkEmailDTO = new CheckEmailDTO();
		checkEmailDTO.setEmail(KD980311);

		when(userService.check(checkEmailDTO)).thenReturn(false);

		mockMvc.perform(post("/users/check/email").param("email", KD980311))
				.andExpect(status().isBadRequest()).andDo(print());
		verify(userService, times(0)).check(checkEmailDTO);
	}

	@Test
	public void checkEmail_성공() throws Exception {
		CheckEmailDTO checkEmailDTO = new CheckEmailDTO();
		checkEmailDTO.setEmail(KD980311_NAVER_COM);

		when(userService.check(checkEmailDTO)).thenReturn(true);

		mockMvc.perform(
				post("/users/check/email").param("email", KD980311_NAVER_COM))
				.andExpect(status().isOk()).andDo(print());
		verify(userService, times(1)).check(checkEmailDTO);
	}

	@Test
	public void checkName_성공() throws Exception {
		CheckNameDTO checkNameDTO = new CheckNameDTO();
		checkNameDTO.setName(LEE);

		when(userService.check(checkNameDTO)).thenReturn(true);

		mockMvc.perform(post("/users/check/name").param("name", LEE))
				.andExpect(status().isOk()).andDo(print());
		verify(userService).check(checkNameDTO);
	}

	@Test
	public void login_성공() throws Exception {
//		LoginUserDTO loginUserDTO = new LoginUserDTO();
//		loginUserDTO.setEmail(KD980311_NAVER_COM);
//		loginUserDTO.setPassword(CORRECT_PASSWORD);
//
//		when(userService.login(loginUserDTO)).thenReturn(true);
//		String apiKeyValue = "value";
//		when(apiKeyService.createApiKey(KD980311_NAVER_COM)).thenReturn(new CreatedApiKeyDTO(apiKeyValue));
//
//		mockMvc.perform(
//				post("/users/login").param("email", KD980311_NAVER_COM).param("password", CORRECT_PASSWORD))
//				.andExpect(status().isOk())
//				.andExpect(jsonPath("$.apiKey", is(apiKeyValue)))
//				.andExpect(content().contentType(UnitTestUtil.APPLICATION_JSON_UTF8))
//				.andDo(print());
//
//		verify(userService, times(1)).login(loginUserDTO);
//		verify(apiKeyService, times(1)).createApiKey(KD980311_NAVER_COM);
	}

	@Test
	public void login_실패_틀린_아이디_패스워드() throws Exception {
//		// when
//		LoginUserDTO loginUserDTO = new LoginUserDTO();
//		loginUserDTO.setEmail(KD980311_NAVER_COM);
//		loginUserDTO.setPassword(INCORRECT_PASSWORD);
//
//		when(userService.login(loginUserDTO)).thenReturn(false);
//
//		// given
//		mockMvc.perform(
//				post("/users/login").param("email", KD980311_NAVER_COM).param(
//						"password", INCORRECT_PASSWORD))
//				.andExpect(status().isNotFound()).andDo(print());
//
//		// then
//		verify(userService, times(1)).login(loginUserDTO);
//		verify(apiKeyService, times(0)).createApiKey(Mockito.anyString());
	}

	@Test
	public void logout_실패_빈값() throws Exception {
//		// when
//		LogoutUserDTO logoutUserDTO = new LogoutUserDTO();
//
//		// given
//		mockMvc.perform(
//				post("/users/logout").param("apiKey", logoutUserDTO.getApiKey()))
//				.andExpect(status().isBadRequest()).andDo(print());
//
//		// then
	}

	@Test
	public void logout_성공() throws Exception {
//		// when
//		LogoutUserDTO logoutUserDTO = new LogoutUserDTO();
//		logoutUserDTO.setApiKey("apiKeyValue");
//
//		// given
//		mockMvc.perform(
//				post("/users/logout").param("apiKey", logoutUserDTO.getApiKey()))
//				.andExpect(status().isOk()).andDo(print());
//
//		// then
//		verify(apiKeyService).removeApiKey(logoutUserDTO.getApiKey());
//		;
	}
}
