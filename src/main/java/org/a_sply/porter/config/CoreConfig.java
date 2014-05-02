package org.a_sply.porter.config;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;

import org.a_sply.porter.model.ApiKeyManager;
import org.a_sply.porter.model.ApiKeyManagerImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.type.StandardAnnotationMetadata;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.support.MultipartFilter;

@Configuration
@ComponentScan(basePackages = {"org.a_sply.porter.services", "org.a_sply.porter.repository"})
@Import({ MessageConfig.class, PersistentConfig.class })
public class CoreConfig {
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		return new StandardPasswordEncoder();
	}
}
