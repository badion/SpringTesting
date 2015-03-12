package com.epam.dao;

import java.util.List;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import org.springframework.integration.annotation.Gateway;

import com.epam.model.Customer;

public interface CustomerDao {

	@Gateway(requestChannel = "insertCustomer")
	public void insert(Customer customer);

	@Gateway(requestChannel = "customerById")
	public Customer findByCustomerId(int custId);

	@Gateway(requestChannel = "allCustomers")
	public List<Customer> getAll();

	public Customer getCustomerById(Long id);

	@Gateway(requestChannel = "customerByFacebookId")
	public Customer getCustomerByFacebookId(String facebookId);
}
