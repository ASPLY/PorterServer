package org.a_sply.porter.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.a_sply.porter.config.CoreConfig;
import org.a_sply.porter.config.PersistentConfig;
import org.a_sply.porter.controller.UnitTestUtil;
import org.a_sply.porter.dao.impl_jdbc.JdbcMessageDao;
import org.a_sply.porter.dao.impl_jdbc.JdbcMessageListDao;
import org.a_sply.porter.dao.impl_jdbc.JdbcUserDao;
import org.a_sply.porter.domain.Message;
import org.a_sply.porter.domain.MessageList;
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
public class MessageListDaoTest {

	@Autowired
	private JdbcMessageDao messageRepository;

	@Autowired
	private JdbcUserDao userRepository;

	@Autowired
	private JdbcMessageListDao messageListRepository;

	private User userA;
	private User userB;

	@Before
	public void setUp() {
		userRepository.insert(UnitTestUtil.userA());
		userA = userRepository.selectByEmail(UnitTestUtil.userA().getEmail());

		userRepository.insert(UnitTestUtil.userB());
		userB = userRepository.selectByEmail(UnitTestUtil.userB().getEmail());

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
