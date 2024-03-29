package org.a_sply.porter.config;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * JavaConfig for creating db beans
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

	/**
     * Create DataSource bean 
     * @return DataSource bean
     */
	
	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName(environment.getProperty("jdbc.driverClassName"));
		basicDataSource.setUrl(environment.getProperty("jdbc.url"));
		String username = environment.getProperty("jdbc.username");
		System.out.println("username : "+username);
		basicDataSource.setUsername(username);
		String password = environment.getProperty("jdbc.password");
		System.out.println("password : "+password);
		basicDataSource.setPassword(password);
		basicDataSource.setMaxActive(Integer.parseInt(environment.getProperty("jdbc.maxActive")));
		basicDataSource.setMaxIdle(Integer.parseInt(environment.getProperty("jdbc.maxIdle")));
		basicDataSource.setMinIdle(Integer.parseInt(environment.getProperty("jdbc.minIdle")));

		return basicDataSource;
	}
	
	/**
     * Create DataSourceTransactionManager bean 
     * @return DataSourceTransactionManager bean
     */

	@Bean
	public DataSourceTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}
	
	/**
     * Create JdbcTemplate bean 
     * @return JdbcTemplate bean
     */

	@Bean
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
		return new NamedParameterJdbcTemplate(dataSource());
	}
	
	@Bean
	public JdbcTemplate JdbcTemplate() {
		return new JdbcTemplate(dataSource(), true);
	}
	
	@Bean
	public KeyHolder KeyHolder(){
		return new GeneratedKeyHolder();
	}

	/**
     * Create DataSourceInitializer bean 
     * @return DataSourceInitializer bean
     */
	
	@Bean
	public DataSourceInitializer dataSourceInitializer() {
		final DataSourceInitializer initializer = new DataSourceInitializer();
		initializer.setDataSource(dataSource());
		initializer.setDatabasePopulator(databasePopulator());
		return initializer;
	}
	
	private DatabasePopulator databasePopulator() {
		final ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		String isInit = environment.getProperty("jdbc.init.enabled");
		System.out.println("init database : " + isInit);
		if (Boolean.valueOf(isInit)) {
			populator.addScript(schemaScript);
			populator.addScript(dataScript);
		}
		return populator;
	}
}
