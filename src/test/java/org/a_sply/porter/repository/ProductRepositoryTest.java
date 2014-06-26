package org.a_sply.porter.repository;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.a_sply.porter.config.CoreConfig;
import org.a_sply.porter.config.PersistentConfig;
import org.a_sply.porter.controller.UnitTestUtil;
import org.a_sply.porter.domain.User;
import org.a_sply.porter.domain.product.Product;
import org.a_sply.porter.repository.jdbc.JdbcUserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

//@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CoreConfig.class, PersistentConfig.class})
public class ProductRepositoryTest {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private JdbcUserRepository userRepository;
	
	private Product product;

	@Before
	public void setUp() {
		User userA = UnitTestUtil.userA();
		userRepository.save(userA);

		product = UnitTestUtil.productA(userA);
	}

	@Test
	public void insert_성공() {
		long id = productRepository.insert(product);
		System.out.println(id);
	}

}
