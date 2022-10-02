package com.learn.firstrestapi.helloworld;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Controller
@RestController
public class HelloWorldResource {

	@RequestMapping("/hello-world")
	public String helloWorld() {
		return "Hello World";
	}

	@RequestMapping("/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("hello, from the bean");
	}
	
	// @PathVariable helps us, capture the values of the path parameters.

	@RequestMapping("/hello-world-bean/{name}")
	public HelloWorldBean helloWorldParams(@PathVariable String name) {
		return new HelloWorldBean(name);
	}

}
