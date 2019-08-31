package demo.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import demo.services.UserService;

@Component
public class MyServlet extends HttpServlet {
	/*
	 * 
	 * You can register servlets, filters, listeners by using the
	 * ServletRegistrationBean, FilterRegistrationBean, and
	 * ServletListenerRegistrationBean bean definitions.
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private UserService userService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.getWriter().write(userService.getMessage());
	}
}
