package org.a_sply.porter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;


/**
 * JavaConfig for Scanning Services, repository annotations and Creating Messages beans and Persistent beans
 *
 * @author LCH
 */

@Configuration
@ComponentScan(basePackages = {"org.a_sply.porter.services", "org.a_sply.porter.repository"})
@Import({ MessageConfig.class, PersistentConfig.class })
public class CoreConfig {
	
    /**
     * Create passwordEncoder Bean
     * @return StandardPasswordEncoder bean
     */
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		return new StandardPasswordEncoder();
	}
}
