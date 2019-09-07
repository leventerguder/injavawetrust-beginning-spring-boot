package demo.config;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	/*
	 * Suppose your application is running on http://localhost:8080 and you want to
	 * use the HTTPS protocol. If anyone is accessing http://localhost:8080, you’ll
	 * want to redirect the request to https:// localhost:8443.
	 * 
	 * 
	 * First, you generate a self-signed SSL certificate using the following
	 * command: keytool -genkey -alias mydomain -keyalg RSA -keysize 2048 -keystore KeyStore.jks -validity 3650
	 */

	/*
	 * After providing answers to questions that keytool asks, it will generate a
	 * KeyStore.jks file and copy it to the src/main/resources folder. Now configure
	 * the SSL properties in the application.properties file as follows:
	 * server.port=8443 server.ssl.key-store=classpath:KeyStore.jks
	 * server.ssl.key-store-password=mysecret server.ssl.keyStoreType=JKS
	 * server.ssl.keyAlias=mydomain
	 * 
	 */
	@Value("${server.port:8443}")
	private int serverPort;

	@Autowired
	private MessageSource messageSource;

	/*
	 * Spring Boot uses Hibernate Validator as the Bean Validation API
	 * implementation. By default, Hibernate validation looks for the
	 * ValidationMessages.properties file in the root classpath for failure message
	 * keys. If you want to use messages.properties for both i18n and Hibernate
	 * Validation error messages, you can register the Validator bean
	 */
	@Override
	public Validator getValidator() {
		LocalValidatorFactoryBean factory = new LocalValidatorFactoryBean();
		factory.setValidationMessageSource(messageSource);
		return factory;
	}

	@Bean
	public ServletWebServerFactory servletContainer() {
		TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
			@Override
			protected void postProcessContext(Context context) {
				SecurityConstraint securityConstraint = new SecurityConstraint();
				securityConstraint.setUserConstraint("CONFIDENTIAL");
				SecurityCollection collection = new SecurityCollection();
				collection.addPattern("/*");
				securityConstraint.addCollection(collection);
				context.addConstraint(securityConstraint);
			}
		};

		tomcat.addConnectorCustomizers(new TomcatConnectorCustomizer() {

			@Override
			public void customize(Connector connector) {
				((AbstractHttp11Protocol<?>) connector.getProtocolHandler()).setMaxSwallowSize(-1);
			}
		});

		tomcat.addAdditionalTomcatConnectors(initiateHttpConnector());
		return tomcat;
	}

	private Connector initiateHttpConnector() {
		Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
		connector.setScheme("http");
		connector.setPort(8080);
		connector.setSecure(false);
		connector.setRedirectPort(serverPort);

		// Tomcat maxSwallowSize sets to 2MB by default.
		// To set the maxSwallowSize property of Tomcat
		// https://tomcat.apache.org/tomcat-8.0-doc/config/http.html
		// http://stackoverflow.com/questions/35748022/multipart-file-maximum-size-exception-spring-boot-embbeded-tomcat

		((AbstractHttp11Protocol<?>) connector.getProtocolHandler()).setMaxSwallowSize(11534336);
		return connector;
	}

}
