package com.epam.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.epam.dao.CustomerDao;
import com.epam.model.Customer;

@Controller
public class HomeController {
	public static final int DEFAULT_SPITTLES_PER_PAGE = 25;


	public HomeController() {
	}

	@RequestMapping({ "/", "/hello" })
	public String showHomePage() {
		return "hello";
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public ModelAndView adminPage() {

		ApplicationContext context = new ClassPathXmlApplicationContext(
				"spring-module.xml");
		CustomerDao customerDao = (CustomerDao) context.getBean("customerDAO");
		EmployeeRestClient empRestClient = (EmployeeRestClient) context
				.getBean("employeeRestClient");

		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security Custom Login Form");
		model.addObject("message", "This is protected page!");
		model.addObject("message", customerDao.getAll().toString());
		model.setViewName("admin");

		model.addObject("emps", empRestClient.getEmployeeFromRestJson());

		return model;

	}


	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public ModelAndView registrationPage() {
		ModelAndView model = new ModelAndView();
		model.addObject("customer", new Customer());
		return model;
	}
}