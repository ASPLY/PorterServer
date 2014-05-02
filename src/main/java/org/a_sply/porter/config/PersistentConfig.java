package org.a_sply.porter.config;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Petri Kainulainen
 */
@Configuration
@EnableTransactionManagement
@PropertySource("classpath:db/config.properties")
public class PersistentConfig {

	@Inject
	private Environment environment;

	@Value("classpath:db/init_sql/create.sql")
	private Resource schemaScript;

	@Value("classpath:db/init_sql/dummy.sql")
	private Resource dataScript;

	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName(environment
				.getProperty("jdbc.driverClassName"));
		basicDataSource.setUrl(environment.getProperty("jdbc.url"));
		basicDataSource.setUsername(environment.getProperty("jdbc.username"));
		basicDataSource.setPassword(environment.getProperty("jdbc.password"));
		basicDataSource.setMaxActive(Integer.parseInt(environment
				.getProperty("jdbc.maxActive")));
		basicDataSource.setMaxIdle(Integer.parseInt(environment
				.getProperty("jdbc.maxIdle")));
		basicDataSource.setMinIdle(Integer.parseInt(environment
				.getProperty("jdbc.minIdle")));

		return basicDataSource;
	}

	@Bean
	public DataSourceTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}

	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSource(), true);
	}

	@Bean
	public DataSourceInitializer dataSourceInitializer() {
		final DataSourceInitializer initializer = new DataSourceInitializer();
		initializer.setDataSource(dataSource());
		initializer.setDatabasePopulator(databasePopulator());
		return initializer;
	}

	private DatabasePopulator databasePopulator() {
		final ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		System.out.println("init database");
		if (Boolean.valueOf(environment.getProperty("jdbc.init.enabled"))) {
			populator.addScript(schemaScript);
			populator.addScript(dataScript);
		}
		return populator;
	}

	// @Autowired
	// private SessionFactory sessionFactory;
	//
	// @Bean
	// public static LocalSessionFactoryBean sessionFactory() {
	// LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
	// ClassPathXmlApplicationContext ctx = new
	// ClassPathXmlApplicationContext();
	// sessionFactory.setConfigLocation(ctx.getResource("classpath:hibernate.cfg.xml"));
	// sessionFactory.setAnnotatedClasses(new Class<?>[]{ApiKey.class});
	// return sessionFactory;
	// }
	//
	// @Bean
	// public PlatformTransactionManager platformTransactionManager() {
	// return new HibernateTransactionManager(sessionFactory);
	// }
}
