package org.a_sply.porter.repository;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import org.a_sply.porter.config.CoreConfig;
import org.a_sply.porter.config.PersistentConfig;
import org.a_sply.porter.controller.UnitTestUtil;
import org.a_sply.porter.domain.Message;
import org.a_sply.porter.domain.User;
import org.a_sply.porter.repository.jdbc.JdbcMessageRepository;
import org.a_sply.porter.repository.jdbc.JdbcUserRepository;
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
public class MessageRepositoryTest {

	@Autowired
	private JdbcMessageRepository messageRepository;

	@Autowired
	private JdbcUserRepository userRepository;

	private User userA;
	private User userB;

	private Message message;

	@Before
	public void setUp() {
		userRepository.save(UnitTestUtil.userA());
		userA = userRepository.findByEmail(UnitTestUtil.userA().getEmail());

		userRepository.save(UnitTestUtil.userB());
		userB = userRepository.findByEmail(UnitTestUtil.userB().getEmail());

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
