package com.epam.scheduling;

import java.util.List;

import com.epam.model.Customer;


public interface NumberOfCustomers {

	void sendEmail(String from, String to, List<Customer> customers);

}
