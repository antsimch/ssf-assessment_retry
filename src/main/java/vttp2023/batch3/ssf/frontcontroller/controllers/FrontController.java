package vttp2023.batch3.ssf.frontcontroller.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.HttpClientErrorException;

import jakarta.validation.Valid;
import vttp2023.batch3.ssf.frontcontroller.model.User;
import vttp2023.batch3.ssf.frontcontroller.services.AuthenticationService;

@Controller
public class FrontController {

	private AuthenticationService authService;

	public FrontController(AuthenticationService authService) {
		this.authService = authService;
	}

	// TODO: Task 2, Task 3, Task 4, Task 6
	@GetMapping(path = "/")
	public String getLandingPage() {
		return "redirect:/login";
	}

	@GetMapping(path = "/login")
	public String getLoginPage(Model model) {
		model.addAttribute("user", new User());
		return "view0";
	}

	@PostMapping(path = "/login", consumes="application/x-www-form-urlencoded", produces="text/html")
	public String postAuthenticate(@Valid User user, BindingResult binding, Model model) {

		if (binding.hasErrors())
			return "view0";

		try {
			authService.authenticate(user.getUsername(), user.getPassword());
		} catch (Exception e) {

			if (e instanceof HttpClientErrorException) {

				HttpClientErrorException ex = (HttpClientErrorException) e;
				System.out.println(ex.getStatusCode().toString());

				return "view0";
			}
		}
			
		return "protected/view1";
	}
}
