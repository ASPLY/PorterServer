package org.a_sply.porter.repository;

import static org.a_sply.porter.controller.UnitTestUtil.productA;
import static org.a_sply.porter.controller.UnitTestUtil.userA;

import java.util.List;

import org.a_sply.porter.config.CoreConfig;
import org.a_sply.porter.config.PersistentConfig;
import org.a_sply.porter.domain.User;
import org.a_sply.porter.domain.product.CarInfo;
import org.a_sply.porter.domain.product.PartType;
import org.a_sply.porter.domain.product.Product;
import org.a_sply.porter.domain.product.ProductCondition;
import org.a_sply.porter.repository.jdbc.JdbcUserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
		User userA = userA();
		userRepository.insert(userA);

		product = productA(userA);
	}

	@Test
	public void insert_성공() {
		long id = productRepository.insert(product);
		System.out.println(id);
	}
	
	@Test
	public void selectByCondtion_성공() {
		ProductCondition productCondition = new ProductCondition();
		productCondition.setCarInfo(new CarInfo(1000, 2000, 3000, 0));
		productCondition.setPartType(new PartType());
		List<Product> products = productRepository.selectByCondition(productCondition);
		System.out.println(products.size());
	}

}
