package demo.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

	/*
	 * You can handle exceptions globally by creating an exception handler class
	 * annotated with @ControllerAdvice. The @ExceptionHandler methods in
	 * the @ControllerAdvice class handle errors that occur in any controller
	 * request handling method.
	 */

	@ExceptionHandler({ MultipartException.class, FileSizeLimitExceededException.class,
			MaxUploadSizeExceededException.class })

	public ModelAndView handleMaxUploadException(Exception e, HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> model = new HashMap<>();
		model.put("msg", e.getMessage());
		return new ModelAndView("fileUpload", model);
	}

	/*
	 * Spring Boot registers a global error handler and maps /error by default,
	 * which renders an HTML response for browser clients and a JSON response for
	 * REST clients. You can provide the custom error page by implementing
	 * ErrorController.
	 */

}
