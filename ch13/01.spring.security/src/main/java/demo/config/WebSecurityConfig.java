package demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
public class WebSecurityConfig {

	/*
	 * The first step is to create our Spring Security Java Configuration. The
	 * configuration creates a Servlet Filter known as the springSecurityFilterChain
	 * which is responsible for all the security (protecting the application URLs,
	 * validating submitted username and passwords, redirecting to the log in form,
	 * etc) within your application.
	 * 
	 */

	@Bean
	public UserDetailsService userDetailsService() {
		

		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(
				User.withDefaultPasswordEncoder().username("user").password("password").roles("USER").build());
		// Using this method is not considered safe for production, but is acceptable
		// for demos and getting started. For production purposes, ensure the password
		// is encoded externally.
		return manager;
	}

	/*
	 * 
	 * There really isn’t much to this configuration, but it does a lot. You can
	 * find a summary of the features below :
	 * 
	 * - Require authentication to every URL in your application.
	 * - Generate a login form for you
	 * - Allow the user with Username user , and the Password password to authenticate with form based authentication
	 * - Allow the user to logout
	 * - CSRF attack prevention
	 * - Session Fixation protection
	 * - Security header integration
	 * - HTTP Strict Transport Security for secure requests
	 * - X-Content-Type-Options integration
	 * - Cache Control
	 * - XSS Protection
	 * - X-Frame-Optionsintegration to help prevent Clickjacking
	 * 
	 *  IntegratewiththefollowingServletAPImethods
	 *  
	 *  • HttpServletRequest#getRemoteUser()
	 *  • HttpServletRequest#getUserPrincipal()
	 *  • HttpServletRequest#isUserInRole(java.lang.String)
	 *  • HttpServletRequest#login(java.lang.String,java.lang.String)
	 *   • HttpServletRequest#logout()

	 */
}
