package com.epam.integration.gateways;

import java.util.Date;

import org.springframework.messaging.Message;

import com.epam.model.Customer;

public class CustomerSaveDate {

	public Date addDate(Message<Customer> date) {
		return new Date();
	}

}
