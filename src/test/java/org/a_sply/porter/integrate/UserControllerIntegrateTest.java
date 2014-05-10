package org.a_sply.porter.integrate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.annotation.Resource;

import org.a_sply.porter.config.CoreConfig;
import org.a_sply.porter.config.SecurityConfig;
import org.a_sply.porter.config.TestMVCConfig;
import org.a_sply.porter.controller.UnitTestUtil;
import org.a_sply.porter.domain.User;
import org.a_sply.porter.dto.email.CheckEmailDTO;
import org.a_sply.porter.dto.user.LoginUserDTO;
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
public class UserControllerIntegrateTest {

	private static final String LEE = "LEE";
	private static final String LOGN_PASSWORD_LENGTH = UnitTestUtil.createStringWithLength(20);
	private static final String SHORHT_PASSWORD_LENGTH = UnitTestUtil.createStringWithLength(4);
	private static final String CORRECT_PASSWORD = UnitTestUtil.createStringWithLength(9);
	private static final String INCORRECT_PASSWORD = UnitTestUtil.createStringWithLength(10);
	private static final String EMPTY = "";
	private static final String _010_555_1111 = "010-555-1111";
	private static final String KD980311 = "kd980311";
	private static final String KD980311_NAVER_COM = "kd980311@naver.com";
	private static final String KD980311_NATE_COM = "kd980311@nate.com";
	private static final String TELEPHONE = "telephone";
	private static final String PASSWORD = "password";
	private static final String EMAIL = "email";
	private static final String NAME = "name";

	private MockMvc mockMvc;

	@Resource
	private WebApplicationContext webApplicationContext;
	private IntegrateTestUtil integrateTestUtil;
	
	

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		integrateTestUtil = new IntegrateTestUtil(mockMvc);
	}

	@Test
	public void create_성공() throws Exception {
		mockMvc.perform(
				post("/users")
				.param(NAME, LEE)
				.param(EMAIL, KD980311_NAVER_COM)
				.param(PASSWORD, CORRECT_PASSWORD)
				.param(TELEPHONE, _010_555_1111))
				.andExpect(status().isOk()).andDo(print());
	}

	@Test
	public void create_실패_이메일_잘못된_형식() throws Exception {
		mockMvc.perform(
				post("/users")
				.param(NAME, LEE)
				.param(EMAIL, KD980311)
				.param(PASSWORD, CORRECT_PASSWORD)
				.param(TELEPHONE, EMPTY))
				.andExpect(status().isBadRequest())
				.andExpect(content().contentType(UnitTestUtil.APPLICATION_JSON_UTF8))
				.andDo(print());
	}

	@Test
	public void create_실패_전화번호_빈경우() throws Exception {
		mockMvc.perform(
				post("/users").param(NAME, LEE)
				.param(EMAIL, KD980311_NAVER_COM)
				.param(PASSWORD, CORRECT_PASSWORD)
				.param(TELEPHONE, EMPTY))
				.andExpect(status().isBadRequest())
				.andExpect(content().contentType(UnitTestUtil.APPLICATION_JSON_UTF8))
				.andDo(print());
	}

	@Test
	public void create_실패_패스워드_짧은_길이() throws Exception {
		mockMvc.perform(
				post("/users").param(NAME, LEE)
				.param(EMAIL, KD980311_NAVER_COM)
				.param(PASSWORD, SHORHT_PASSWORD_LENGTH)
				.param(TELEPHONE, _010_555_1111))
				.andExpect(status().isBadRequest())
				.andExpect(content().contentType(UnitTestUtil.APPLICATION_JSON_UTF8))
				.andDo(print());
	}

	@Test
	public void create_실패_패스워드_긴_길이() throws Exception {
		mockMvc.perform(
				post("/users").param(NAME, LEE)
				.param(EMAIL, KD980311_NAVER_COM)
				.param(PASSWORD, LOGN_PASSWORD_LENGTH)
				.param(TELEPHONE, _010_555_1111))
				.andExpect(status().isBadRequest())
				.andExpect(content().contentType(UnitTestUtil.APPLICATION_JSON_UTF8))
				.andDo(print());
	}

	@Test
	public void checkEmail_실패_이메일() throws Exception {
		User userA = UnitTestUtil.userA();
		integrateTestUtil.createUser(UnitTestUtil.createUserDTO(userA));

		mockMvc.perform(
				post("/users/check/email").param("email", userA.getEmail()))
				.andExpect(status().isBadRequest()).andDo(print());
	}

	@Test
	public void checkEmail_실패_이메일_형식_문제() throws Exception {
		CheckEmailDTO checkEmailDTO = new CheckEmailDTO();
		checkEmailDTO.setEmail(KD980311);

		mockMvc.perform(post("/users/check/email").param("email", KD980311))
				.andExpect(status().isBadRequest()).andDo(print());
	}

	@Test
	public void checkEmail_성공() throws Exception {
		mockMvc.perform(
				post("/users/check/email").param("email", KD980311_NATE_COM))
				.andExpect(status().isOk()).andDo(print());
	}

	@Test
	public void checkName_실패_이메일() throws Exception {
		User userA = UnitTestUtil.userA();
		integrateTestUtil.createUser(UnitTestUtil.createUserDTO(userA));

		mockMvc.perform(
				post("/users/check/name").param("name", userA.getName()))
				.andExpect(status().isBadRequest()).andDo(print());
	}

	@Test
	public void checkName_성공() throws Exception {
		mockMvc.perform(post("/users/check/name").param("name", LEE))
				.andExpect(status().isOk()).andDo(print());
	}

	@Test
	public void login_성공() throws Exception {
		User userA = UnitTestUtil.userA();
		integrateTestUtil.createUser(UnitTestUtil.createUserDTO(userA));

		mockMvc.perform(
				post("/users/login").param("email", userA.getEmail()).param("password", userA.getPassword()))
				.andExpect(status().isOk())
				.andDo(print());

	}

	@Test
	public void login_실패_틀린_아이디_패스워드() throws Exception {
		// when
		LoginUserDTO loginUserDTO = new LoginUserDTO();
		loginUserDTO.setEmail(KD980311_NAVER_COM);
		loginUserDTO.setPassword(INCORRECT_PASSWORD);

		// given
		mockMvc.perform(
				post("/users/login").param("email", KD980311_NAVER_COM).param("password", INCORRECT_PASSWORD))
				.andExpect(status().isNotFound()).andDo(print());

	}
}
