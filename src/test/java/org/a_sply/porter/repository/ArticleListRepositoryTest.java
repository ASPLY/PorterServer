package org.a_sply.porter.repository;

import java.util.List;

import org.a_sply.porter.config.CoreConfig;
import org.a_sply.porter.config.PersistentConfig;
import org.a_sply.porter.controller.UnitTestUtil;
import org.a_sply.porter.domain.Article;
import org.a_sply.porter.domain.ArticleList;
import org.a_sply.porter.domain.RequestArticleLists;
import org.a_sply.porter.domain.Search;
import org.a_sply.porter.domain.User;
import org.a_sply.porter.repository.jdbc.JdbcArticleListRepository;
import org.a_sply.porter.repository.jdbc.JdbcArticleRepository;
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
public class ArticleListRepositoryTest {

	@Autowired
	private JdbcArticleListRepository articleListRepository;

	@Autowired
	private JdbcArticleRepository jdbcArticleRepository;

	@Autowired
	private JdbcUserRepository userRepository;

	private Article articleA;

	@Before
	public void setUp() {
		User userA = UnitTestUtil.userA();
		userRepository.insert(userA);

		articleA = UnitTestUtil.article(userA, UnitTestUtil.partA());
		articleA = jdbcArticleRepository.save(articleA);
		Article articleB = UnitTestUtil.article(userA, UnitTestUtil.partB());
		articleB = jdbcArticleRepository.save(articleB);
	}

	@Test
	public void search_성공() {
		Search search = new Search();
		search.setKeyword(articleA.getPart().getName());
		search.setCount(2);
		List<ArticleList> articleLists = articleListRepository.search(search);
		for (ArticleList articleList : articleLists) {
			System.out.println(articleList.getName()
					+ articleList.getArticleId() + articleList.getIsSold());
		}
	}

	@Test
	public void searchByEmail_성공() {
		List<ArticleList> articleLists = articleListRepository
				.searchByEmail("kd980311@naver.com");
		System.out.println(articleLists.get(0));
	}

	@Test
	// category 별 목록가져오기, testdb로 테스트
	public void get_성공() {
		RequestArticleLists getArticleLists = new RequestArticleLists();
		getArticleLists.setCount(100);
		getArticleLists.setLargeCategory(1000);
		getArticleLists.setMiddleCategory(1001);
		getArticleLists.setOffset(2);

		List<ArticleList> articleLists = articleListRepository
				.get(getArticleLists);
		for (ArticleList articleList : articleLists) {
			System.out.println(articleList.getName()
					+ articleList.getArticleId());
		}
	}

}
