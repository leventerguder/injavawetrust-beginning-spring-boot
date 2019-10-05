package demo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, proxyTargetClass = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	/*
	 * Spring Boot implemented the default Spring Security autoconfiguration in
	 * SecurityAutoConfiguration. To switch the default web application security
	 * configuration and provide your own customized security configuration, you can
	 * create a configuration class that extends WebSecurityConfigurerAdapter and is
	 * annotated with @EnableWebSecurity.
	 */
	
	@Autowired
	private UserDetailsService customUserDetailsService;
	
	@Autowired
	private DataSource dataSource;
	
	
	/*
	 * BCryptPasswordEncoder to be used by AuthenticationManager instead of the
	 * default in-memory database with a single-user with a plaintext password.
	 * 
	 */
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
        	.userDetailsService(customUserDetailsService)
        	.passwordEncoder(passwordEncoder());
    }
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http        	
        	.headers()
        		.frameOptions().sameOrigin()
        		.and()
            .authorizeRequests()
            	.antMatchers("/resources/**", "/webjars/**","/assets/**").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/home")
                .failureUrl("/login?error")
                .permitAll()
                .and()
            .logout()
            	.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            	.logoutSuccessUrl("/login?logout")
            	.deleteCookies("my-remember-me-cookie")
                .permitAll()
                .and()
             .rememberMe()
             	//.key("my-secure-key")
             	.rememberMeCookieName("my-remember-me-cookie")
             	.tokenRepository(persistentTokenRepository())
             	.tokenValiditySeconds(24 * 60 * 60)
             	.and()
            	/*
            	 * Now when you successfully log in with the Remember-Me checkbox selected, the
            	 * generated token will be stored in the persistent_logins table.
            	 */
            .exceptionHandling()
            	//.accessDeniedPage("/403")
             	;
    }
    
	/*
	 * The configure(HttpSecurity http) method is configured to: 
	 * • Ignore the static resource paths "/resources/**", "/webjars/**", and "/assets/**" 
	 * • Allow everyone to have access to the root URL "/" 
	 * • Restrict access to URLs that start with /admin/ to only users with the ADMIN role 
	 * • All other URLs should be accessible to authenticated users only
	 */
	
	/*
	 * You are also configuring custom form-based login parameters and making them
	 * accessible to everyone. By default, the logout request works only with the
	 * HTTP POST method, so this example configures it to be used with any HTTP
	 * method, which makes it accessible to everyone.
	 * 
	 */
	
	/*
	 * You are going to use Thymeleaf view templates for rendering views. The
	 * thymeleaf-extras- springsecurity5 module provides Thymeleaf Spring Security
	 * dialect attributes (sec:authentication, sec:authorize, etc.) to conditionally
	 * render parts of the view based on authentication status, logged-in user
	 * roles, etc.
	 * 
	 */
    
	/*
	 * Spring Security provides the Remember-Me feature so that applications can
	 * remember the identity of a user between sessions. To use the Remember-Me
	 * functionality, you just need to send the HTTP parameter remember-me.
	 * 
	 */
    
    /*
     * <form th:action="@{/login}" method="post">
			<input type="email" name="username"/>
			<input type="password" name="password" />
			<input type="checkbox" name="remember-me"> Remember Me <button type="submit">LogIn</button>
		</form>
     * 
     */
    

	/*
	 * 
	 * Spring Security provides the following two implementations of the Remember-Me
	 * feature out-of-the-box: 
	 * • Simple hash-based token as a cookie—This approach creates a token by hashing 
	 * the user identity information and setting it as a cookie on the client browser. 
	 * • Persistent token—This approach uses a persistent store like a relational database 
	 * to store the tokens.
	 */
    


	/*
	 * Simple Hash-Based Token as Cookie You can enable the Remember-Me feature in
	 * the Security configuration which by default use hash-based token approach,
	 * 
	 * ...
	 * deleteCookies("remember-me") 
	 * .permitAll() 
	 * .and() 
	 * .rememberMe() 
	 * .and()
	 * .exceptionHandling()
	 */
    
    
	/*
	 * This example configures view controllers to specify which view to render for
	 * which URL. Also, it registers SpringSecurityDialect to enable using the
	 * Thymeleaf Spring Security dialect. Now that you have all the configuration
	 * ready, it’s time to create views using Thymeleaf.
	 * 
	 */

    

	/*
	 * Spring Security provides another implementation of the Remember-Me feature,
	 * which can be used to store the generated tokens in a persistent storage such
	 * as a database. The persistent tokens approach is implemented using
	 * org.springframework.security.web.authentication.rememberme.
	 * PersistentTokenBasedRememberMeServices, which internally uses the
	 * PersistentTokenRepository interface to store the tokens.
	 */

	// InMemoryTokenRepositoryImpl can be used to store tokens in-memory
	// (notrecommended for production use).

	// JdbcTokenRepositoryImpl can be used to store tokens in a database.

	/*
	 * The JdbcTokenRepositoryImpl stores the tokens in the persistent_logins table.
	 * 
	 */
    
	/*
	 * Cross-Site Request Forgery (CSRF) is an attack that lets users execute
	 * unwanted actions on an authenticated web application. Suppose you go to the
	 * genuinesite.com web site and authenticate yourself. This may set cookies on
	 * your browser, including an authentication token. Now, if you open the
	 * malicioussite. com web site on the same browser but in a different tab, you
	 * can send a request from malicioussite.com to genuinesite.com with unwanted
	 * data. This data will send the request along with the cookies set by
	 * genuinesite.com.
	 * 
	 */

	/*
	 * Spring Security provides CSRF protection and is enabled by default. Spring
	 * Security provides CSRF protection by using
	 * org.springframework.security.web.csrf.CsrfFilter. The CsrfFilter will
	 * intercept all the requests, ignore the GET, HEAD, TRACE, and OPTIONS
	 * requests, and check whether a valid CSRF token is present for all the other
	 * requests (such as POST, PUT, DELETE, etc.). If the CSRF token is missing or
	 * contains an invalid token, then it will throw AccessDeniedException.
	 * 
	 */
	
	/*
	 * You can manually insert the token as follows:
		<form method="post" action="/users">
		    ...
		... <input type="hidden"
		      th:name="${_csrf.parameterName}"
		      th:value="${_csrf.token}" />
		</form>
	 * 
	 */
    
	/*
	 * If you are using Spring Security and Thymeleaf, the CSRF token will be
	 * automatically included if the <form> has the th:action attribute and method
	 * is anything other than GET, HEAD, TRACE, or OPTIONS.
	 */
	
	/*
	 * 
	 * 
	<form th:action="@{/messages}" method="post">
    	<textarea name="content" cols="50" rows="5"></textarea>
    	<input type="submit" value="Submit"/>
	</form>
	 */
	
	/*
	 * 
	 <form action="/messages" method="post">
    	<input type="hidden" name="_csrf" value="57f12f98-d62c-4d01-88c0-a11cf9ed980a"/>
    	<textarea name="content" cols="50" rows="5"></textarea>
    	<input type="submit" value="Submit"/>
	</form>
	 * 
	 */
    
	/*
	 * if you use the action attribute instead of setting the th:action or method
	 * value to any of GET, HEAD, TRACE, or OPTIONS, the CSrF token won’t be
	 * inserted automatically.
	 */

	/*
	 * If you submit the form without a CSRF token or with an invalid CSRF token,
	 * then AccessDeniedException will be thrown with the 403 HTTP status code.
	 */
    
    
    PersistentTokenRepository persistentTokenRepository(){
    	JdbcTokenRepositoryImpl tokenRepositoryImpl = new JdbcTokenRepositoryImpl();
    	tokenRepositoryImpl.setDataSource(dataSource);
    	return tokenRepositoryImpl;
    }
}
