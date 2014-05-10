package org.a_sply.porter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * JavaConfig for scanning controller and configuring WebMVC 
 *
 * @author LCH
 */

@Configuration
@ComponentScan(basePackages = { "org.a_sply.porter.controller"})
@EnableWebMvc
public class MVCConfig extends WebMvcConfigurerAdapter {

    /**
     * Configure to use WebMvc
     */
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	/**
     * Create MultipartResolver bean for implementing file upload 
     * @return MultipartResolver bean
     */

	@Bean
	public MultipartResolver multipartResolver() {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setMaxUploadSize(1000000);
		resolver.setDefaultEncoding("UTF-8");
		//resolver.setResolveLazily(true);
		return resolver;
	}
}
