package com.umeshjoshi.rest.webservices.restfulwebservices.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

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
}
