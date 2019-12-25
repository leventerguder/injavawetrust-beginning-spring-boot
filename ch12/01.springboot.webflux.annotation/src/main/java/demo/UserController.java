package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
	private UserReactiveRepository userReactiveRepository;

	@GetMapping
	public Flux<User> allUsers() {
		return userReactiveRepository.findAll();
	}

	@GetMapping("/{id}")
	public Mono<User> getUser(@PathVariable String id) {
		return userReactiveRepository.findById(id);
	}

	/*
	 * There is nothing new in the UserController implementation except that you are
	 * using Reactor types such as Mono<User> to represent a stream of a single user
	 * object and using Flux<User> to represent a stream of one or more User
	 * objects.
	 * 
	 */

	/*
	 * One key point to remember is that, unless someone subscribes to the reactive
	 * stream pipeline, nothing happens. For example, the following method doesn’t
	 * insert the user document in MongoDB.
	 * 
	 */

	// @PostMapping
	// public Mono<User> saveUser(@RequestBody Mono<User> userMono) {
	// userMono.flatMap(user -> userReactiveRepository.save(user));
	// return Mono.empty();
	// }

	@PostMapping
	public Mono<User> saveUser(@RequestBody Mono<User> userMono) {
		// the following does NOT work
		// userMono.flatMap(user -> userReactiveRepository.save(user));

		/*
		 * This just defined the stream pipeline, but nobody subscribed to it, so
		 * nothing happens. The following code will insert the user document because it
		 * is explicitly calling subscribe() on the pipeline.
		 */

		// the following works
		
		userMono.flatMap(user -> userReactiveRepository.save(user)).subscribe();

		return Mono.empty();
	}

	@DeleteMapping("/{id}")
	public Mono<Void> deleteUser(@PathVariable String id) {
		return userReactiveRepository.deleteById(id);
	}
}
