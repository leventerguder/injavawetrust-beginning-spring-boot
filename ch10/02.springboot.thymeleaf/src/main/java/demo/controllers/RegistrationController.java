
package demo.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import demo.domain.User;
import demo.repositories.UserRepository;
import demo.validators.UserValidator;

@Controller
public class RegistrationController {
	@Autowired
	private UserValidator userValidator;
	
	@Autowired
	private UserRepository userRepository;

	/*
	 * When you request the "/registration" URL, a GET request is triggered and will
	 * be handled by the RegistrationController.registrationForm() method.
	 */

	@GetMapping(value = "/registration")
	public String registrationForm(Model model) {
		model.addAttribute("user", new User());
		return "registration";
	}

	/*
	 * th:action builds a context-relative URL and th:object specifies the model
	 * attribute name.
	 * 
	 * <form th:action="@{/registration}" th:object="${user}" method="post"> ... ...
	 * </form>
	 * 
	 */

	/*
	 * 
	 * The example uses the syntax th:field="*{propertyName}" for form input fields
	 * so that the field will be backed by the model object property. So when you
	 * use <input type="text" th:field="*{name}"/>, the name input field value will
	 * be bounded to the user.name property.
	 * 
	 */
	@PostMapping(value = "/registration")
	public String handleRegistration(@Valid User user, BindingResult result) {
		System.out.println("Registering User : " + user);
		userValidator.validate(user, result);
		if (result.hasErrors()) {
			return "registration";
		}
		
		userRepository.save(user);
		
		return "redirect:/login";
	}
}
