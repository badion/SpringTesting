package com.epam.splitter;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.epam.dao.CustomerDao;
import com.epam.model.Customer;


public class Application {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"spring-module.xml");

		CustomerDao customerDao = (CustomerDao) context.getBean("customerDAO");
//		Customer customer = new Customer(5, "Ololo", 23);
		// customerDao.insert(customer);

		Customer customer2 = customerDao.findByCustomerId(2);
		System.out.println(customer2.getName());
		
		customerDao.getAll();
	}
}
