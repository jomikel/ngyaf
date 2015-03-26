package io.theoperator.controller;

import com.github.sommeri.less4j.utils.ProblemsPrinter;
import io.theoperator.model.Person;
import io.theoperator.model.Tag;
import io.theoperator.service.PersonService;
import io.theoperator.utils.jqgrid.JqGridFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by andreas on 2/12/15.
 */

@Controller
@RequestMapping("/persons")
@SessionAttributes
public class PersonController {

	private PersonService personService;

	@Autowired
	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView list() {
		return new ModelAndView("persons/list").addObject("person", new Person());
	}

	@RequestMapping(value = "{id}")
	public ModelAndView details(@PathVariable("id") String personId) {
		ModelAndView mav = new ModelAndView("persons/details");

		Person p = null;
		if("new".equals(personId))
			p = new Person();
		else
			p = this.personService.getBean(Long.valueOf(personId));

		mav.addObject("person", p);
		return mav;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("person") Person person) {

		person = this.personService.save(person);
		ModelAndView mav = new ModelAndView("redirect:/persons/" + person.getId());
		return mav;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		sdf.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}

}
