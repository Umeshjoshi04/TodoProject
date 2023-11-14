package com.umeshjoshi.rest.webservices.restfulwebservices.helloworld;

import java.util.Locale;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	MessageSource messageSource;

	public HelloWorldController(MessageSource messageSource) {
		super();
		this.messageSource = messageSource;
	}

	@GetMapping(path = "/hello-world")
	public String helloWorld() {
		return "Hello World";
	}

	@GetMapping(path = "/hello-world-bean")
	public HelloWroldBean helloWorldBean() {
		return new HelloWroldBean("Hello World");
	}

	@GetMapping(path = "/hello-world-bean/path-variable/{name}")
	public HelloWroldBean helloWorldBeanPathVaribale(@PathVariable String name) {
		return new HelloWroldBean(String.format("Hello World %s", name));
	}

	@GetMapping(path = "/hello-world-internationalisation")
	public String helloWorldInternationalisation() {
		Locale locale = LocaleContextHolder.getLocale(); // this will fetch the value from accept-header
		return messageSource.getMessage("good.morning.message", null, "Default msg", locale);
	}
}
