package demo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Email;

@Entity
public class User {

	/*
	 * Validating the user submitted data is crucial in web applications. Spring
	 * supports data validation using Spring’s own validation framework and supports
	 * the Java Bean Validation API. First, you need to specify the user validation
	 * rules using Java Bean validation annotations.
	 * 
	 * 
	 */

	/*
	 * Thymeleaf provides the syntax #fields.hasErrors('fieldName') to check if
	 * there are any errors associated with the fieldName field.
	 * 
	 */

	/*
	 * <p th:if="${#fields.hasErrors('name')}" th:errors="*{name}"
	 * th:class="text-red">Incorrect data</p>
	 * 
	 */

	/*
	 * You can use #fields.hasErrors('global') to check whether there are any global
	 * errors (not related to any specific fields).
	 * 
	 */

	/*
	 * You also need to define the BindingResult parameter immediately next to the
	 * model object. The validation errors will be populated in BindingResult, which
	 * you can inspect later in your method body.
	 * 
	 * 
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotNull
	@Size(min = 3, max = 50)
	private String name;
	@NotNull
	@Email(message = "{invalid.email}")
	private String email;
	@NotNull
	@Size(min = 6, max = 50)
	private String password;

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
