package org.a_sply.porter.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.a_sply.porter.config.CoreConfig;
import org.a_sply.porter.config.PersistentConfig;
import org.a_sply.porter.controller.UnitTestUtil;
import org.a_sply.porter.dao.impl_jdbc.JdbcArticleDao;
import org.a_sply.porter.dao.impl_jdbc.JdbcUserDao;
import org.a_sply.porter.domain.Article;
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
public class ArticleDaoTest {

	@Autowired
	private JdbcArticleDao articleRepository;

	@Autowired
	private JdbcUserDao userRepository;

	private Article articleA;

	@Before
	public void setUp() {
		User userA = UnitTestUtil.userA();
		userRepository.insert(userA);

		articleA = UnitTestUtil.article(userA, UnitTestUtil.partA());
		articleA = articleRepository.save(articleA);
		Article articleB = UnitTestUtil.article(userA, UnitTestUtil.partB());
		articleB = articleRepository.save(articleB);
	}

	@Test
	public void save_성공() {
	}

	@Test
	public void get_성공() {
		Article same = articleRepository.get(articleA.getId());
		assertThat(articleA, is(same));
	}

}
