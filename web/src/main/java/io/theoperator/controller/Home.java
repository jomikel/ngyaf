package io.theoperator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Home {

	@RequestMapping("/home")
	public ModelAndView showMessage() {
		return new ModelAndView("home");
	}
}