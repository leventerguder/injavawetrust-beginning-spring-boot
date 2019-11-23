/**
 * 
 */
package demo.controller;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.hamcrest.Matchers.*;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import demo.entity.Todo;
import demo.repository.TodoRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = TodoController.class, secure = false)
public class TodoControllerTests {

	/*
	 * 
	 * Spring Boot provides the @WebMvcTest annotation , which will autoconfigure
	 * SpringMVC infrastructure components and load only @Controller
	 * , @ControllerAdvice , @JsonComponent , FIlter , WebMvcConfigurer ,
	 * HandlerMethodArgumentResolver components. Other Spring beans (annotated
	 * with @Component , @Service , @Repository ) will not be scanned when using
	 * this annotation.
	 * 
	 */

	/*
	 * You have annotated the test with @WebMvcTest(controllers =
	 * TodoController.class) by explicitly specifying which controller you are
	 * testing. As @WebMvcTest doesn’t load other regular Spring beans and
	 * TodoController depends on TodoRepository, you provided a mock bean using
	 * the @MockBean annotation. The @WebMvcTest autoconfigures MockMvc, which can
	 * be used to test controllers without starting an actual servlet container.
	 * 
	 */
	@Autowired
	private MockMvc mvc;

	@MockBean
	private TodoRepository todoRepository;

	@Test
	public void testFindTodoById() throws Exception {
		Todo todo1 = new Todo(1, "Todo1", false);
		Todo todo2 = new Todo(2, "Todo2", true);

		given(this.todoRepository.findAll()).willReturn(Arrays.asList(todo1, todo2));

		this.mvc.perform(get("/todolist")
				.accept(MediaType.TEXT_HTML))
				.andExpect(status().isOk())
				.andExpect(view().name("todos"))
				.andExpect(model().attribute("todos", hasSize(2)));

		verify(todoRepository, times(1)).findAll();
	}

}
