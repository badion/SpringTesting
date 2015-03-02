package com.epam.dao;

import java.util.List;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.epam.model.Customer;

public interface CustomerDao {

	public void insert(Customer customer);

	public Customer findByCustomerId(int custId);

	public List<Customer> getAll();

	public Customer getCustomerById(Long id);
}
