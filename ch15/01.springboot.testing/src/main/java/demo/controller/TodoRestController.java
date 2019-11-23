package demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import demo.entity.Todo;
import demo.repository.TodoRepository;

@RestController
public class TodoRestController {

	@Autowired
	private TodoRepository todoRepository;

	@GetMapping("/api/todos/list")
	public List<Todo> findAll() {
		return todoRepository.findAll();
	}

	@GetMapping("/api/todos/{id}")
	public Optional<Todo> findById(@PathVariable Integer id) {
		return todoRepository.findById(id);
	}

	@PostMapping("/api/todos")
	public Todo createTodo(@RequestBody Todo todo) {
		return todoRepository.save(todo);
	}
}
