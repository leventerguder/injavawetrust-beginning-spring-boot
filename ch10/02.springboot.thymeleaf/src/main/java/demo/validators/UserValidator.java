package demo.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import demo.domain.User;
import demo.repositories.UserRepository;

@Component
public class UserValidator implements Validator {

	/*
	 * Sometimes you can’t express all the validation rules using annotations only.
	 * For example, you want the user e-mail to be unique. You can’t implement this
	 * without checking against your database.
	 */
	@Autowired
	private UserRepository userRepository;

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;
		String email = user.getEmail();
		User userByEmail = userRepository.findUserByEmail(email);
		if (userByEmail != null) {
			errors.rejectValue("email", "error.exists", new Object[] { email }, "Email " + email + " already in use");
		}
	}
}
