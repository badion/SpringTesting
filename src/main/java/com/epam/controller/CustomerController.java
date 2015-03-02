package com.epam.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.epam.dao.CustomerDao;
import com.epam.model.Customer;

@Controller
public class CustomerController {

	private Map<String, Customer> customers = null;

	private CustomerDao customerDao;

	public CustomerController() {
		customers = new HashMap<String, Customer>();
	}

	@RequestMapping(value = "/cust/save", method = RequestMethod.GET)
	public String saveCustomerPage(Model model) {
		model.addAttribute("customer", new Customer());
		return "registration";
	}

	@RequestMapping(value = "/cust/save.do", method = RequestMethod.POST)
	public String saveCustomerAction(@Valid Customer customer,
			BindingResult bindinfResult, Model model) {

		if (bindinfResult.hasErrors()) {
			return "registration";
		}

		model.addAttribute("customer", customer);
		customers.put(customer.getEmail(), customer);
		customerDao.insert(customer);

		return "registrationSuccess";
	}

	@RequestMapping(value = "/404", method = RequestMethod.GET)
	public String pageNotFound() {
		return "pageNotFound";
	}

	@Inject
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}
}
