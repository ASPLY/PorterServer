package org.a_sply.porter.repository;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.a_sply.porter.config.CoreConfig;
import org.a_sply.porter.config.PersistentConfig;
import org.a_sply.porter.controller.UnitTestUtil;
import org.a_sply.porter.domain.Message;
import org.a_sply.porter.domain.MessageList;
import org.a_sply.porter.domain.User;
import org.a_sply.porter.repository.jdbc.JdbcMessageListRepository;
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
public class MessageListRepositoryTest {

	@Autowired
	private JdbcMessageRepository messageRepository;

	@Autowired
	private JdbcUserRepository userRepository;

	@Autowired
	private JdbcMessageListRepository messageListRepository;

	private User userA;
	private User userB;

	@Before
	public void setUp() {
		userRepository.save(UnitTestUtil.userA());
		userA = userRepository.findByEmail(UnitTestUtil.userA().getEmail());

		userRepository.save(UnitTestUtil.userB());
		userB = userRepository.findByEmail(UnitTestUtil.userB().getEmail());

		Message message = new Message();
		message.setTo(userA);
		message.setFrom(userB);
		message.setContent("안녕하세요. 물건 상태는 어떤가요 ?");

		messageRepository.save(message);
		messageRepository.save(message);
		messageRepository.save(message);
	}

	@Test
	public void findByUserId_성공() {
		List<MessageList> messages = messageListRepository.findByUserId(userB.getId());
		assertThat(messages.size(), is(3));
		for (MessageList messageList : messages) {
			assertThat(messageList.getFrom(), is(userB.getName()));
		}
	}

}
