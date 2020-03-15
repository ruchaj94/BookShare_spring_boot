package com.example.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	  @RequestMapping("/hello")
	  public String index() {
		  System.out.println("inside index()");
	    return "hello.html";
	  }
	  
	  

}
