package org.a_sply.porter.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import org.a_sply.porter.config.CoreConfig;
import org.a_sply.porter.config.PersistentConfig;
import org.a_sply.porter.controller.UnitTestUtil;
import org.a_sply.porter.dao.impl_jdbc.JdbcMessageDao;
import org.a_sply.porter.dao.impl_jdbc.JdbcUserDao;
import org.a_sply.porter.domain.Message;
import org.a_sply.porter.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CoreConfig.class, PersistentConfig.class })
public class MessageDaoTest {

	@Autowired
	private JdbcMessageDao messageRepository;

	@Autowired
	private JdbcUserDao userRepository;

	private User userA;
	private User userB;

	private Message message;

	@Before
	public void setUp() {
		userRepository.insert(UnitTestUtil.userA());
		userA = userRepository.selectByEmail(UnitTestUtil.userA().getEmail());

		userRepository.insert(UnitTestUtil.userB());
		userB = userRepository.selectByEmail(UnitTestUtil.userB().getEmail());

	}

	@Test
	public void save_성공() {
		message = new Message();
		message.setTo(userA);
		message.setFrom(userB);
		message.setContent("안녕하세요. 물건 상태는 어떤가요 ?");

		message = messageRepository.save(message);
		System.out.println(message.getId());
		assertThat(message.getId(), not(0));
	}

	@Test
	public void delete_성공() {
		int count = 2;
		for (int i = 0; i < count; i++) {
			save_성공();
		}

		messageRepository.delete(message.getId());
		assertThat(messageRepository.countAll(), is(count - 1));
	}

}
