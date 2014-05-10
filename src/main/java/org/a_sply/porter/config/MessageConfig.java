package org.a_sply.porter.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * JavaConfig for Creating Messages beans
 *
 * @author LCH
 */

@Configuration
@Profile("webapp")
public class MessageConfig {

	private static final String MESSAGE_SOURCE_BASE_NAME = "i18n/messages";

    /**
     * Create messageSource Bean
     * @return ResourceBundleMessageSource bean with MessageConfig's member variable, MESSAGE_SOURCE_BASE_NAME
     */
	
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();

		messageSource.setBasename(MESSAGE_SOURCE_BASE_NAME);
		messageSource.setUseCodeAsDefaultMessage(true);

		return messageSource;
	}
}
