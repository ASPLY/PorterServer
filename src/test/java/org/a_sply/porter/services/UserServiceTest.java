package org.a_sply.porter.services;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.a_sply.porter.controller.UnitTestUtil;
import org.a_sply.porter.domain.User;
import org.a_sply.porter.dto.email.CheckEmailDTO;
import org.a_sply.porter.dto.user.CheckNameDTO;
import org.a_sply.porter.dto.user.CreateUserDTO;
import org.a_sply.porter.dto.user.LoginUserDTO;
import org.a_sply.porter.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserServiceTest {

	@Mock
	private UserRepository userRepository;
	
	@Mock
	private PasswordEncoder passwordEncoder;

	@InjectMocks
	private UserService userService = new UserServiceImpl();

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void createUser_성공() {
		// when
		CreateUserDTO createUserDTO = UnitTestUtil.createUserADTO();

		// given
		userService.createUser(createUserDTO);

		// then
		verify(userRepository, times(1)).save(User.from(createUserDTO));
	}

	@Test
	public void check_성공_email() {
		// when
		CheckEmailDTO checkEmailDTO = UnitTestUtil.checkEmailDTO();
		when(userRepository.containsEmail(checkEmailDTO.getEmail()))
				.thenReturn(true);

		// given
		boolean isExist = userService.check(checkEmailDTO);

		// then
		assertThat(isExist, is(false));
		verify(userRepository, times(1))
				.containsEmail(checkEmailDTO.getEmail());
	}

	@Test
	public void check_실패_email() {
		// when
		CheckEmailDTO checkEmailDTO = UnitTestUtil.checkEmailDTO();
		when(userRepository.containsEmail(checkEmailDTO.getEmail()))
				.thenReturn(false);

		// given
		boolean isExist = userService.check(checkEmailDTO);

		// then
		assertThat(isExist, is(true));
		verify(userRepository, times(1))
				.containsEmail(checkEmailDTO.getEmail());
	}

	@Test
	public void check_성공_name() {
		// when
		CheckNameDTO checkNameDTO = UnitTestUtil.CheckNameDTO();
		when(userRepository.containsName(checkNameDTO.getName())).thenReturn(
				true);

		// given
		boolean isExist = userService.check(checkNameDTO);

		// then
		assertThat(isExist, is(false));
		verify(userRepository, times(1)).containsName(checkNameDTO.getName());
	}

	@Test
	public void check_실패_name() {
		// when
		CheckNameDTO checkNameDTO = UnitTestUtil.CheckNameDTO();
		when(userRepository.containsEmail(checkNameDTO.getName())).thenReturn(
				false);

		// given
		boolean isExist = userService.check(checkNameDTO);

		// then
		assertThat(isExist, is(true));
		verify(userRepository, times(1)).containsName(checkNameDTO.getName());
	}

	@Test
	public void login_성공() {
		// when
		LoginUserDTO loginUserDTO = UnitTestUtil.loginUserDTO();
		User userA = UnitTestUtil.userA();
		when(userRepository.findByEmail(loginUserDTO.getEmail())).thenReturn(userA);
		when(passwordEncoder.matches(loginUserDTO.getPassword(), userA.getPassword())).thenReturn(true);

		// given
		boolean isExist = userService.login(loginUserDTO);

		// then
		assertThat(isExist, is(true));
		verify(userRepository).findByEmail(loginUserDTO.getEmail());
		verify(passwordEncoder).matches(loginUserDTO.getPassword(), userA.getPassword());
	}

	@Test
	public void login_실패() {
		// when
		LoginUserDTO loginUserDTO = UnitTestUtil.loginUserDTO();
		User userA = UnitTestUtil.userA();
		when(userRepository.findByEmail(loginUserDTO.getEmail())).thenReturn(userA);
		when(passwordEncoder.matches(loginUserDTO.getPassword(), userA.getPassword())).thenReturn(false);


		// given
		boolean isExist = userService.login(loginUserDTO);

		// then
		assertThat(isExist, is(false));
		verify(userRepository).findByEmail(loginUserDTO.getEmail());
		verify(passwordEncoder).matches(loginUserDTO.getPassword(), userA.getPassword());
	}
}