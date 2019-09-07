package demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import demo.domain.User;

@Controller
public class HomeController {

	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("message", "SpringBoot + Thymeleaf rocks");
		return "index";
	}

	@GetMapping(value = "/login")
	public String loginForm(Model model) {
		model.addAttribute("user", new User());
		return "login";
	}

	@PostMapping(value = "/login")
	public String login(User user, Model model) {
		System.out.println("Login User: " + user);
		return "redirect:/";
	}

}
