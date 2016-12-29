package cn.chengchaos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping
public class DemoController {

	@RequestMapping(value={"/spring-demo"}, method=RequestMethod.GET)
	public String demo() {
		
		System.out.println("kao, demo ");
		return "demo";
	}
}
