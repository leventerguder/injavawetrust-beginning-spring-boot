package demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import demo.repository.TodoRepository;

@Controller
public class TodoController {
	
	@Autowired
	private TodoRepository todoRepository;

	@GetMapping("/todolist")
	public String showTodos(Model model) {
		model.addAttribute("todos", todoRepository.findAll());
		return "todos";
	}
}