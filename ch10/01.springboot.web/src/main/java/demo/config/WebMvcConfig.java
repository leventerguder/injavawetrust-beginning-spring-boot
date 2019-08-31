/**
 * 
 */
package demo.config;

import javax.servlet.DispatcherType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import demo.web.MyFilter;
import demo.web.MyServlet;
import net.bull.javamelody.MonitoringFilter;
import net.bull.javamelody.SessionListener;

@Configuration
/*
 * 
 * SpringMVC provides WebMvcConfigurerAdapter, which is an implementation of the
 * WebMvcConfigurer interface. But WebMvcConfigurerAdapter is deprecated as of
 * Spring 5.0, because WebMvcConfigurer has default method implementations and
 * uses Java 8 default method support.
 */
public class WebMvcConfig implements WebMvcConfigurer {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private MyServlet myServlet;

	@Override
	public Validator getValidator() {
		LocalValidatorFactoryBean factory = new LocalValidatorFactoryBean();
		factory.setValidationMessageSource(messageSource);
		return factory;
	}

	/*
	 * 
	 * 
	 * You can register servlets, filters, listeners by using the
	 * ServletRegistrationBean, FilterRegistrationBean, and
	 * ServletListenerRegistrationBean bean definitions.
	 * 
	 */
	@Bean
	public ServletRegistrationBean<MyServlet> myServletBean() {

		ServletRegistrationBean<MyServlet> servlet = new ServletRegistrationBean<>();
		servlet.setServlet(myServlet);
		servlet.addUrlMappings("/myServlet");
		return servlet;
	}

	@Bean
	public FilterRegistrationBean<MyFilter> myFilterBean() {

		FilterRegistrationBean<MyFilter> filter = new FilterRegistrationBean<>();
		filter.setFilter(new MyFilter());
		filter.addUrlPatterns("/*");
		return filter;
	}

	/*
	 * JavaMelody (https://github.com/javamelody/javamelody/wiki) is a library that
	 * can be used to monitor Java-based web applications. JavaMelody can provide
	 * various metrics about the running application, including:
	 * 
	 * - A summary indicating the overall number of executions, the average
	 * execution time, the CPU time, and the percentage of errors.
	 * 
	 * - The percentage of time spent on the requests when the average time exceeds
	 * a configurable threshold.
	 * 
	 * - The complete list of requests, aggregated without dynamic parameters with
	 * the number of executions, the mean execution time, the mean CPU time, the
	 * percentage of errors, and an evolution chart of execution time over time.
	 * 
	 * - Each HTTP request indicates the size of the flow response, the mean number
	 * of SQL executions, and the mean SQL time.
	 * 
	 * 
	 * - Integrating JavaMelody into a Java web application is very simple. You need
	 * to register net.bull. javamelody.MonitoringFilter, which is a filter, and
	 * net.bull.javamelody.SessionListener, which is a HttpSessionListener.
	 * 
	 * 
	 * 
	 */

	@Bean(name = "javamelodyFilter")
	public FilterRegistrationBean<MonitoringFilter> javamelodyFilterBean() {

		FilterRegistrationBean<MonitoringFilter> registration = new FilterRegistrationBean<>();
		registration.setFilter(new MonitoringFilter());
		registration.addUrlPatterns("/*");
		registration.setName("javamelodyFilter");
		registration.setAsyncSupported(true);
		registration.setDispatcherTypes(DispatcherType.REQUEST, DispatcherType.ASYNC);
		return registration;
	}

	/*
	 * you can start the application and see JavaMelody’s monitoring dashboard at
	 * http://localhost:8080/monitoring
	 */

	@Bean(name = "javamelodySessionListener")
	public ServletListenerRegistrationBean<SessionListener> sessionListener() {
		return new ServletListenerRegistrationBean<>(new SessionListener());
	}

}
