package vttp2023.batch3.ssf.frontcontroller.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.HttpClientErrorException;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import vttp2023.batch3.ssf.frontcontroller.model.Captcha;
import vttp2023.batch3.ssf.frontcontroller.model.Login;
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
	public String getLandingPage(HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}

	@GetMapping(path = "/login")
	public String getLoginPage(Model model, HttpSession session) {
		model.addAttribute("user", new User());

		Login login = (Login) session.getAttribute("login");

		if (login == null)
			login = new Login();

		model.addAttribute("loginAttempts", login.getLoginAttempts());

		return "view0";
	}

	@PostMapping(path = "/login", consumes="application/x-www-form-urlencoded", produces="text/html")
	public String postAuthenticate(@Valid User user, BindingResult binding, Model model, HttpSession session, Captcha captcha) {

		if (binding.hasErrors())
			return "view0";

		Login login = (Login) session.getAttribute("login");

		if (login == null)
			login = new Login();

		if (login.getLoginAttempts() > 0) {
			Captcha sessionCaptcha = (Captcha) session.getAttribute("captcha");

			if (captcha.getAnswer() != sessionCaptcha.getResult()) {

				login.setLoginAttempts(login.getLoginAttempts() + 1);
				System.out.println(login.getLoginAttempts());

				session.setAttribute("login", login);
				model.addAttribute("loginAttempts", login.getLoginAttempts());
				session.setAttribute("captcha", new Captcha());
				model.addAttribute("captcha", session.getAttribute("captcha"));

				return "view0";
			}
		}


		try {
			authService.authenticate(user.getUsername(), user.getPassword());
		} catch (Exception e) {

			if (e instanceof HttpClientErrorException) {

				HttpClientErrorException ex = (HttpClientErrorException) e;
				System.out.println(ex.getStatusCode().toString());

				if (ex.getStatusCode().equals(HttpStatus.BAD_REQUEST) ||
						ex.getStatusCode().equals(HttpStatus.UNAUTHORIZED)) {

					if (ex.getStatusCode().equals(HttpStatus.BAD_REQUEST)) {
						model.addAttribute("error", "Invalid payload");
					} else {
						model.addAttribute("error", "Incorrect username and/or password");
					}
					
					login.setLoginAttempts(login.getLoginAttempts() + 1);
					System.out.println(login.getLoginAttempts());

					session.setAttribute("login", login);
					model.addAttribute("loginAttempts", login.getLoginAttempts());
					session.setAttribute("captcha", new Captcha());
					model.addAttribute("captcha", session.getAttribute("captcha"));
				}

				return "view0";
			}
		}
			
		return "protected/view1";
	}
}
