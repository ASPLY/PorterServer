package org.a_sply.porter.repository;

import javax.transaction.Transactional;

import org.a_sply.porter.config.CoreConfig;
import org.a_sply.porter.config.PersistentConfig;
import org.a_sply.porter.controller.UnitTestUtil;
import org.a_sply.porter.domain.Article;
import org.a_sply.porter.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CoreConfig.class, PersistentConfig.class})
public class BasketRepositoryTest {

	@Autowired
	private BasketRepository basketRepository;
	
	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private UserRepository userRepository;

	private User userA;
	private Article articleA;
	private Article articleB;

	@Before
	public void setUp() {
		userA = UnitTestUtil.userA();
		userA = userRepository.save(userA);
		
		articleA = UnitTestUtil.article(userA, UnitTestUtil.partA());
		articleA = articleRepository.save(articleA);
		articleB = UnitTestUtil.article(userA, UnitTestUtil.partB());
		articleB = articleRepository.save(articleB);
		
		basketRepository.save(userA.getId(), articleA.getId());
		basketRepository.save(userA.getId(), articleB.getId());
	}
	
	@Test
	public void save_성공(){
		basketRepository.save(userA.getId(), articleA.getId());
		basketRepository.save(userA.getId(), articleB.getId());
	}
	
	@Test
	public void findByUserId_성공(){
	
	}
}
