package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;
import reactor.core.publisher.Flux;

import java.util.List;

@Controller
public class UserListController {

	@Autowired
	private UserReactiveRepository userReactiveRepository;

	@GetMapping("/")
	public String index() {
		return "index";
	}

	/*
	 * In the listUsers() method, you are getting data from MongoDB as a reactive
	 * stream Flux<User> and fully resolving the data and then rendering the view
	 * with a complete set of data available in memory.
	 */

	@GetMapping("/list-users")
	public String listUsers(Model model) {

		Flux<User> userFlux = this.userReactiveRepository.findAll().repeat(30000);
		List<User> userList = userFlux.collectList().block();
		model.addAttribute("users", userList);
		return "users";
	}

	/*
	 * In the listUsersChunked() method, you are adding Flux<User> directly to the
	 * model, but you haven’t specified responseMaxChunkSizeBytes, so it will be
	 * processed like FULL mode. If you configure the spring.
	 * thymeleaf.reactive.max-chunk-size=size_in_bytes property, then it will be
	 * processed in CHUNKED mode.
	 */
	@GetMapping("/list-users-chunked")
	public String listUsersChunked(Model model) {

		Flux<User> userFlux = this.userReactiveRepository.findAll().repeat(30000);
		model.addAttribute("users", userFlux);
		return "users";
	}

	/*
	 * In the listUsersReactive() method, you are getting data from MongoDB as a
	 * reactive stream Flux<User> and wrapping it in
	 * ReactiveDataDriverContextVariable with a buffer size so that Thymeleaf will
	 * use the data-driven mode to the render view.
	 * 
	 */

	@GetMapping("/list-users-reactive")
	public String listUsersReactive(Model model) {

		Flux<User> userFlux = this.userReactiveRepository.findAll().repeat(30000);
		model.addAttribute("users", new ReactiveDataDriverContextVariable(userFlux, 1000));
		return "users";
	}
}
