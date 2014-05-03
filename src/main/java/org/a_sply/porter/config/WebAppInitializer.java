package org.a_sply.porter.config;

import java.util.Set;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.multipart.support.MultipartFilter;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * @author Petri Kainulainen
 */
public class WebAppInitializer implements WebApplicationInitializer {

	private static final String SPRING_SECURITY_FILTER_CHAIN_NAME = "springSecurityFilterChain";
	private static Logger LOG = LoggerFactory.getLogger(WebAppInitializer.class);
	private static final String CHARACTER_ENCODING_FILTER_NAME = "CharacterEncodingFilter";
	private static final String DISPATCHER_SERVLET_NAME = "dispatcher";
	private static final String DISPATCHER_SERVLET_MAPPING = "/";

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		WebApplicationContext rootContext = createRootContext(servletContext);
		configureSpringMvc(servletContext, rootContext);
		configureSpringSecurity(servletContext, rootContext);
	}


	private WebApplicationContext createRootContext(ServletContext servletContext) {
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(CoreConfig.class, SecurityConfig.class);
		rootContext.refresh();

		servletContext.addListener(new ContextLoaderListener(rootContext));
		servletContext.setInitParameter("defaultHtmlEscape", "true");

		return rootContext;
	}

	// {!end addToRootContext}

	private void configureSpringMvc(ServletContext servletContext,
			WebApplicationContext rootContext) {
		 AnnotationConfigWebApplicationContext mvcContext = new AnnotationConfigWebApplicationContext();
		 mvcContext.register(MVCConfig.class);
		
		 mvcContext.setParent(rootContext);

		ServletRegistration.Dynamic appServlet = servletContext.addServlet(DISPATCHER_SERVLET_NAME, new DispatcherServlet(mvcContext));
		appServlet.setLoadOnStartup(1);
		Set<String> mappingConflicts = appServlet.addMapping(DISPATCHER_SERVLET_MAPPING);

		if (!mappingConflicts.isEmpty()) {
			for (String s : mappingConflicts) {
				LOG.error("Mapping conflict: " + s);
			}
			throw new IllegalStateException(
					"'webservice' cannot be mapped to '/'");
		}

		setUTF8Encoding(servletContext);
	}

	private void setUTF8Encoding(ServletContext servletContext) {
		FilterRegistration.Dynamic filter = servletContext.addFilter(CHARACTER_ENCODING_FILTER_NAME, org.springframework.web.filter.CharacterEncodingFilter.class);
		filter.setInitParameter("encoding", "utf-8");
		filter.setInitParameter("forceEncoding", "true");
		filter.addMappingForUrlPatterns(null, true, "/*");
	}

	private void configureSpringSecurity(ServletContext servletContext, WebApplicationContext rootContext) {
		FilterRegistration.Dynamic springSecurity = servletContext.addFilter(SPRING_SECURITY_FILTER_CHAIN_NAME, new DelegatingFilterProxy(SPRING_SECURITY_FILTER_CHAIN_NAME, rootContext));
		springSecurity.addMappingForUrlPatterns(null, true, "/*");
	}
}
