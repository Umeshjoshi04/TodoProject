package com.umeshjoshi.springboot.myfirstwebapp.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({"name", "password"})
public class LoginController {
	
	private AuthenticationService authenticationService;

	public LoginController(AuthenticationService authenticationService) {
		super();
		this.authenticationService = authenticationService;
	}
	@RequestMapping(value="login", method=RequestMethod.GET)
	public String login() {
		return "login";
	}
	@RequestMapping(value="login", method=RequestMethod.POST)
	public String welcome(@RequestParam String name,
			@RequestParam String password,
			ModelMap model) {
		if(authenticationService.authenticate(name, password)) {
			model.put("name", name);
			model.put("password", password);
				return "welcome";
		}
		model.put("errorMsg", "error aa gaya");
		return "login";

	}
}
