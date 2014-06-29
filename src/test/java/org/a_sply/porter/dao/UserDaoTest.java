package org.a_sply.porter.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.a_sply.porter.config.CoreConfig;
import org.a_sply.porter.config.PersistentConfig;
import org.a_sply.porter.controller.UnitTestUtil;
import org.a_sply.porter.dao.impl_jdbc.JdbcUserDao;
import org.a_sply.porter.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CoreConfig.class, PersistentConfig.class })
@Transactional
public class UserDaoTest {

	private static final User USER_D = UnitTestUtil.userD();
	private static final User USER_E = UnitTestUtil.userE();
	private static final User USER_F = UnitTestUtil.userF();

	@Autowired
	private JdbcUserDao jdbcUserRepository;

	@Before
	public void setUp() {
		jdbcUserRepository.insert(USER_D);
		jdbcUserRepository.insert(USER_E);
	}

	@Test
	public void save_성공() {
	}

	@Test
	public void containsEmail_실패() {
		assertThat(jdbcUserRepository.containsEmail(USER_F.getEmail()),
				is(false));
	}

	@Test
	public void containsEmail_성공() {
		assertThat(jdbcUserRepository.containsEmail(USER_D.getEmail()),
				is(true));
	}

	@Test
	public void containsName_실패() {
		assertThat(jdbcUserRepository.containsName(USER_F.getName()), is(false));
	}

	@Test
	public void containsName_성공() {
		assertThat(jdbcUserRepository.containsName(USER_D.getName()), is(true));
	}

	@Test
	public void login_성공() {
		assertThat(jdbcUserRepository.contains(USER_D), is(true));
	}

	@Test
	public void login_실패() {
		assertThat(jdbcUserRepository.contains(USER_F), is(false));
	}

	@Test
	public void findByEmail_성공() {
		User userD = jdbcUserRepository.selectByEmail(USER_D.getEmail());
		System.out.println(userD.getEmail());
		assertThat(userD, is(USER_D));
	}
}
