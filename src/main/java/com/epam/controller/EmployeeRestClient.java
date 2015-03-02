package com.epam.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.epam.model.Employee;

public class EmployeeRestClient {

	public List<Employee> getEmployeeFromRestJson() {
		RestTemplate restTemplate = new RestTemplate();
		List<Employee> employees = Arrays.asList(restTemplate.getForObject(
				"http://localhost:8080/SpringInActionPart6/emps",
				Employee[].class));
		return employees;
	}

	public void deleteEmployeeFromRest() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate
				.delete("http://localhost:8080/SpringInActionPart6/emp/delete/{id}");
	}
}
