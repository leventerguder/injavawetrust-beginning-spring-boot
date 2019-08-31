
package demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping(value = { "/", "/home" })
	// Spring 4.3 introduced @GetMapping, @PostMapping, @PutMapping, etc.,
	// annotations as convenient composed annotations so that you don’t have to
	// specify a method type in @RequestMapping(value="/url",
	// method=RequestMethod.XXX).
	public String home(Model model) {
		return "home.html";
	}

	@GetMapping("/about")
	// Spring 4.3 introduced @GetMapping, @PostMapping, @PutMapping, etc.,
	// annotations as convenient composed annotations so that you don’t have to
	// specify a method type in @RequestMapping(value="/url",
	// method=RequestMethod.XXX).
	public String aboutUs(Model model) {
		return "about-us.html";
	}
}
