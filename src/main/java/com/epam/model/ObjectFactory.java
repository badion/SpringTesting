package com.epam.model;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {

	public ObjectFactory() {
	}

	public Customer createCustomer() {
		return new Customer();
	}
}
